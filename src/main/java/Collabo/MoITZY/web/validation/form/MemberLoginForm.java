package Collabo.MoITZY.web.validation.form;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;


@Data
public class MemberLoginForm {

    @NotBlank
    private String loginId;

    @NotBlank
    private String password;

    // Getters and setters
    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
