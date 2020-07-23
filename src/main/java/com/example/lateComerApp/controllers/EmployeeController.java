package com.example.lateComerApp.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.lateComerApp.entities.Employee;
import com.example.lateComerApp.exceptions.NotFoundException;
import com.example.lateComerApp.pojo.AddEmployee;
import com.example.lateComerApp.pojo.GenericResponse;
import com.example.lateComerApp.services.EmployeeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.SwaggerDefinition;
import io.swagger.annotations.Tag;

@Api(tags = {"Employee Controller"})
@SwaggerDefinition(tags = {
    @Tag(name = "employee", description = "Handles employee arrival")
})
@RestController
@RequestMapping(path = "api")
public class EmployeeController {
	
	@Autowired
	private EmployeeService employeeService;
	
	
	  @GetMapping("/v1/employee/get_all")
	  public Page<Employee> findAll(@RequestParam int start, @RequestParam int limit) {
		return employeeService.findAll(PageRequest.of(start, limit));
	  }
	  
	  @GetMapping("/v1/employee/search")
	  public Optional<Employee> employeeSearch(@RequestParam String name, @RequestParam String email, 
			  @RequestParam String address) {
		return employeeService.findByEmployeeNameAndEmailAndAddress(name, email, address);
	  }
	  
	  @GetMapping("/v1/employee/sort_by_name")
	  public List<Employee> employeeSortByName() {
		return employeeService.sortEmployees(Sort.by("employeeName"));
	  }
	  
	  @GetMapping("/v1/employee/sort_by_highest_oweing_employee")
	  public List<Employee> employeeSortByHighestBill() {
		return employeeService.sortEmployees(Sort.by("bill").ascending());
	  }
	  
	  @GetMapping("/v1/employee/sort_by_lowest_oweing_employee")
	  public List<Employee> employeeSortByLowestBill() {
		return employeeService.sortEmployees(Sort.by("bill").descending());
	  }
	  
	  @PutMapping("/v1/employee/add")
	  public GenericResponse addEmployee(@RequestBody AddEmployee request) {
		  
		  Employee emp = employeeService.findByEmail(request.getEmail()).orElse(null);
		  
		  SimpleDateFormat parser = new SimpleDateFormat("HH:mm");
		  Date eigth = null;
			try {
				eigth = parser.parse("8:00");
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		  
		  if(emp != null) {
			  emp.setArrivalTime(request.getArrivalTime());
			  
			  Double owe = 0.0;
			  if(request.getArrivalTime().after(eigth)) {
				   owe = Double.valueOf(request.getArrivalTime().getMinutes()) * 0.2;
			  }
			  
			  Double total = owe + emp.getBill();
			  
			  emp.setBill(total);
			  
		  }else {
			  
			  emp = new Employee();
			  emp.setAddress(request.getAddress());
			  emp.setEmployeeName(request.getEmployeeName());
			  emp.setEmail(request.getEmail());
			  emp.setArrivalTime(request.getArrivalTime());
			  
			  Double owe = 0.0;
			  if(request.getArrivalTime().after(eigth)) {
				   owe = Double.valueOf(request.getArrivalTime().getMinutes()) * 0.2;
			  }
			  
			  Double total = owe + emp.getBill();
			  
			  emp.setBill(total);
			  
		  }
		  
		  
		 employeeService.save(emp);
		  
		return new GenericResponse("Employee added successfully");
	  }
	  
	  @PutMapping("/v1/employee/update")
	  public Employee updateEmployee(@RequestBody Employee employee) {
		return employeeService.save(employee);
	  }
	  
	  @DeleteMapping("/v1/employee/delete/{id}")
	  public GenericResponse deleteEmployee(@PathVariable Long id) {
		  
		  Employee employee = employeeService.findById(id).orElseThrow(() -> new NotFoundException(
					"Employee not found"));
		  
		employeeService.delete(employee);
		
		return new GenericResponse("Employee deleted successfully");
	  }
	  
	  
	
}
