package com.clonecode.repairweb.service.register;

import com.clonecode.repairweb.domain.Address;
import com.clonecode.repairweb.domain.login.Admin;
import com.clonecode.repairweb.domain.login.Member;
import com.clonecode.repairweb.domain.login.Repairman;
import com.clonecode.repairweb.domain.login.User;
import com.clonecode.repairweb.form.AdminRegisterForm;
import com.clonecode.repairweb.form.MemberRegisterForm;
import com.clonecode.repairweb.form.RepairmanRegisterForm;
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
    public Admin registerAdmin(AdminRegisterForm form) {
        validateDuplicateAdmin(form.getLoginId());
        Admin admin = new Admin();
        admin.setName(form.getName());
        admin.setLoginId(form.getLoginId());
        admin.setPassword(form.getPassword());
        admin.setAddress(new Address(form.getCity(), form.getStreetAddress()));
        admin.setPhoneNumber(form.getPhoneNumber());
        return adminRepository.save(admin);
    }

    @Override
    public Member registerMember(MemberRegisterForm form) {
        validateDuplicateMember(form.getLoginId());
        Member member = new Member();
        member.setName(form.getName());
        member.setLoginId(form.getLoginId());
        member.setPassword(form.getPassword());
        member.setAddress(new Address(form.getCity(), form.getStreetAddress()));
        member.setPhoneNumber(form.getPhoneNumber());
        return memberRepository.save(member);
    }

    @Override
    public Repairman registerRepairman(RepairmanRegisterForm form) {
        validateDuplicateRepairman(form.getLoginId());
        Repairman repairman = new Repairman();
        repairman.setName(form.getName());
        repairman.setLoginId(form.getLoginId());
        repairman.setPassword(form.getPassword());
        repairman.setAddress(new Address(form.getCity(), form.getStreetAddress()));
        repairman.setPhoneNumber(form.getPhoneNumber());
        repairman.setRegion(form.getRegion());
        return repairmanRepository.save(repairman);
    }

    private void validateDuplicateAdmin(String loginId){
        if (adminRepository.findByLoginId(loginId).isPresent()){
            throw new IllegalStateException("이미 존재하는 계정입니다.");
        }
    }

    private void validateDuplicateMember(String loginId){
        if (adminRepository.findByLoginId(loginId).isPresent()){
            throw new IllegalStateException("이미 존재하는 계정입니다.");
        }
    }

    private void validateDuplicateRepairman(String loginId){
        if (adminRepository.findByLoginId(loginId).isPresent()){
            throw new IllegalStateException("이미 존재하는 계정입니다.");
        }
    }

}
