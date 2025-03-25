package com.example.demo.controller;

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
	public String contact(@RequestParam String name,
						  @RequestParam String email,
						  Model model) {
		// 入力値チェック
		List<String> errors = new ArrayList<String>();
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
		
		// 遷移先画面への引き継ぎ
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
