package com.mall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpSession;


@Controller
public class HomeController {

    @GetMapping("/")
    public String home(HttpSession session, Model model) {
        // 세션에서 username을 가져옵니다.
        String username = (String) session.getAttribute("username");

        // username이 세션에 존재하면 모델에 추가
        if (username != null) {
            model.addAttribute("username", username);
        }
        
    
        // home.html 템플릿을 반환
        return "index";  // 이 home은 templates 폴더 아래의 home.html 파일을 의미
    }
}
