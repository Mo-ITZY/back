package Collabo.MoITZY.controller;

import Collabo.MoITZY.service.MemberService;
import Collabo.MoITZY.web.validation.form.MemberJoinForm;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequiredArgsConstructor
public class JoinController {

    private final MemberService memberService;

    @GetMapping("/api/test")
    public String test() {
        return "test!!!!";
    }

    // 회원 가입 페이지
    @GetMapping("/mo-itzy/join")
    public String joinForm(@ModelAttribute("memberJoinForm") MemberJoinForm form) {
        return "join/joinForm"; // 여기에 리액트 회원가입 페이지 경로 맞추면 될듯
    }

    // 회원 가입
    @PostMapping("/mo-itzy/join")
    public String join(@ModelAttribute("memberJoinForm") MemberJoinForm form) {
        memberService.join(form);
        return "redirect:/mo-itzy/login"; // 여기에 리액트 로그인 페이지 경로 맞추면 될듯
    }
}