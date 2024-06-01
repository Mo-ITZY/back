package Collabo.MoITZY.web.service;

import Collabo.MoITZY.domain.Member;
import Collabo.MoITZY.web.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.dao.IncorrectResultSizeDataAccessException; // 추가

@Service
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    public Member login(String loginId, String password) {
        System.out.println("Attempting login for Login ID: " + loginId);

        try {  // 중복된 멤버 검색 시 예외 처리 및 null 반환, 비밀번호 불일치 시 null 반환
            Member member = memberRepository.findByLoginId(loginId)
                    .orElseThrow(() -> new IllegalArgumentException("No member found with Login ID: " + loginId));

            System.out.println("Member found: " + member.getLoginId());

            if (member.getPassword().equals(password)) {
                System.out.println("Password match");
                return member;
            } else {
                System.out.println("Password mismatch");
                return null; // 비밀번호 불일치 시 null 반환
            }
        } catch (IncorrectResultSizeDataAccessException ex) {
            System.out.println("Multiple members found with Login ID: " + loginId);
            // 여러 개의 결과가 반환되는 경우 예외 처리
            // 예외를 처리하는 방법은 상황에 따라 달라질 수 있음
            // 여기서는 예외를 잡고 null을 반환하여 처리하도록 함
            return null;
        }
    }
}
