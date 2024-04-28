package com.example.cpm.repository;

import com.example.cpm.model.FamilyMembers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FamilyMembersRepository extends JpaRepository<FamilyMembers, Long> {
}
