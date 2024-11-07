package com.fca.calidad.service;

import com.fca.calidad.dao.IDAOUser;
import com.fca.calidad.model.User;

public class LoginService {
	IDAOUser dao;

	public LoginService(IDAOUser d) {
		dao = d;
	}
	
	public boolean login(String name, String pass) { //metodo login
		
		User u = dao.findByUserName(name);
		if (u != null) {
			if (u.getPassword() == pass) {
				
				return true;
			}
			else {
				
				return false;
			}
		}
		else {
			return false;
		}
	}

}
