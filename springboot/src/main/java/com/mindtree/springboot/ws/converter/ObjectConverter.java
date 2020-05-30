package com.mindtree.springboot.ws.converter;

@FunctionalInterface
public interface ObjectConverter {

	<T,V> void convert(T t, V v);
}
