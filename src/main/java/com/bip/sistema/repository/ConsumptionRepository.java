package com.bip.sistema.repository;

import com.bip.sistema.model.ConsumptionRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsumptionRepository extends JpaRepository<ConsumptionRecord, Long> {

}
