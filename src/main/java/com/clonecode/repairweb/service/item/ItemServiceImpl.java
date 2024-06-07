package com.clonecode.repairweb.service.item;

import com.clonecode.repairweb.domain.item.AirConditioner;
import com.clonecode.repairweb.domain.item.Cleaner;
import com.clonecode.repairweb.domain.item.Item;
import com.clonecode.repairweb.domain.item.Tv;
import com.clonecode.repairweb.domain.search.ItemSearch;
import com.clonecode.repairweb.form.item.ItemRegisterForm;
import com.clonecode.repairweb.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ItemServiceImpl implements ItemService{

    private final ItemRepository itemRepository;

    //For Admin
    @Override
    @Transactional
    public Item save(Item item) {
        return itemRepository.save(item);
    }

    @Override
    public Item findOne(Long id) {
        Optional<Item> findItem = itemRepository.findById(id);
        return findItem.orElse(null);
    }

    @Override
    public List<Item> findAll() {
        return itemRepository.findAll();
    }

    @Override
    public Item searchItem(ItemSearch itemSearch) {
        return itemRepository.searchItem(itemSearch);
    }

    @Override
    @Transactional
    public void saveItem(ItemRegisterForm form) {
        Item item = createItem(form);
        itemRepository.save(item);
    }

    @Override
    @Transactional
    public void updateItem(Long id, ItemRegisterForm form) {
        Item item = itemRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("정확하지 않은 제품 ID 오류"));

        updateItemDetails(item, form);
        itemRepository.save(item);
    }

    @Override
    public ItemRegisterForm convertToForm(Item item) {
        ItemRegisterForm form = new ItemRegisterForm();
        form.setId(item.getId());
        form.setName(item.getName());
        form.setSerialNumber(item.getSerialNumber());
        form.setRepairFee(item.getRepairFee());
        form.setItemType(item.getItemType());
        return form;
    }

    @Override
    @Transactional
    public void deleteItem(Long id) {
        if (itemRepository.existsById(id)){
            itemRepository.deleteById(id);
        } else {
            throw new IllegalArgumentException("올바르지 않은 ID 값 오류");
        }
    }

    private Item createItem(ItemRegisterForm form){
        Item item;
        switch (form.getItemType()){
            case AIR_CONDITIONER -> item = new AirConditioner();
            case CLEANER -> item = new Cleaner();
            case TV -> item = new Tv();
            default -> throw new IllegalStateException("알 수 없는 제품 종류 입니다.");
        }
        updateItemDetails(item, form);
        return item;
    }

    private void updateItemDetails(Item item, ItemRegisterForm form){
        item.setName(form.getName());
        item.setSerialNumber(form.getSerialNumber());
        item.setRepairFee(form.getRepairFee());
        item.setItemType(form.getItemType());
    }
}
