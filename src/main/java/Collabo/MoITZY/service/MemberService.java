package Collabo.MoITZY.service;

import Collabo.MoITZY.domain.Member;
import Collabo.MoITZY.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

//    @Transactional
//    public Long join(Member member) {
//
//        return member.get
//    }

}