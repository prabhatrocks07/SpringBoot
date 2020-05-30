package com.mindtree.springboot.ws.web.api;

/**
 * This is API controller class exposing different end points for GET, POST, PUT & DELETE operations
 */
import java.util.Collection;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mindtree.springboot.ws.common.CommonConstant;
import com.mindtree.springboot.ws.configration.ConverterFactory;
import com.mindtree.springboot.ws.converter.AbstractListConverter;
import com.mindtree.springboot.ws.converter.ObjectConverter;
import com.mindtree.springboot.ws.model.Employee;
import com.mindtree.springboot.ws.model.request.EmployeeRQ;
import com.mindtree.springboot.ws.model.response.EmployeeRS;
import com.mindtree.springboot.ws.service.EmployeeService;

@RestController
@RequestMapping(value = "/employees")
public class EmployeeController extends BaseController {
	
	
	@Autowired
	private EmployeeService empService;
	
	@Autowired
	private ConverterFactory factory;
  
	/**
	 * This method supports GET operation and responsible for fetching list of employees
	 * @return Collection of employees
	 */
	@RequestMapping(method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<EmployeeRS>> getEmployees() {
		
		logger.info("<EmployeeController> - getEmployees() - start");
		Collection<Employee> empList = empService.getAllEmployees();
		
		Collection<EmployeeRS> empRSList = ((AbstractListConverter) factory.getConverter(CommonConstant.EMPLOYEELIST_CONVERTER)).convert(empList);
		
		logger.info("<EmployeeController> - getEmployees() - end");
		
		return new ResponseEntity<Collection<EmployeeRS>>(empRSList, HttpStatus.OK);
	}
	
	/**
	 * This method supports GET operation and responsible to get an employee based on employee ID
	 * @return employee
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmployeeRS> getEmployee(@PathVariable Long id) {
		
		logger.info("<EmployeeController> - getEmployee() - start");
		Employee employee = empService.getEmployeeById(id);
		EmployeeRS employeeRS = new EmployeeRS();
		
		((ObjectConverter) factory.getConverter(CommonConstant.EMPLOYEERS_CONVERTER)).convert(employee, employeeRS);
		employeeRS.setErrorCode(-1);
		employeeRS.setErrorMessage(CommonConstant.SUCCESS);
		
		if(employee == null) {
			logger.info("<EmployeeController> - getEmployee() : Employee not found for empID: " + id);
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		
		logger.info("<EmployeeController> - getEmployee() - end");
		
		return new ResponseEntity<EmployeeRS>(employeeRS, HttpStatus.OK);
		
	}
	
	/**
	 * This method supports POST operation and responsible to search employee based on First Name, Last Name or Email search criteria
	 * @return employee
	 */
	@RequestMapping(value="/search", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Collection<EmployeeRS>> searchEmployee(@RequestBody EmployeeRQ empRQ) {
		
		logger.info("<EmployeeController> - searchEmployee() - start");
		Employee emp = new Employee();
		((ObjectConverter) factory.getConverter(CommonConstant.EMPLOYEERQ_CONVERTER)).convert(empRQ, emp);
		Collection<Employee> empList = empService.searchEmployees(emp);
		
		Collection<EmployeeRS> empRSList = ((AbstractListConverter) factory.getConverter(CommonConstant.EMPLOYEELIST_CONVERTER)).convert(empList);
		
		logger.info("<EmployeeController> - searchEmployee() - end");
		
		return new ResponseEntity<Collection<EmployeeRS>>(empRSList, HttpStatus.CREATED);
		
	}
	
	/**
	 * This method supports POST operation and responsible to create an employee based on employee ID
	 * @return employee
	 */
	@RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmployeeRS> saveEmployee(@Valid @RequestBody EmployeeRQ empRQ) {
		
		logger.info("<EmployeeController> - createEmployee() - start");
		Employee emp = new Employee();
		((ObjectConverter) factory.getConverter(CommonConstant.EMPLOYEERQ_CONVERTER)).convert(empRQ, emp);
		Employee employee = empService.saveEmployee(emp);
		
		EmployeeRS employeeRS = new EmployeeRS();
		((ObjectConverter) factory.getConverter(CommonConstant.EMPLOYEERS_CONVERTER)).convert(employee, employeeRS);
		employeeRS.setErrorCode(-1);
		employeeRS.setErrorMessage(CommonConstant.SUCCESS);
		
		logger.info("<EmployeeController> - createEmployee() - end");
		
		return new ResponseEntity<EmployeeRS>(employeeRS, HttpStatus.CREATED);
		
	}
	
	/**
	 * This method supports PUT operation and responsible to update an employee based on employee ID
	 * @return employee
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.PUT, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<EmployeeRS> updateEmployee(@Valid @RequestBody EmployeeRQ empRQ) {
		
		logger.info("<EmployeeController> - updateEmployee() - start");
		Employee emp = new Employee();
		((ObjectConverter) factory.getConverter(CommonConstant.EMPLOYEERQ_CONVERTER)).convert(empRQ, emp);
		Employee updatedEmp = empService.updateEmployee(emp);
		
		if(updatedEmp == null) {
			logger.error("<Employee Controller> - updateEmployee() - Update failed for employee: " + emp.getEmpId());
			return new ResponseEntity<EmployeeRS>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		EmployeeRS employeeRS = new EmployeeRS();
		((ObjectConverter) factory.getConverter(CommonConstant.EMPLOYEERS_CONVERTER)).convert(updatedEmp, employeeRS);
		employeeRS.setErrorCode(-1);
		employeeRS.setErrorMessage(CommonConstant.SUCCESS);
		
		logger.info("<EmployeeController> - updateEmployee() - end");
		
		return new ResponseEntity<EmployeeRS>(employeeRS, HttpStatus.OK);
		
	}
	
	/**
	 * This method supports DELETE operation and responsible to delete an employee based on employee ID
	 * @return employee
	 */
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<EmployeeRS> deleteEmployee(@PathVariable long id) {
		
		logger.info("<EmployeeController> - deleteEmployee() - start");
		
		empService.delete(id);
		
		logger.info("<EmployeeController> - deleteEmployee() - end");
		
		return new ResponseEntity<EmployeeRS>(HttpStatus.NO_CONTENT);
		
	}
}
