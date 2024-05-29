package Collabo.MoITZY.web.controller;

import Collabo.MoITZY.domain.Member;
import Collabo.MoITZY.web.service.LoginService;
import Collabo.MoITZY.web.validation.form.MemberLoginForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class LoginController {

    private final LoginService loginService;

    // 로그인 페이지
    @GetMapping("/mo-itzy/login")
    public String loginForm(@ModelAttribute("MemberLoginForm") MemberLoginForm form) {
        return "login/loginForm"; // 여기에 리액트 로그인 페이지 경로 맞추면 될듯
    }

    // 로그인
    @PostMapping("/mo-itzy/login")
    public String login(@Validated @ModelAttribute("MemberLoginForm") MemberLoginForm form, BindingResult bindingResult) {

        // 로그인 실패 - 입력값 검증 실패
        if (bindingResult.hasErrors()) {
            return "login/loginForm";
        }

        Member loginMember = loginService.login(form);

        // 로그인 실패 - 아이디 또는 비밀번호가 맞지 않음
        if (loginMember == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "login/loginForm";
        }

        // 로그인 성공
        return "redirect:/mo-itzy/"; // 여기에 리액트 메인 페이지 경로 맞추면 될듯
    }
}