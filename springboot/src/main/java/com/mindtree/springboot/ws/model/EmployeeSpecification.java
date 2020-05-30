package com.mindtree.springboot.ws.model;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

public class EmployeeSpecification implements Specification<Employee>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -84383702757572730L;
	private Employee filter;
	
	public EmployeeSpecification(Employee filter) {
		this.filter = filter;
	}

	@Override
	public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
		Predicate prd = criteriaBuilder.disjunction();
		
		if (filter.getFirstName() != null) {
			prd.getExpressions().add(criteriaBuilder.equal(root.get("firstName"), filter.getFirstName()));
		}

		if (filter.getLastName() != null) {
			prd.getExpressions().add(criteriaBuilder.equal(root.get("lastName"), filter.getLastName()));
		}
		
		if (filter.getEmail() != null) {
			prd.getExpressions().add(criteriaBuilder.equal(root.get("email"), filter.getEmail()));
		}
		
		return prd;
	}

}
