/**
 * This is Employee service class 
 */
package com.mindtree.springboot.ws.service;

import java.util.Collection;
import java.util.Optional;

import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.springboot.ws.model.Employee;
import com.mindtree.springboot.ws.model.EmployeeSpecification;
import com.mindtree.springboot.ws.repository.EmployeeRepository;

/**
 * @author prabhat
 *
 */
@Service
@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
public class EmployeeServiceBean implements EmployeeService {
	
	private static transient Logger logger = LoggerFactory.getLogger(EmployeeServiceBean.class); 

	@Autowired
	private EmployeeRepository empRepository;
	
	
	@Override
	public Collection<Employee> getAllEmployees() {
		logger.info("<EmployeeServiceBean> - getAllEmployees()");
		return empRepository.findAll();
	}
	
	@Override
	public Collection<Employee> searchEmployees(Employee filter) {
		logger.info("<EmployeeServiceBean> - searchEmployees()");
		Specification<Employee> spec = new EmployeeSpecification(filter);
		return empRepository.findAll(spec);
	}

	@Override
	@Cacheable(value = "employees", key = "#id")
	public Employee getEmployeeById(Long id) {
		logger.info("<EmployeeServiceBean> - getEmployeeById()");
		Optional<Employee> emp = empRepository.findById(id);
		
		if(emp.isPresent()) {
			return emp.get();
		} else {
			
			return null;
		}
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@CachePut(value = "employees", key = "#result.empId")
	public Employee saveEmployee(Employee emp) {
		logger.info("<EmployeeServiceBean> - saveEmployee()");
		//Can not create with specified ID value
		if(emp.getEmpId() != null) {
			logger.info("<EmployeeServiceBean> - saveEmployee() - Employee is already persisted!");
			throw new EntityExistsException("Employee is already persisted");
		}
		
		return empRepository.save(emp);
	}

	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@CachePut(value = "employees", key = "#emp.empId")
	public Employee updateEmployee(Employee emp) {
		logger.info("<EmployeeServiceBean> - updateEmployee()");
		if(emp.getEmpId() == null) {
			throw new RuntimeException("Request is invalid");
		}
		Optional<Employee> empPersisted = empRepository.findById(emp.getEmpId());
		
		//Can not update employee that haven't been persisted
		if(empPersisted.isPresent()) {
			return empRepository.save(emp);
		} else {
			logger.info("<EmployeeServiceBean> - updateEmployee() - can't perform update on non-persisted employee");
			throw new NoResultException("Employee doesn't exist in record");
		}
		
	}

	
	@Override
	@Transactional(propagation = Propagation.REQUIRED, readOnly = false)
	@CacheEvict(value = "employees", key = "#id")
	public void delete(Long id) {
		logger.info("<EmployeeServiceBean> - delete()");
		empRepository.deleteById(id);
	}

	@Override
	@CacheEvict(value = "employees", allEntries = true)
	public void evictCache() {
		
	}

}
