package com.clonecode.repairweb.repository;

import com.clonecode.repairweb.domain.login.Admin;
import com.clonecode.repairweb.domain.login.Member;
import com.clonecode.repairweb.domain.login.Repairman;
import com.clonecode.repairweb.domain.login.User;
import com.clonecode.repairweb.service.register.RegisterService;
import com.clonecode.repairweb.service.user.UserService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
@Transactional
public class RegisterAndReadTest {

    @Autowired
    UserService userService;
    @Autowired
    RegisterService registerService;

    @Test
    void registerTest() throws Exception{

        Member member = new Member();
        member.setName("kim");
        member.setLoginId("member1");
        member.setPassword("1111");

        User savedUser = registerService.registerUser(member);

        assertThat(member).isEqualTo(userService.findOne(savedUser.getId()));
    }

    @Test
    void registerTest2() throws Exception{

        Member member = new Member();
        member.setName("kim");
        member.setLoginId("member1");
        member.setPassword("1111");

        Admin admin = new Admin();
        admin.setName("Jo");
        admin.setLoginId("admin1");
        admin.setPassword("1111");

        Repairman repairman = new Repairman();
        repairman.setName("Go");
        repairman.setLoginId("repairman1");
        repairman.setPassword("1111");

        registerService.registerUser(member);
        registerService.registerUser(admin);
        registerService.registerUser(repairman);

        List<User> users = userService.findUsers();

        for (User user : users) {
            System.out.println("user : " + user);
        }
    }

    @Test
    void duplicatedUserRead() throws Exception{

        Member member1 = new Member();
        member1.setName("kim");
        member1.setLoginId("member1");
        member1.setPassword("1111");

        Member member2 = new Member();
        member2.setName("park");
        member2.setLoginId("member2");
        member2.setPassword("1111");

        registerService.registerUser(member1);
        registerService.registerUser(member2);


    }

}
