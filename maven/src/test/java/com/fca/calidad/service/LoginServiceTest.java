package com.fca.calidad.service;



import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;
import org.junit.jupiter.api.Test;

import com.fca.calidad.dao.IDAOUser;
import com.fca.calidad.model.User;

class LoginServiceTest {
	
	

	LoginService service;
	IDAOUser dao;
	@Test
	void LoginServicetest() {
		//inicializacion
		dao = mock(IDAOUser.class); //IDAOUser es la clase a la que se va a reemplazar (dao al ser un mock esta vacio)
		
		service = new LoginService(dao); //instancia de la clase a probar
		User usuario= new User("nombre1","email@email.com", "12345");//crear usuario
		when(dao.findByUserName("nombre1")).thenReturn(usuario);
		
		//Ejercicio (llamada)
		boolean result= service.login("nombre1", "12345");
		
		//verificacion
		assertThat(result, is(true));
	
	}
	@Test
	void LoginNoExisteUsuariotest() {
		//inicializacion
		dao = mock(IDAOUser.class); //IDAOUser es la clase a la que se va a reemplazar (dao al ser un mock esta vacio)
		
		service = new LoginService(dao); //instancia de la clase a probar
		//User usuario= new User("nombre1","email@email.com", "12345");//crear usuario
		when(dao.findByUserName("nombre1")).thenReturn(null);
		
		//Ejercicio (llamada)
		boolean result= service.login("nombre1", "12345");
		
		//verificacion
		assertThat(result, is(false));
	
	}
	

}
