package com.mindtree.springboot.ws.configration;
/**
 * This class contains a HashMap to store all the converter class
 */
import java.util.Map;

public class ConverterFactory {

	private Map<String, Object> converterFactoryMap;

	public Map<String, Object> getConverterFactoryMap() {
		return converterFactoryMap;
	}

	public void setConverterFactoryMap(Map<String, Object> converterFactoryMap) {
		this.converterFactoryMap = converterFactoryMap;
	}
	
	public Object getConverter(final String name) { 
		return converterFactoryMap.get(name);
	}
}
