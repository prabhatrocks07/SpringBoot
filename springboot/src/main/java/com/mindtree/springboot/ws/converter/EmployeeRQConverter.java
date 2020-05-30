package com.mindtree.springboot.ws.converter;

import com.mindtree.springboot.ws.model.Employee;
import com.mindtree.springboot.ws.model.request.EmployeeRQ;

public class EmployeeRQConverter implements ObjectConverter{

	@Override
	public <T, V> void convert(T t, V v) {
		EmployeeRQ empRQ = (EmployeeRQ) t;
		Employee entity = (Employee) v;
		
		entity.setEmpId(empRQ.getEmpId());
		entity.setFirstName(empRQ.getFirstName());
		entity.setLastName(empRQ.getLastName());
		entity.setEmail(empRQ.getEmail());
		entity.setAge(empRQ.getAge());
		entity.setDeptID(empRQ.getDeptID());
		entity.setSalary(empRQ.getSalary());
		
	}

}
