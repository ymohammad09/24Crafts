package com.yzsquare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yzsquare.model.Craft;

public interface CraftRepository extends JpaRepository<Craft, Long> {
	Craft findById(Long id);
}
