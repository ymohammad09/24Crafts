package com.yzsquare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yzsquare.model.Audition;

public interface AuditionRepository extends JpaRepository<Audition, Long> {
	Audition findById(Long id);
}
