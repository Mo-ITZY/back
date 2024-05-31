package Collabo.MoITZY.web.service;

import Collabo.MoITZY.domain.Member;
import Collabo.MoITZY.web.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public Member login(String loginId, String password) {
        System.out.println("Attempting login for Login ID: " + loginId); // 입력된 Login ID 출력
        Member member = memberRepository.findByLoginId(loginId).orElse(null);

        if (member != null) {
            System.out.println("Member found: " + member.getLoginId()); // 찾은 Member의 Login ID 출력
            if (member.getPassword().equals(password)) {
                System.out.println("Password match");
                return member;
            } else {
                System.out.println("Password mismatch");
            }
        } else {
            System.out.println("No member found with Login ID: " + loginId);
        }
        return null;
    }
}
