package com.mindtree.springboot.ws.service;

import java.util.Collection;

import com.mindtree.springboot.ws.model.Employee;

public interface EmployeeService {

	Collection<Employee> getAllEmployees();
	
	Collection<Employee> searchEmployees(Employee filter);
	
	Employee getEmployeeById(Long id);
	
	Employee saveEmployee(Employee emp);
	
	Employee updateEmployee(Employee emp);
	
	void delete(Long id);
	
	void evictCache();
}
