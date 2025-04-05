package com.mall.controller;

import com.mall.model.User;
import com.mall.service.UserService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    // 회원가입 페이지
    @GetMapping("/signUp")
    public String signUpForm() {
        return "signUp";
    }

 // 회원가입 처리
    @PostMapping("/signUp")
    public String signUp(@ModelAttribute("user") User user, Model model) {
        try {
            // 회원가입 처리
            userService.signUpUser(user);
            return "redirect:/login"; // 회원가입 성공 후 로그인 페이지로 리다이렉트
        } catch (IllegalArgumentException e) {
            // 이미 존재하는 사용자 이름일 경우
            model.addAttribute("error", e.getMessage()); // 예외 메시지를 에러로 전달
            return "signUp"; // 회원가입 페이지로 돌아가기
        } catch (Exception e) {
            // 기타 오류 발생 시
            e.printStackTrace();
            model.addAttribute("error", "회원가입 처리 중 오류가 발생했습니다.");
            return "signUp";
        }
    }



    // 로그인 페이지
    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }
    
    @PostMapping("/login")
    public String login(
            @RequestParam String username, 
            @RequestParam String password, 
            Model model, 
            HttpSession session, 
            HttpServletResponse response) { // HttpServletResponse 추가

        // 사용자 정보 가져오기
        User user = userService.getUserByUsername(username);
        
        if (user != null && user.getPassword().equals(password)) {
            // 세션 ID 가져오기
            String sessionId = session.getId();

            // JSESSIONID 쿠키 설정
            Cookie cookie = new Cookie("JSESSIONID", sessionId);
            cookie.setHttpOnly(false); // JavaScript에서 접근 가능하게 설정
            cookie.setPath("/"); // 경로 설정
            cookie.setMaxAge(3600); // 1시간 유효
            response.addCookie(cookie);

         // 세션에 사용자 정보 저장
            session.setAttribute("username", user.getUsername()); // 사용자명 저장
            session.setAttribute("userId", user.getUserId()); // ID도 저장

         // ✅ 디버깅을 위해 콘솔에 출력
            System.out.println("로그인 성공: " + user.getUsername()); 
            System.out.println("세션에 저장된 userId: " + session.getAttribute("userId"));
            
            // 로그인 성공 후 메인 페이지로 리다이렉트
            return "redirect:/";
        } else {
            // 로그인 실패 시 에러 메시지 설정
            model.addAttribute("error", "아이디 또는 비밀번호가 잘못되었습니다.");
            return "login";
        }
    }


    // 로그아웃 처리
    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletResponse response) {
        // 세션 종료
        session.invalidate();

        // JSESSIONID 쿠키 삭제
        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setMaxAge(0); // 쿠키 만료 시간 설정
        cookie.setPath("/");  // 경로 설정
        response.addCookie(cookie);

        // 로그아웃 후 홈 화면으로 리다이렉트
        return "redirect:/"; 
    }

    // 마이페이지 (회원 정보 수정)
    @GetMapping("/mypage")
    public String myPage(HttpSession session, Model model) {
        Integer userId = (Integer) session.getAttribute("userId"); // 세션에서 userId 가져오기
        
        if (userId == null) {
        	 System.out.println("세션에 userId가 없습니다.");
        	 model.addAttribute("error", "로그인 후 이용 가능합니다.");
            return "redirect:/login"; // 로그인되지 않은 사용자 처리
        }
        
        User user = userService.getUserById(userId);  // userId로 사용자 정보 가져오기
        model.addAttribute("user", user);
        return "mypage";
    }


    // 회원 정보 수정 처리
    @PostMapping("/mypage/update")
    public String updateUser(@ModelAttribute User user, HttpSession session) {
        userService.updateUser(user);
        session.setAttribute("userId", user.getUserId()); // 세션에 업데이트된 userId 저장
        return "redirect:/mypage"; // 마이페이지로 리다이렉트
    }
}
