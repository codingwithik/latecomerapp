package com.example.lateComerApp.services;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.lateComerApp.entities.Employee;
import com.example.lateComerApp.repositories.EmployeeRepository;

@Service
public class EmployeeService {
	
	private EmployeeRepository employeeRepository;

	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public Optional<Employee> findById(Long id) {
		return employeeRepository.findById(id);
	}

		
	public Optional<Employee> findByEmail(String email) {
		return employeeRepository.findByEmail(email);
	}

	
	public List<Employee> findAll() {
		return (List<Employee>) employeeRepository.findAll();
	}
	
	public Page<Employee> findAll(Pageable pageable) {
		return (Page<Employee>) employeeRepository.findAll(pageable);
	}
	
	public List<Employee> sortEmployees(Sort sort) {
		return (List<Employee>) employeeRepository.findAll(sort);
	}
	
	
	
	public Optional<Employee> findByEmployeeNameAndEmailAndAddress(String name, String email, String address){
		return employeeRepository.findByEmployeeNameAndEmailAndAddress(name, email, address);
	}

	
	public void deleteById(Long id) {
		employeeRepository.deleteById(id);
	}

	public void delete(Employee employee) {
		employeeRepository.delete(employee);
	}

	public Employee save(Employee employee) {
		return employeeRepository.save(employee);
	}

	public void saveAll(List<Employee> employees) {
		employeeRepository.saveAll(employees);
	}

	public boolean existsById(Long id) {
		return employeeRepository.existsById(id);
	}

	public long count() {
		return employeeRepository.count();
	}

	public void deleteAll(List<Employee> employees) {
		employeeRepository.deleteAll(employees);
	}

	public void deleteAll() {
		employeeRepository.deleteAll();
	}


}
