//package Collabo.MoITZY.controller;
//
//import Collabo.MoITZY.domain.Member;
//import Collabo.MoITZY.service.LoginService;
//import Collabo.MoITZY.web.validation.form.MemberLoginForm;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.*;
//
//@Controller
//@RequiredArgsConstructor
//@RequestMapping("/api")
//public class LoginController {
//
//    private final LoginService loginService;
//
//    @GetMapping("/mo-itzy/login")
//    public String loginForm(@ModelAttribute("memberLoginForm") MemberLoginForm form) {
////        return "login/loginForm"; // 로그인 폼이 있는 템플릿을 반환
//        return "/login";
//    }
//
//    @PostMapping("/mo-itzy/login")
//    @ResponseBody
//    public String login(@Validated @ModelAttribute("memberLoginForm") MemberLoginForm form, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
////            return "login/loginForm";
//            return "/login";
//        }
//
//        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
//
//        if (loginMember == null) {
//            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
////            return "login/loginForm";
//            return "/login";
//
//        }
//
//        // 로그인 성공 시 메인 페이지로 리다이렉트
//        return "redirect:/main";
//    }
//}


package Collabo.MoITZY.controller;

import Collabo.MoITZY.domain.Member;
import Collabo.MoITZY.service.LoginService;
import Collabo.MoITZY.web.validation.form.MemberLoginForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login") // 로그인 폼 페이지 요청
    public String loginForm(@ModelAttribute("memberLoginForm") MemberLoginForm form) {
//        return "login/loginForm"; // 로그인 폼이 있는 템플릿을 반환
        return "redirect:/react-login"; // login.html 뷰를 반환
    }

//    @PostMapping("/login")
//    @ResponseBody
//    public String login(@Validated @ModelAttribute("memberLoginForm") MemberLoginForm form, BindingResult bindingResult) {
//
//        System.out.println(form.getLoginId());
//        System.out.println(form.getPassword());
//
//        if (bindingResult.hasErrors()) {
//            System.out.println("ERR!!!");
////            return "login/loginForm";
//            return "redirect:/react-login";
//        }
//
//        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());
//
//        if (loginMember == null) {
//            System.out.println("NULL!!!");
//            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
////            return "login/loginForm";
//            return "/login";
//        }
//
//        // 로그인 성공 시 메인 페이지로 리다이렉트
//        return "redirect:/main";

    @CrossOrigin(origins = "http://localhost:5173") // 허용할 출처 지정
    @PostMapping("/login")
//    @ResponseBody
    public String login(@Validated @ModelAttribute("memberLoginForm") MemberLoginForm form, BindingResult bindingResult) {
        System.out.println("LoginId: " + form.getLoginId());
        System.out.println("Password: " + form.getPassword());

        if (bindingResult.hasErrors()) {
            System.out.println("Validation errors: " + bindingResult.getAllErrors());
            return "/login";
        }

        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());

        if (loginMember == null) {
            System.out.println("Login failed");
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "/login";
        }

        System.out.println("Login successful");
        return "redirect:/main";
    }
}