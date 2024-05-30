package com.clonecode.repairweb.repository;

import com.clonecode.repairweb.domain.ItemSearch;
import com.clonecode.repairweb.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {
    List<Item> findAll(ItemSearch itemSearch);
}
