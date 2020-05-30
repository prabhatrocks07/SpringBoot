package com.mindtree.springboot.ws.converter;

import java.util.ArrayList;
import java.util.Collection;

import com.mindtree.springboot.ws.model.Employee;
import com.mindtree.springboot.ws.model.response.EmployeeRS;

public class EmployeeListConverter extends AbstractListConverter<Employee> {

	@Override
	public Collection<EmployeeRS> convert(Collection<Employee> empList) {
		final Collection<EmployeeRS> empRSList = new ArrayList<EmployeeRS>();
		
		if(empList != null) {
			empList.forEach(emp -> {
				EmployeeRS empRS = new EmployeeRS();
				objectConverter.convert(emp, empRS);
				empRSList.add(empRS);
			});
		}
		
		return empRSList;
	}

}
