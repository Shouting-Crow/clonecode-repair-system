package com.clonecode.repairweb.repository;

import com.clonecode.repairweb.domain.item.Item;
import com.clonecode.repairweb.domain.search.ItemSearch;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import org.springframework.util.StringUtils;

import static com.clonecode.repairweb.domain.item.QItem.item;

public class ItemRepositoryCustomImpl implements ItemRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public ItemRepositoryCustomImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Item searchItem(ItemSearch itemSearch) {
        return queryFactory.selectFrom(item)
                .where(serialNumberEq(itemSearch.getSerialNumber()))
                .fetchOne();
    }

    private BooleanExpression serialNumberEq(String serialNumber){
        return StringUtils.hasText(serialNumber) ? item.serialNumber.eq(serialNumber) : null;
    }
}
