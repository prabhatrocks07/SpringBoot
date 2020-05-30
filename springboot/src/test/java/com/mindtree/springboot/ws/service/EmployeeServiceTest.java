package com.mindtree.springboot.ws.service;

import java.util.Collection;

import javax.persistence.EntityExistsException;
import javax.persistence.NoResultException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.mindtree.springboot.ws.AbstractTest;
import com.mindtree.springboot.ws.model.Employee;


@Transactional
public class EmployeeServiceTest extends AbstractTest {

	@Autowired
	private EmployeeService empService;
	
	@Before
	public void setUp() {
		empService.evictCache();
	}
	
	@After
	public void tearDown() {
		
	}
	
	@Test
	public void testGetAllEmployees() {
		
		Collection<Employee> list = empService.getAllEmployees();
		Assert.assertNotNull("failure - expected not null", list);
		Assert.assertEquals("failure - expected size ", 5, list.size());
	}
	
	@Test
	public void testGetEmployeeById() {
		
		Long id = new Long(1);
		
		Employee emp = empService.getEmployeeById(id);
		Assert.assertNotNull("failure - expected not null", emp);
		Assert.assertEquals("failure - expected id attribute match", id, emp.getEmpId());
	}
	
	@Test
	public void testFindOneNotFound() {
		
		Long id = Long.MAX_VALUE;
		Employee entity  = null;
		
		entity = empService.getEmployeeById(id);
		
		Assert.assertNull("failure - expected null", entity);
	}
	
	@Test
	public void testSaveEmployee() {
		
		Employee emp = new Employee("James", "Bond", "james@yahho.com", 49, 3, new Long(87000));
		Employee savedEmployee = empService.saveEmployee(emp);
		
		Assert.assertNotNull("failure - expected not null", savedEmployee);
		Assert.assertNotNull("failure - expected id attribute not null", savedEmployee.getEmpId());
		Assert.assertEquals("failure - expected id attribute match", "James", savedEmployee.getFirstName());
		
		Collection<Employee> list = empService.getAllEmployees();
		Assert.assertEquals("failure - expected size ", 6, list.size());
	}
	
	@Test
	public void testSaveEmployeeWithId() {
		
		Employee emp = new Employee("James", "Bond", "james@yahho.com", 49, 3, new Long(87000));
		emp.setEmpId(1L);
		Exception ex = null;
		
		try {
			empService.saveEmployee(emp);
		} catch (Exception e) {
			ex = e;
		}
		
		Assert.assertNotNull("failure - expected not exception", ex);
		Assert.assertTrue("failure - expected EntityExistenceException", ex instanceof EntityExistsException);
		
	}
	
	@Test
	public void testUpdateEmployee() {
		
		Long id = new Long(1);
		Employee emp = empService.getEmployeeById(id);
		
		Assert.assertNotNull("failure - expected not null", emp);
		String lastName = "Janniffer";
		emp.setLastName(lastName);
		
		Employee updatedEmp = empService.updateEmployee(emp);
		Assert.assertNotNull("failure - expected updated entity not null", updatedEmp);
		Assert.assertEquals("failure - expected updated entity id attribute unchanged", id, updatedEmp.getEmpId());
		Assert.assertEquals("failure - expected updated entity lastName attribute match", lastName, updatedEmp.getLastName());
		
	}
	
	@Test
	public void testUpdateNotFound() {
		
		Exception ex = null;
		
		Employee emp = new Employee("James", "Bond", "james@yahho.com", 49, 3, new Long(87000));
		emp.setEmpId(Long.MAX_VALUE);
		
		try {
			empService.updateEmployee(emp);
		} catch (Exception e) {
			ex = e;
		}
		
		Assert.assertNotNull("failure - expected not exception", ex);
		Assert.assertTrue("failure - expected EntityExistenceException", ex instanceof NoResultException);
		
	}
	
	@Test
	public void testDelete() {
		
		Long id = new Long(1);
		Employee emp = empService.getEmployeeById(id);
		
		Assert.assertNotNull("failure - expected not null", emp);
		
		empService.delete(id);
		
		Collection<Employee> list = empService.getAllEmployees();
		Assert.assertEquals("failure - expected size ", 4, list.size());
		
		Employee deletedEmp = empService.getEmployeeById(id);
		Assert.assertNull("failure - expected entity to be deleted", deletedEmp);
	}
}
