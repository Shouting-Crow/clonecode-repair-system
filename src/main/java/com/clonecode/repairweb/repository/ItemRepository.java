package com.clonecode.repairweb.repository;

import com.clonecode.repairweb.domain.item.Item;
import com.clonecode.repairweb.domain.item.ItemType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long>, ItemRepositoryCustom {
    List<Item> findByItemType(ItemType type);
}
