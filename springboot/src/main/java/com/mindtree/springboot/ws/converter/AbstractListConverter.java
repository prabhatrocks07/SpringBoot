package com.mindtree.springboot.ws.converter;

import java.util.Collection;

public abstract class AbstractListConverter<E> {

	protected ObjectConverter objectConverter;
	
	public abstract Collection<?> convert(Collection<E> sourceList);

	public ObjectConverter getObjectConverter() {
		return objectConverter;
	}

	public void setObjectConverter(ObjectConverter objectConverter) {
		this.objectConverter = objectConverter;
	}
	
	
}
