package com.clonecode.repairweb.repository;

import com.clonecode.repairweb.domain.login.Repairman;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface RepairmanRepository extends JpaRepository<Repairman, Long> {
    Optional<Repairman> findByLoginId(String loginId);

    List<Repairman> findByName(String name);

    List<Repairman> findByRegion(String region);
}
