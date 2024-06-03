package com.clonecode.repairweb.service.item;

import com.clonecode.repairweb.domain.item.Item;
import com.clonecode.repairweb.domain.search.ItemSearch;
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
}
