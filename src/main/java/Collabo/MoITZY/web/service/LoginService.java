package Collabo.MoITZY.web.service;

import Collabo.MoITZY.domain.Member;
import Collabo.MoITZY.web.repository.MemberRepository;
import Collabo.MoITZY.web.validation.form.MemberLoginForm;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class LoginService {

    private final MemberRepository memberRepository;

    /**
     * return null 이면 로그인 실패
     * 로그인할 때 기입한 password와 db에 저장된 password가 같은지 확인하는 메소드
     */
    public Member login(MemberLoginForm member) {
        return memberRepository.findByLoginId(member.getLoginId())
                .filter(m -> m.getPassword().equals(member.getPassword()))
                .orElse(null);
    }
}