package org.sid.service;

import java.util.ArrayList;
import java.util.Collection;

import org.sid.dao.AppRoleRepository;
import org.sid.dao.AppUserRepository;
import org.sid.entities.AppRole;
import org.sid.entities.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Transactional;

@Service
//@Transactional
public class ImplAccountService implements AccountService{
	
	@Autowired
	private AppUserRepository appUserRepository;
	@Autowired
	private AppRoleRepository appRoleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public AppUser saveUser(String userName, String password, String confirmedPassword) {
		AppUser user = appUserRepository.findByUsername(userName);
		if(user != null) throw new RuntimeException("Utilisateur existe deja.");
		if(!password.equals(confirmedPassword)) throw new RuntimeException("Verifier votre mot de passe.");
		
		AppUser appUser = new AppUser();
		appUser.setUsername(userName);
		appUser.setPassword(bCryptPasswordEncoder.encode(password));
		appUser.setActived(true);
		appUserRepository.save(appUser);
		addRoleToUser(userName, "USER");
		
		return appUser;
	}

	@Override
	public AppRole save(AppRole role) {
		
		return appRoleRepository.save(role);
	}

	@Override
	public AppUser loadUserByUserName(String userName) {
		
		return appUserRepository.findByUsername(userName);
	}

	@Override
	public void addRoleToUser(String userName, String roleName) {
		AppUser user = appUserRepository.findByUsername(userName);
		AppRole role = appRoleRepository.findByRoleName(roleName);
		//user.getRoles().add(role);
		if (user.getRoles()==null) {
			Collection<AppRole> roles = new ArrayList<AppRole>();
			roles.add(role);
			user.setRoles(roles);
		} else {
			user.getRoles().add(role);
		}
		
		appUserRepository.save(user);
	}
}
