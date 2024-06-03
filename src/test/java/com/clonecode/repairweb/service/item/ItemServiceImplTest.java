package com.clonecode.repairweb.service.item;

import com.clonecode.repairweb.domain.item.AirConditioner;
import com.clonecode.repairweb.domain.item.Item;
import com.clonecode.repairweb.domain.search.ItemSearch;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ItemServiceImplTest {

    @Autowired
    private ItemService itemService;

    @Test
    void searchItemTest(){
        AirConditioner airConditioner = new AirConditioner();
        airConditioner.setName("LG 에어컨 1");
        airConditioner.setSerialNumber("AC133466IWZ");

        Item savedItem = itemService.save(airConditioner);

        ItemSearch itemSearch1 = new ItemSearch();
        itemSearch1.setSerialNumber("AC111111");
        Item searchItem1 = itemService.searchItem(itemSearch1);

        ItemSearch itemSearch2 = new ItemSearch();
        itemSearch2.setSerialNumber("AC133466IWZ");
        Item searchItem2 = itemService.searchItem(itemSearch2);

        assertThat(searchItem1).isNull();
        assertThat(searchItem2.getName()).isEqualTo("LG 에어컨 1");

    }

}