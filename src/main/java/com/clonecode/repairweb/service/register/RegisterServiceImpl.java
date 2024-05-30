package com.clonecode.repairweb.service.register;

import com.clonecode.repairweb.domain.login.Admin;
import com.clonecode.repairweb.domain.login.Member;
import com.clonecode.repairweb.domain.login.Repairman;
import com.clonecode.repairweb.domain.login.User;
import com.clonecode.repairweb.repository.AdminRepository;
import com.clonecode.repairweb.repository.MemberRepository;
import com.clonecode.repairweb.repository.RepairmanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class RegisterServiceImpl implements RegisterService{

    private final AdminRepository adminRepository;
    private final MemberRepository memberRepository;
    private final RepairmanRepository repairmanRepository;

    @Override
    public User registerUser(User user) {

        validateDuplicateUser(user);

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

    private void validateDuplicateUser(User user){
        if (user instanceof Admin && !adminRepository.findByName(((Admin)user).getName()).isEmpty()){
            throw new IllegalStateException("이미 존재하는 관리자입니다.");
        }
        if (user instanceof Member && !memberRepository.findByName(((Member)user).getName()).isEmpty()){
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
        if (user instanceof Repairman && !repairmanRepository.findByName(((Repairman)user).getName()).isEmpty()){
            throw new IllegalStateException("이미 존재하는 수리 기사입니다.");
        }
    }

}
