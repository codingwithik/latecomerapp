package com.example.lateComerApp.repositories;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.example.lateComerApp.entities.Employee;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {
	
	Optional<Employee> findByEmployeeNameAndEmailAndAddress(String name, String email, String address);
	Optional<Employee> findByEmail(String email);
	
}
