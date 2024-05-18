package Collabo.MoITZY.controller;

import Collabo.MoITZY.service.MemberService;
import Collabo.MoITZY.web.validation.form.MemberJoinForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class JoinController {

    private final MemberService memberService;

    // 회원 가입 페이지
    @GetMapping("/mo-itzy/join")
    public String joinForm(@ModelAttribute("memberJoinForm") MemberJoinForm form) {
        return "/signin"; // 여기에 리액트 회원가입 페이지 경로 맞추면 될듯
    }

    // 회원 가입
    @PostMapping("/mo-itzy/join")
    public String join(@ModelAttribute("memberJoinForm") MemberJoinForm form) {
        memberService.join(form);
        return "redirect:/login"; // 여기에 리액트 로그인 페이지 경로 맞추면 될듯
    }
}