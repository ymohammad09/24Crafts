package com.yzsquare.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yzsquare.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
	Employee findByName(String name);
}
