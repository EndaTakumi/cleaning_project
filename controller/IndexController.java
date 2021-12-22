package jp.co.sss.project.controller;

import java.util.ArrayList;
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

import jp.co.sss.project.entity.Goods;
import jp.co.sss.project.entity.Tax;
import jp.co.sss.project.form.LoginFormValidation;
import jp.co.sss.project.form.ProductNumberList;
import jp.co.sss.project.repository.CustomerRepository;
import jp.co.sss.project.repository.EmployeeRepository;
import jp.co.sss.project.repository.GoodsRepository;
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
				model.addAttribute("goodses",goodsrepository.findAll());
				List<Integer> list = new ArrayList<>();
				for(int i = 0 ; i < 3 ; i++) {
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
		model.addAttribute("goodses",goodsrepository.findAll());
		List<Integer> list = new ArrayList<>();
		for(int i = 0 ; i < 3 ; i++) {
			list.add(0);
		}
		model.addAttribute("numbers", list);
		return "product_input";
	}
	/*商品入力画面に戻る*/
	@RequestMapping("/product_input_return")
	public String showProductInputReturn(Model model, ProductNumberList form, int id) {
		model.addAttribute("goodses",goodsrepository.findAll());
		model.addAttribute("numbers", form.getNumber());
		model.addAttribute("id", id);
		return "product_input";
	}
	/*商品入力画面から確認画面に遷移*/
	@RequestMapping(path = "/product_input_confirm", params = "conf", method=RequestMethod.POST)
	public String showProductInputConfirm(Model model, ProductNumberList form, int id) {
		model.addAttribute("customer", customerrepository.getOne(id));
		
		/*商品を全件検索してリストに格納*/
		List<Goods> goodsList = goodsrepository.findAll();
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
		model.addAttribute("sale", -sale);
		/*
		 * 消費税を計算
		 * 今回は10%なので、切り捨てなど考慮していない
		 */
		int tax = 0;
		Tax t = new Tax();
		t = taxrepository.getOne(1);
		tax = subTotal * t.getTax() / 100;
		model.addAttribute("tax", tax);
		/*
		 * 最終的な合計を計算
		 */
		int total = 0;
		total = subTotal - sale + tax;
		model.addAttribute("total", total);
		
		return "product_input_con";
	}
	/*確認画面から確定画面への遷移*/
	@RequestMapping("/product_success")
	String showProductSuccess() {
		return "product_success";
	}
	
	
	
}
