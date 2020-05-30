package com.mylearning.springboot.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.mylearning.springboot.dao.AppRoleDAO;
import com.mylearning.springboot.dao.AppUserDAO;
import com.mylearning.springboot.entity.AppUser;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private AppUserDAO appUserDAO;

	@Autowired
	private AppRoleDAO appRoleDAO;
	
	@Override
	public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
		AppUser appUser = this.appUserDAO.findUserAccount(userName);
		
		if(appUser == null) {
			System.out.println("User not found! " + userName);
			throw new UsernameNotFoundException("User " + userName + " was not found in the database");
		}
		
		System.out.println("Found User: " + appUser);
		
		//[ROLE_USER, ROLE_ADMIN,..]
		List<String> roleNames = this.appRoleDAO.getRoleNames(appUser.getUserId());
		
		List<GrantedAuthority> grantList = new ArrayList<>();
		if(roleNames != null) {
			for (String role : roleNames) {
				//ROLE_USER, ROLE_ADMIN,..
				GrantedAuthority authority = new SimpleGrantedAuthority(role);
				grantList.add(authority);
			}
		}
		
		UserDetails userDetails = new User(appUser.getUserName(), appUser.getEncryptedPassword(), grantList);
		return userDetails;
	}

}
