package com.clonecode.repairweb.service.login;

import com.clonecode.repairweb.domain.login.Admin;
import com.clonecode.repairweb.domain.login.Member;
import com.clonecode.repairweb.domain.login.Repairman;
import com.clonecode.repairweb.domain.login.User;
import com.clonecode.repairweb.repository.login.AdminRepository;
import com.clonecode.repairweb.repository.login.MemberRepository;
import com.clonecode.repairweb.repository.login.RepairmanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LoginServiceImpl implements LoginService{

    private final AdminRepository adminRepository;
    private final MemberRepository memberRepository;
    private final RepairmanRepository repairmanRepository;

    @Override
    public Optional<? extends User> login(String loginId, String password) {
        Optional<Admin> admin = adminRepository.findByLoginId(loginId);
        if (admin.isPresent() && admin.get().getPassword().equals(password)){
            return admin;
        }

        Optional<Member> member = memberRepository.findByLoginId(loginId);
        if (member.isPresent() && member.get().getPassword().equals(password)){
            return member;
        }

        Optional<Repairman> repairman = repairmanRepository.findByLoginId(loginId);
        if (repairman.isPresent() && repairman.get().getPassword().equals(password)){
            return repairman;
        }

        return Optional.empty();
    }
}
