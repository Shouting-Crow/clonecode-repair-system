package com.clonecode.repairweb.service.user;

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

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class UserServiceImpl implements UserService{

    private final AdminRepository adminRepository;
    private final MemberRepository memberRepository;
    private final RepairmanRepository repairmanRepository;

    @Override
    public List<User> findUsers() {
        List<User> users = new ArrayList<>();

        users.addAll(adminRepository.findAll());
        users.addAll(memberRepository.findAll());
        users.addAll(repairmanRepository.findAll());

        return users;
    }

    @Override
    public User findOne(Long userId) {
        Optional<Admin> admin = adminRepository.findById(userId);
        if (admin.isPresent()){
            return admin.get();
        }

        Optional<Member> member = memberRepository.findById(userId);
        if (member.isPresent()){
            return member.get();
        }

        Optional<Repairman> repairman = repairmanRepository.findById(userId);
        if (repairman.isPresent()){
            return repairman.get();
        }

        return null;
    }

    @Override
    public List<Repairman> findRepairmen() {
        return repairmanRepository.findAll();
    }

    @Override
    public List<Repairman> findRepairmenByMemberCity(String city) {
        return repairmanRepository.findByRegion(city);
    }
}
