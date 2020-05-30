package com.mindtree.springboot.ws.model.request;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EmployeeRQ implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 450685268937148583L;
	private Long empId;
	
	@Size(max=18, message = "Length of firstName can not be more than 18")
	private String firstName;
	
	@NotNull(message = "Last name is mandatory")
	@NotBlank(message = "Last name can not be blank")
	@Size(max=32, message = "Length of firstName can not be more than 32")
	private String lastName;
	
	@NotEmpty(message = "Email cannot be null")
	@Email
	@Size(max=50, message = "Length of firstName can not be more than 32")
	private String email; 
	private int age;
	private int deptID;
	private Long salary;


	public Long getEmpId() {
		return empId;
	}

	public void setEmpId(Long empId) {
		this.empId = empId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getDeptID() {
		return deptID;
	}

	public void setDeptID(int deptID) {
		this.deptID = deptID;
	}

	public Long getSalary() {
		return salary;
	}

	public void setSalary(Long salary) {
		this.salary = salary;
	}

}
