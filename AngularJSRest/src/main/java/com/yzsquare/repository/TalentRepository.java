package com.yzsquare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yzsquare.model.Talent;

public interface TalentRepository extends JpaRepository<Talent, Long> {
	Talent findById(Long id);
}
