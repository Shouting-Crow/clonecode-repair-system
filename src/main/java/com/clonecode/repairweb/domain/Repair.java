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

    private String serialNumber;

}
