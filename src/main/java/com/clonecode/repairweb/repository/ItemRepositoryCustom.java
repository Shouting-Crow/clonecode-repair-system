package com.clonecode.repairweb.repository;

import com.clonecode.repairweb.domain.item.Item;
import com.clonecode.repairweb.domain.search.ItemSearch;

public interface ItemRepositoryCustom {
    Item searchItem(ItemSearch itemSearch);
}
