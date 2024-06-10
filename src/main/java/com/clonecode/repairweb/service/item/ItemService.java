package com.clonecode.repairweb.service.item;

import com.clonecode.repairweb.domain.item.Item;
import com.clonecode.repairweb.domain.search.ItemSearch;
import com.clonecode.repairweb.form.item.ItemRegisterForm;

import java.util.List;

public interface ItemService {

    Item save(Item item);
    void saveItem(ItemRegisterForm form);
    Item findOne(Long id);
    List<Item> findAll();

    Item searchItem(ItemSearch itemSearch);
    void updateItem(Long id, ItemRegisterForm form);
    ItemRegisterForm convertToForm(Item item);
    void deleteItem(Long id);
}
