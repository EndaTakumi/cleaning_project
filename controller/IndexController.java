package jp.co.sss.project.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import jp.co.sss.project.entity.Customer;
import jp.co.sss.project.entity.Goods;
import jp.co.sss.project.entity.Hist;
import jp.co.sss.project.entity.Histdetail;
import jp.co.sss.project.form.LoginFormValidation;
import jp.co.sss.project.form.ProductNumberList;
import jp.co.sss.project.repository.CustomerRepository;
import jp.co.sss.project.repository.EmployeeRepository;
import jp.co.sss.project.repository.GoodsRepository;
import jp.co.sss.project.repository.HistRepository;
import jp.co.sss.project.repository.HistdetailRepository;
import jp.co.sss.project.repository.InfoRepository;
import jp.co.sss.project.repository.SaleRepository;
import jp.co.sss.project.repository.TaxRepository;

@Controller
public class IndexController {
	
	/*レポジトリの宣言*/
	@Autowired
	EmployeeRepository employeerepository;
	@Autowired
	InfoRepository inforepository;
	@Autowired
	GoodsRepository goodsrepository;
	@Autowired
	CustomerRepository customerrepository;
	@Autowired
	TaxRepository taxrepository;
	@Autowired
	SaleRepository salerepository;
	@Autowired
	HistRepository histrepository;
	@Autowired
	HistdetailRepository histdetailrepository;
	
	
	/*トップページ（ログイン画面の表示）*/
	@RequestMapping("/index")
	public String login(@ModelAttribute LoginFormValidation form) {
		return "login";
	}
	
	/*ログイン処理+ダメであればエラーメッセージ表示*/
	@RequestMapping(path = "/doLogin", method = RequestMethod.POST)
	public String doLogin(@Valid @ModelAttribute LoginFormValidation form, BindingResult result ,Model model, HttpSession session) {
		//入力チェックエラーがあったとき
		if(result.hasErrors()) {
			model.addAttribute("jsFlg", "1");
			return login(form);
		} else {
			//IDとPWが一致したとき
			if(employeerepository.findByEmployeeIdAndEmployeePw(Integer.valueOf(form.getEmployeeId()), form.getEmployeePw()).size() != 0) {
				//セッションにIDと名前をセットして商品入力画面に遷移
				session.setAttribute("employeeId", form.getEmployeeId());
				session.setAttribute("employeeNameSei", employeerepository.findEmployeeNameSei(Integer.valueOf(form.getEmployeeId())));
				session.setAttribute("employeeNameMei", employeerepository.findEmployeeNameMei(Integer.valueOf(form.getEmployeeId())));
				List<Goods> goodsList = goodsrepository.findAllWithDeleteFlg();
				model.addAttribute("goodses",goodsList);
				List<Integer> list = new ArrayList<>();
				for(int i = 0 ; i < goodsList.size() ; i++) {
					list.add(0);
				}
				model.addAttribute("numbers", list);
				return "product_input";
			}
			//一致しなかった場合
			model.addAttribute("misMatchFlg", "1");
			return "login";
		}
	}
	/*ログアウト処理*/
	@RequestMapping("/logout")
	public String logout(@ModelAttribute LoginFormValidation form, HttpSession session) {
		session.invalidate();
		return "login";
	}
	/*
	 * お知らせ一覧
	 * 掲載期間のものだけ表示をする 
	 */
	@RequestMapping("/showAllNews")
	public String showAllNews(Model model) {
		model.addAttribute("newses", inforepository.findByKeisaiDateBetween());
		return "news_list";
	}
	/*
	 * お知らせ詳細 
	 * IDをクリックすると詳細に遷移する
	 */
	@RequestMapping("/showNewsDetail/{id}")
	public String showNewsDetail(Model model, @PathVariable int id) {
		model.addAttribute("news", inforepository.getOne(id));
		return "news_list_detail";
	}
	/*商品入力画面に遷移する*/
	@RequestMapping("/product_input")
	public String showProductInput(Model model) {
		List<Goods> goodsList = goodsrepository.findAllWithDeleteFlg();
		model.addAttribute("goodses",goodsList);
		model.addAttribute("goodsNumber",goodsList.size());
		List<Integer> list = new ArrayList<>();
		for(int i = 0 ; i < goodsList.size() ; i++) {
			list.add(0);
		}
		model.addAttribute("numbers", list);
		return "product_input";
	}
	
	/*商品入力画面から確認画面に遷移*/
	@RequestMapping(path = "/product_input_confirm", params = "conf", method=RequestMethod.POST)
	public String showProductInputConfirm(Model model, ProductNumberList form, String id) {
		if(id.equals("")) {
			model.addAttribute("customer", customerrepository.getOne(0));
		} else {
			model.addAttribute("customer", customerrepository.getOne(Integer.parseInt(id)));
		}
		
		/*商品を全件検索してリストに格納*/
		List<Goods> goodsList = goodsrepository.findAllWithDeleteFlg();
		/*小計を格納するリストを宣言*/
		List<Integer> subTotalList = new ArrayList<>();
		for(int i = 0 ; i < goodsList.size() ; i++) {
			subTotalList.add(goodsList.get(i).getPrice() * form.getNumber().get(i));
		}
		model.addAttribute("goodses", goodsList);
		model.addAttribute("numbers", form.getNumber());
		model.addAttribute("subTotalList", subTotalList);
		
		/*小計合計*/
		int subTotal = 0;
		for(int i = 0 ; i < subTotalList.size() ; i++) {
			subTotal += subTotalList.get(i);
		}
		model.addAttribute("subTotal",subTotal);
		
		/* セールの額を計算*/
		int sale = 0;
		//nullチェックしたい
		int discount = salerepository.findDiscountBetween();
		sale = subTotal * discount / 100;
		model.addAttribute("discount", discount);
		model.addAttribute("sale", -sale);
		/*
		 * 消費税を計算
		 * 今回は10%なので、切り捨てなど考慮していない
		 */
		int salesTax = 0;
		int tax = taxrepository.getOne(1).getTax();
		salesTax = subTotal * tax / 100;
		model.addAttribute("tax", tax);
		model.addAttribute("salesTax", salesTax);
		/*
		 * 最終的な合計を計算
		 */
		int total = 0;
		total = subTotal - sale + tax;
		model.addAttribute("total", total);
		
		return "product_input_con";
	}
	/*商品入力画面に戻る*/
	@RequestMapping(path = "/product_input_return", method = RequestMethod.POST)
	public String showProductInputReturn(Model model, ProductNumberList form, int id) {
		model.addAttribute("goodses",goodsrepository.findAllWithDeleteFlg());
		model.addAttribute("numbers", form.getNumber());
		model.addAttribute("id", id);
		return "product_input";
	}
	/*確認画面から確定画面への遷移
	 * データの受け渡し*/
	@RequestMapping(path = "/product_success", method = RequestMethod.GET)
	String showProductSuccess(Model model, ProductNumberList form, int id, int discount, int tax) {
		//histテーブル登録
		Hist hist = new Hist();
		Customer customer = new Customer();
		customer.setId(id);
		hist.setCustomer(customer);
		hist.setBuyDate(new Date());
		hist.setDiscount(discount);
		hist.setTax(tax);
		Hist h = histrepository.saveAndFlush(hist);
		
		
		//histdetailテーブル登録
		//商品の数だけ登録をする
		List<Integer> histdetailIds = new ArrayList<>();
		List<Goods> goodsList = goodsrepository.findAllWithDeleteFlg();
		for(int i = 0 ; i < goodsList.size() ; i++) {
			Histdetail histdetail = new Histdetail();
			Hist hist1 = new Hist();
			hist1.setId(hist.getId());
			histdetail.setHist(hist1);
			histdetail.setGoods(goodsList.get(i));
			histdetail.setCount(form.getNumber().get(i));
			histdetail.setPrice(goodsList.get(i).getPrice());
			Histdetail hd = histdetailrepository.saveAndFlush(histdetail);
			histdetailIds.add(histdetail.getId());
		}
		model.addAttribute("histdetailIds", histdetailIds);
		return "product_success";
	}
	/*商品入力画面から顧客選択するためのダイアログ表示*/
	@RequestMapping("/customer_dia")
	String searchCustomer() {
		return "customer_dia";
	}
	/*商品履歴一覧画面への遷移*/
	@RequestMapping("/product_hist")
	public String showAllProductHist(Model model) {
		//履歴をリクエストスコープに保存
		List<Hist> hists = histrepository.findAll();
		model.addAttribute("hists", hists);
		
		//合計数のリストをリクエストスコープに保存
		List<String> totalList = new ArrayList<>();
		for(int i = 1 ; i <= hists.size() ; i++) {
			//hist_idごとの合計数をDBからもってくる
			Hist h = new Hist();
			h.setId(i);
			String total = histdetailrepository.findTotalGroupByHistId(h);
			//nullが返ってきたら、0をリストに入れる
			if(total == null) { total = "0"; }
			totalList.add(total);
		}
		model.addAttribute("totalList", totalList);
		
		return "product_hist";
	}
	/*商品履歴詳細への遷移*/
	@RequestMapping("/product_hist_detail/{id}")
	public String showProductHistDetail(Model model, @PathVariable int id) {
		model.addAttribute("histdetails", histdetailrepository.getOne(id));
		return "product_hist_detail";
	}
	
}
