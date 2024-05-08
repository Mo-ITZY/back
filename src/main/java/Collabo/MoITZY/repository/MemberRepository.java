package Collabo.MoITZY.repository;

import Collabo.MoITZY.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}