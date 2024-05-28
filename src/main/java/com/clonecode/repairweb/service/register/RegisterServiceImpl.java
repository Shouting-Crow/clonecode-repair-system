package com.clonecode.repairweb.service.register;

import com.clonecode.repairweb.domain.login.Admin;
import com.clonecode.repairweb.domain.login.Member;
import com.clonecode.repairweb.domain.login.Repairman;
import com.clonecode.repairweb.domain.login.User;
import com.clonecode.repairweb.repository.login.AdminRepository;
import com.clonecode.repairweb.repository.login.MemberRepository;
import com.clonecode.repairweb.repository.login.RepairmanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class RegisterServiceImpl implements RegisterService{

    private final AdminRepository adminRepository;
    private final MemberRepository memberRepository;
    private final RepairmanRepository repairmanRepository;

    @Override
    public User registerUser(User user) {
        if (user instanceof Admin){
            return adminRepository.save((Admin) user);
        } else if(user instanceof Member){
            return memberRepository.save((Member) user);
        } else if (user instanceof Repairman){
            return repairmanRepository.save((Repairman) user);
        } else {
            new IllegalArgumentException("Unknown user type");
            return null;
        }
    }
}
