package com.clonecode.repairweb.initDB;

import com.clonecode.repairweb.domain.Address;
import com.clonecode.repairweb.domain.item.AirConditioner;
import com.clonecode.repairweb.domain.item.Cleaner;
import com.clonecode.repairweb.domain.item.ItemType;
import com.clonecode.repairweb.domain.item.Tv;
import com.clonecode.repairweb.domain.login.Admin;
import com.clonecode.repairweb.domain.login.Member;
import com.clonecode.repairweb.domain.login.Repairman;
import com.clonecode.repairweb.repository.AdminRepository;
import com.clonecode.repairweb.repository.ItemRepository;
import com.clonecode.repairweb.repository.MemberRepository;
import com.clonecode.repairweb.repository.RepairmanRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final AdminRepository adminRepository;
    private final MemberRepository memberRepository;
    private final RepairmanRepository repairmanRepository;
    private final ItemRepository itemRepository;

    @PostConstruct
    @Transactional
    public void loadData(){
        loadAdminData();
        loadMemberData();
        loadRepairmanData();
        loadItemData();
    }

    public void loadAdminData(){
        Admin admin = new Admin();
        admin.setName("admin");
        admin.setLoginId("admin");
        admin.setPassword("1111");
        adminRepository.save(admin);
    }

    public void loadMemberData(){
        Member member = new Member();
        member.setName("member");
        member.setLoginId("member");
        member.setPassword("1111");
        member.setAddress(new Address("서울", "광안로 11길 12"));
        memberRepository.save(member);
    }

    public void loadRepairmanData(){
        Repairman repairman1 = new Repairman();
        repairman1.setName("repairman1");
        repairman1.setLoginId("repairman1");
        repairman1.setPassword("1111");
        repairman1.setRegion("서울");
        repairmanRepository.save(repairman1);

        Repairman repairman2 = new Repairman();
        repairman2.setName("repairman2");
        repairman2.setLoginId("repairman2");
        repairman2.setPassword("1111");
        repairman2.setRegion("서울");
        repairmanRepository.save(repairman2);

        Repairman repairman3 = new Repairman();
        repairman3.setName("repairman3");
        repairman3.setLoginId("repairman3");
        repairman3.setPassword("1111");
        repairman3.setRegion("인천");
        repairmanRepository.save(repairman3);
    }

    public void loadItemData(){
        AirConditioner airConditioner = new AirConditioner();
        airConditioner.setName("Air Conditioner");
        airConditioner.setSerialNumber("AC1");
        airConditioner.setRepairFee(15000);
        airConditioner.setItemType(ItemType.AIR_CONDITIONER);
        itemRepository.save(airConditioner);

        Cleaner cleaner = new Cleaner();
        cleaner.setName("Cleaner");
        cleaner.setSerialNumber("CL1");
        cleaner.setRepairFee(8000);
        cleaner.setItemType(ItemType.CLEANER);
        itemRepository.save(cleaner);

        Tv tv = new Tv();
        tv.setName("TV");
        tv.setSerialNumber("TV1");
        tv.setRepairFee(20000);
        tv.setItemType(ItemType.TV);
        itemRepository.save(tv);
    }

}






















