package com.clonecode.repairweb.domain;

import com.clonecode.repairweb.domain.item.Item;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@Table(name = "repair_item")
public class RepairItem {

    @Id @GeneratedValue
    @Column(name = "repair_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "repair_id")
    private Repair repair;

    private Integer repairFee;

    @Embedded
    private ItemStatus itemStatus;

}
