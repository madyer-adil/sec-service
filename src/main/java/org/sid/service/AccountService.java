package org.sid.service;

import org.sid.entities.AppRole;
import org.sid.entities.AppUser;

public interface AccountService {
	
	public AppUser saveUser(String userName,String password,String confirmedPassword);
	public AppRole save(AppRole role);
	public AppUser loadUserByUserName(String userName);
	public void addRoleToUser(String userName,String roleName);
}
