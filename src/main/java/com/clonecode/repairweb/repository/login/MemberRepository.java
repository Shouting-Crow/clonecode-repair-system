package com.clonecode.repairweb.repository.login;

import com.clonecode.repairweb.domain.login.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByLoginId(String loginId);
}


