package jp.co.sss.project.controller;

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

import jp.co.sss.project.form.LoginFormValidation;
import jp.co.sss.project.repository.EmployeeRepository;
import jp.co.sss.project.repository.InfoRepository;

@Controller
public class IndexController {
	
	/*顧客レポジトリの宣言*/
	@Autowired
	EmployeeRepository employeerepository;
	@Autowired
	InfoRepository inforepository;
	
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
				return "common";
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
		model.addAttribute("newses", inforepository.findAll());
		return "news_list";
	}
	/*
	 * お知らせ詳細 
	 * IDをクリックすると詳細に飛ぶことができる
	 */
	@RequestMapping("/showNewsDetail/{id}")
	public String showNewsDetail(Model model, @PathVariable int id) {
		model.addAttribute("news", inforepository.getOne(id));
		return "news_list_detail";
	}
}
