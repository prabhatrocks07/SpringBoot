package com.mindtree.springboot.ws.converter;

import com.mindtree.springboot.ws.model.Employee;
import com.mindtree.springboot.ws.model.response.EmployeeRS;

public class EmployeeRSConverter implements ObjectConverter {

	@Override
	public <T, V> void convert(T t, V v) {
		Employee emp = (Employee) t;
		EmployeeRS empRS = (EmployeeRS) v;
		
		if(emp != null) {
			empRS.setEmpId(emp.getEmpId());
			empRS.setFirstName(emp.getFirstName());
			empRS.setLastName(emp.getLastName());
			empRS.setEmail(emp.getEmail());
			empRS.setAge(emp.getAge());
			empRS.setDeptID(emp.getDeptID());
			empRS.setSalary(emp.getSalary());
		}
	}

}
