package com.nicolacalise.SportAccountingManager.dao;

import com.nicolacalise.SportAccountingManager.models.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberDAO extends JpaRepository<Member, Integer> {
}
