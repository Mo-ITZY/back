package Collabo.MoITZY.controller;

import Collabo.MoITZY.domain.Member;
import Collabo.MoITZY.service.LoginService;
import Collabo.MoITZY.web.validation.form.MemberLoginForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@RequestMapping("/api")
public class LoginController {

    private final LoginService loginService;

    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<Map<String, String>> login(@Validated @RequestBody MemberLoginForm form, BindingResult bindingResult) {
        System.out.println("LoginId: " + form.getLoginId());
        System.out.println("Password: " + form.getPassword());

        Map<String, String> response = new HashMap<>();

        if (bindingResult.hasErrors()) {
            System.out.println("Validation errors: " + bindingResult.getAllErrors());
            response.put("status", "failure");
            response.put("message", "Validation errors");
            return ResponseEntity.badRequest().body(response);
        }

        Member loginMember = loginService.login(form.getLoginId(), form.getPassword());

        if (loginMember == null) {
            System.out.println("Login failed");
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            response.put("status", "failure");
            response.put("message", "아이디 또는 비밀번호가 맞지 않습니다.");
            return ResponseEntity.status(401).body(response);
        }

        System.out.println("Login successful");
        response.put("status", "success");
        response.put("message", "Login successful");
        return ResponseEntity.ok(response);
    }
}
