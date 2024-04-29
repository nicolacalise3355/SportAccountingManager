package com.nicolacalise.SportAccountingManager.dao;

import com.nicolacalise.SportAccountingManager.models.entities.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceDAO extends JpaRepository<Attendance, Integer> {
}
