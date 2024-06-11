package com.clonecode.repairweb.domain;

import com.clonecode.repairweb.domain.login.Member;
import com.clonecode.repairweb.domain.login.Repairman;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter @Setter
public class Repair {

    @Id @GeneratedValue
    @Column(name = "repair_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "repairman_id")
    private Repairman repairman;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private LocalDateTime bookDate;

    @Enumerated(EnumType.STRING)
    private RepairStatus status;

    @Embedded
    private ItemStatus itemStatus;

    @OneToMany(mappedBy = "repair", cascade = CascadeType.ALL)
    private List<RepairItem> repairItems = new ArrayList<>();

    public void setMember(Member member){
        this.member = member;
        member.getRepairs().add(this);
    }

    public void setRepairman(Repairman repairman){
        this.repairman = repairman;
        repairman.getRepairs().add(this);
    }

    public void addRepairItem(RepairItem repairItem){
        repairItems.add(repairItem);
        repairItem.setRepair(this);
    }

    //수리 요청
    public static Repair createRepair(Member member, Repairman repairman, LocalDateTime bookDate, RepairItem repairItem){
        Repair repair = new Repair();
        repair.setId(repair.getId());
        repair.setMember(member);
        repair.addRepairItem(repairItem);
        repair.setItemStatus(repairItem.getItemStatus());
        repair.setStatus(RepairStatus.INIT);
        repair.setRepairman(repairman);
        repair.setBookDate(bookDate);

        return repair;
    }

    //수리 취소
    public void cancelRepair(){
        this.setStatus(RepairStatus.CANCELED);
    }

    //총 수리 요금
    public int getTotalFee(){
        int totalFee = 0;
        for (RepairItem repairItem : repairItems) {
            totalFee += repairItem.getRepairFee();
        }
        return totalFee;
    }

}
