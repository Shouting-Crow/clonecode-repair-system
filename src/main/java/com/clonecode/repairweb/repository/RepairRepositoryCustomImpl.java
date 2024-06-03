package com.clonecode.repairweb.repository;

import com.clonecode.repairweb.domain.Repair;
import com.clonecode.repairweb.domain.RepairStatus;
import com.clonecode.repairweb.domain.search.RepairSearch;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import java.util.List;

import static com.clonecode.repairweb.domain.QRepair.repair;


@Repository
public class RepairRepositoryCustomImpl implements RepairRepositoryCustom{

    private final JPAQueryFactory queryFactory;

    public RepairRepositoryCustomImpl(EntityManager em){
        this.queryFactory = new JPAQueryFactory(em);
    }


    @Override
    public List<Repair> searchRepairs(RepairSearch repairSearch) {
        return queryFactory.selectFrom(repair)
                .where(memberNameEq(repairSearch.getMemberName()),
                        repairStatusEq(repairSearch.getRepairStatus()))
                .fetch();
    }

    private BooleanExpression memberNameEq(String memberName){
        return StringUtils.hasText(memberName) ? repair.member.name.eq(memberName) : null;
    }

    private BooleanExpression repairStatusEq(RepairStatus repairStatus){
        return repairStatus != null ? repair.status.eq(repairStatus) : null;
    }
}
