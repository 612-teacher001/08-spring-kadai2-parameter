package com.example.demo.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ContactController {
	@PostMapping("/contact")
	public String contact(@RequestParam(defaultValue = "") Integer genre,  // 種別
						  @RequestParam(defaultValue = "") String[] lang,  // 言語（必須）
						  @RequestParam(defaultValue = "") String detail,  // 詳細内容
						  @RequestParam(defaultValue = "") LocalDate dueDate, // 実施予定日
						  @RequestParam(defaultValue = "") String name,    // お名前（必須）
						  @RequestParam(defaultValue = "") String email,   // メールアドレス（必須）
						  Model model) {
		// 入力値チェック
		List<String> errors = new ArrayList<String>();
		// 言語のチェック
		if (lang.length == 0) {
			errors.add("言語は必須です");
		}
		
		// 実施予定日
		LocalDate today = LocalDate.now(); // 実行日現在の日付を取得
		if (dueDate != null && dueDate.compareTo(today) <= 0) {
			errors.add("実行予定日は翌日以降を選択してください");
		}
		
		// お名前のチェック
		if (name == null || name.isEmpty()) {
			errors.add("名前は必須です");
		} else if (name.length() > 20) {
			errors.add("名前は20文字以内で入力してください");
		}
		// メールアドレスのチェック
		if (email == null || email.isEmpty()) {
			errors.add("メールアドレスは必須です");
		}
		
		// エラーメッセージがある場合
		if (errors.size() > 0) {
			// エラーメッセージをスコープに登録
			model.addAttribute("errors", errors);
			// 自画面遷移
			return "contactForm";
		}
		
		// 種別の文字列化
		String genreString = "資料請求";
		if (genre == 1) {
			genreString = "見積もり依頼";
		}
		
		// 遷移先画面への引き継ぎ
		model.addAttribute("genre", genreString);
		model.addAttribute("lang", lang);
		model.addAttribute("detail", detail);
		model.addAttribute("dueDate", dueDate);
		model.addAttribute("name", name);
		model.addAttribute("email", email);;
		// 画面遷移
		return "contactResult";
	}
	
	@GetMapping("/contact")
	public String index() {
		// 初期画面表示：問合せ入力画面表示
		return "contactForm";
	}
}
