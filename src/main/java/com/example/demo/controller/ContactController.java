package com.example.demo.controller;

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
		if (name == null || name.isEmpty()) {
			model.addAttribute("error", "名前は必須です");
			return "contactForm"; // ここで返却してしまえば、あとのコードを変更する必要がない
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
