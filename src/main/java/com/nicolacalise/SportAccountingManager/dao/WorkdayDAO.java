package com.nicolacalise.SportAccountingManager.dao;

import com.nicolacalise.SportAccountingManager.models.entities.Workday;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WorkdayDAO extends JpaRepository<Workday, Integer> {
}
