package com.mindtree.springboot.ws.configration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mindtree.springboot.ws.common.CommonConstant;
import com.mindtree.springboot.ws.converter.EmployeeListConverter;
import com.mindtree.springboot.ws.converter.EmployeeRQConverter;
import com.mindtree.springboot.ws.converter.EmployeeRSConverter;

@Configuration
public class BeanConfiguration {

	@Bean
	public ConverterFactory getConverterFactory() {
		ConverterFactory factory = new ConverterFactory();
		Map<String, Object> factoryMap = new HashMap<String, Object>();
		factoryMap.put(CommonConstant.EMPLOYEERQ_CONVERTER, getEmployeeRQConverter());
		factoryMap.put(CommonConstant.EMPLOYEERS_CONVERTER, getEmployeeRSConverter());
		factoryMap.put(CommonConstant.EMPLOYEELIST_CONVERTER, getEmployeeListConverter());
		factory.setConverterFactoryMap(factoryMap);
		return factory;
	}
	
	@Bean
	protected EmployeeRQConverter getEmployeeRQConverter() {
		return new EmployeeRQConverter();
	}
	
	@Bean
	protected EmployeeRSConverter getEmployeeRSConverter() {
		return new EmployeeRSConverter();
	}
	
	@Bean
	protected EmployeeListConverter getEmployeeListConverter() {
		EmployeeListConverter empListConverter = new EmployeeListConverter();
		empListConverter.setObjectConverter(getEmployeeRSConverter());
		return empListConverter;
	}
}
