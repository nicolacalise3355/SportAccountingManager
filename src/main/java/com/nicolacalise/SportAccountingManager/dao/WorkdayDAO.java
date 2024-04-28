package com.nicolacalise.SportAccountingManager.dao;

import com.nicolacalise.SportAccountingManager.models.entities.Workday;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkdayDAO extends JpaRepository<Workday, Integer> {
}
