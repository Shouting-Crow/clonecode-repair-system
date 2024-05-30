package com.clonecode.repairweb.service.item;

import com.clonecode.repairweb.domain.item.Item;

import java.util.List;

public interface ItemService {

    Item save(Item item);
    Item findOne(Long id);
    List<Item> findAll();
}
