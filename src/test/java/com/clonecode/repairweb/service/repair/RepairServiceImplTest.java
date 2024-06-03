package com.clonecode.repairweb.service.repair;

import com.clonecode.repairweb.domain.Address;
import com.clonecode.repairweb.domain.Repair;
import com.clonecode.repairweb.domain.RepairStatus;
import com.clonecode.repairweb.domain.item.Cleaner;
import com.clonecode.repairweb.domain.item.Tv;
import com.clonecode.repairweb.domain.login.Member;
import com.clonecode.repairweb.domain.login.Repairman;
import com.clonecode.repairweb.domain.search.RepairSearch;
import com.clonecode.repairweb.repository.RepairRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class RepairServiceImplTest {

    @PersistenceContext
    EntityManager em;

    @Autowired RepairService repairService;
    @Autowired
    RepairRepository repairRepository;

    @Test
    void repairRegisterTest() throws Exception{

        Member member = createMember();
        Repairman repairman = createRepairman();
        Tv tv = createTv("LG 50인치 OLED 티비", 25000, "T2024");

        Long repairId = repairService.repairRegister(member.getId(), repairman.getId(), tv.getId());

        Repair findRepair = repairRepository.findById(repairId).get();

        System.out.println("member : " + findRepair.getMember().getName());
        System.out.println("repairman : " + findRepair.getRepairman().getName());
        System.out.println("tv status : " + findRepair.getItemStatus().getStatusArr());
        System.out.println("tv serialNum : " + findRepair.getRepairItems().get(0).getItem().getSerialNumber());
        System.out.println("tv basic status" +findRepair.getRepairItems().get(0).getItemStatus().getStatusArr());

        assertThat(findRepair.getStatus()).isEqualTo(RepairStatus.INIT);
        assertThat(findRepair.getRepairItems().size()).isEqualTo(1);
        assertThat(findRepair.getTotalFee()).isEqualTo(25000);
    }

    @Test
    void repairCancel(){
        Member member = createMember();
        Repairman repairman = createRepairman();
        Cleaner cleaner = createCleaner("LG 로봇 청소기", 15000, "C2024");

        Long repairId = repairService.repairRegister(member.getId(), repairman.getId(), cleaner.getId());

        repairService.repairCancel(repairId);

        Repair repair = repairRepository.findById(repairId).get();

        assertThat(repair.getStatus()).isEqualTo(RepairStatus.CANCELED);
    }

    @Test
    void repairSearchTest(){
        Member member1 = new Member();
        member1.setName("member1");
        member1.setLoginId("member1");
        member1.setPassword("1111");

        Member member2 = new Member();
        member2.setName("member2");
        member2.setLoginId("member2");
        member2.setPassword("1111");

        em.persist(member1);
        em.persist(member2);

        Repair repair1 = new Repair();
        repair1.setMember(member1);
        repair1.setStatus(RepairStatus.ACCEPTED);
        repairRepository.save(repair1);

        Repair repair2 = new Repair();
        repair2.setMember(member2);
        repair2.setStatus(RepairStatus.FINISHED);
        repairRepository.save(repair2);

        Repair repair3 = new Repair();
        repair3.setMember(member2);
        repair3.setStatus(RepairStatus.DENIED);
        repairRepository.save(repair3);

        RepairSearch repairSearch1 = new RepairSearch();
        repairSearch1.setMemberName("member2");

        List<Repair> result = repairRepository.searchRepairs(repairSearch1);

        assertThat(result).hasSize(2);

        RepairSearch repairSearch2 = new RepairSearch();
        repairSearch2.setMemberName("member1");
        repairSearch2.setRepairStatus(RepairStatus.CANCELED);

        List<Repair> result2 = repairRepository.searchRepairs(repairSearch2);

        assertThat(result2).hasSize(0);

        RepairSearch repairSearch3 = new RepairSearch();
        repairSearch3.setRepairStatus(RepairStatus.FINISHED);

        List<Repair> result3 = repairRepository.searchRepairs(repairSearch3);

        assertThat(result3).hasSize(1);


    }

    private Member createMember(){
        Member member = new Member();
        member.setName("Kim");
        member.setLoginId("member1");
        member.setPassword("1111");
        member.setAddress(new Address("seoul", "kangnamro"));
        em.persist(member);
        return member;
    }

    private Repairman createRepairman(){
        Repairman repairman = new Repairman();
        repairman.setName("Park");
        repairman.setLoginId("repairman1");
        repairman.setPassword("1111");
        repairman.setRegion("seoul");
        em.persist(repairman);
        return repairman;
    }

    private Tv createTv(String name, int repairFee, String serialNumber){
        Tv tv = new Tv();
        tv.setName(name);
        tv.setRepairFee(repairFee);
        tv.setSerialNumber(serialNumber);
        em.persist(tv);
        return tv;
    }

    private Cleaner createCleaner(String name, int repairFee, String serialNumber){
        Cleaner cleaner = new Cleaner();
        cleaner.setName(name);
        cleaner.setRepairFee(repairFee);
        cleaner.setSerialNumber(serialNumber);
        em.persist(cleaner);
        return cleaner;
    }

}