package com.mylearning.springboot.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.mylearning.springboot.entity.UserRole;

@Repository
@Transactional
public class AppRoleDAO {

	@Autowired
	private EntityManager entityManager;
	
	@SuppressWarnings("unchecked")
	public List<String> getRoleNames(Long userId) {
		String sql = "SELECT ur.appRole.roleName from " + UserRole.class.getName() + " ur WHERE ur.appUser.userId = :userId" ;
		
		Query query = this.entityManager.createQuery(sql, String.class);
		query.setParameter("userId", userId);
		
		return query.getResultList();
	}
}
