package com.clonecode.repairweb.repository;

import com.clonecode.repairweb.domain.Repair;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RepairRepository extends JpaRepository<Repair, Long>, RepairRepositoryCustom {
    List<Repair> findByMemberId(Long memberId);
}
