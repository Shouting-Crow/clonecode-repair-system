package com.clonecode.repairweb.domain;

import com.clonecode.repairweb.domain.item.AirConditioner;
import com.clonecode.repairweb.domain.item.Cleaner;
import com.clonecode.repairweb.domain.item.Item;
import com.clonecode.repairweb.domain.item.Tv;
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

    private int repairFee;

    @Embedded
    private ItemStatus itemStatus;

    //수리 제품 생성
    public static RepairItem createRepairItem(Item item, int repairFee){
        RepairItem repairItem = new RepairItem();
        repairItem.setItem(item);
        repairItem.setRepairFee(repairFee);

        if (item instanceof AirConditioner){
            AirConditioner airConditioner = new AirConditioner();
            repairItem.setItemStatus(airConditioner.getAirConditionerStatus());
        }
        if (item instanceof Tv){
            Tv tv = new Tv();
            repairItem.setItemStatus(tv.getTvStatus());
        }
        if (item instanceof Cleaner){
            Cleaner cleaner = new Cleaner();
            repairItem.setItemStatus(cleaner.getCleanerStatus());
        }

        return repairItem;
    }

}
