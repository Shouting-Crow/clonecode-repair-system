package com.clonecode.repairweb.repository;

import com.clonecode.repairweb.domain.item.AirConditioner;
import com.clonecode.repairweb.domain.item.Item;
import com.clonecode.repairweb.service.item.ItemService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class ItemTest {

    @Autowired
    ItemService itemService;

    @Test
    void saveItemTest(){

        AirConditioner airConditioner = new AirConditioner();
        airConditioner.setName("LG 에어컨 1");
        airConditioner.setSerialNumber("AC133466IWZ");

        Item savedItem = itemService.save(airConditioner);
        Item foundItem = itemService.findOne(savedItem.getId());

        System.out.println(airConditioner.getAirConditionerStatus().getStatusArr());
        System.out.println(airConditioner.getSpec().getSpecArr());

        assertThat(savedItem.getId()).isEqualTo(foundItem.getId());
        assertThat(savedItem.getName()).isEqualTo(foundItem.getName());
        assertThat(savedItem.getSerialNumber()).isEqualTo(foundItem.getSerialNumber());

    }
}
