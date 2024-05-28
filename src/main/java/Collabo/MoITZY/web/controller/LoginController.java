package Collabo.MoITZY.web.controller;

import Collabo.MoITZY.domain.Member;
import Collabo.MoITZY.web.service.LoginService;
import Collabo.MoITZY.web.validation.form.MemberLoginForm;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
<<<<<<< HEAD:src/main/java/Collabo/MoITZY/web/controller/LoginController.java
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
=======
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;
>>>>>>> 309dbc54dbd260d8fbb097a16626eec427277cce:src/main/java/Collabo/MoITZY/controller/LoginController.java

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LoginController {

    private final LoginService loginService;

<<<<<<< HEAD:src/main/java/Collabo/MoITZY/web/controller/LoginController.java
    // 로그인 페이지
    @GetMapping("/mo-itzy/login")
    public String loginForm(@ModelAttribute("MemberLoginForm") MemberLoginForm form) {
        // 모델에 담긴 정보를 리액트에서는 어떻게 사용할지..?
        return "login/loginForm"; // 여기에 리액트 로그인 페이지 경로 맞추면 될듯
    }
=======
    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<Map<String, String>> login(@Validated @RequestBody MemberLoginForm form, BindingResult bindingResult) {
        System.out.println("LoginId: " + form.getLoginId());
        System.out.println("Password: " + form.getPassword());
>>>>>>> 309dbc54dbd260d8fbb097a16626eec427277cce:src/main/java/Collabo/MoITZY/controller/LoginController.java

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
