package com.fca.calidad.service;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import java.util.HashMap;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

import com.fca.calidad.dao.IDAOUser;
import com.fca.calidad.model.User;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.MatcherAssert.assertThat; 

class UserServiceTest {
	private User usuario;
	private UserService servicio;
	private IDAOUser dao;
	private HashMap<Integer,User> baseDatos;
	
	@BeforeEach
	void setup() {
		dao= mock(IDAOUser.class);
		servicio= new UserService(dao);
		baseDatos= new HashMap <Integer,User>();
	}


	@Test
	void updateTest() {
		//INICIALIZACION
		User usuarioViejo = new User("nombre2" , "email" , "password");
		usuarioViejo.setId(1);
		baseDatos.put(usuarioViejo.getId(), usuarioViejo);
		
		User usuarioNuevo = new User("nuevoNombre" , "email" , "nuevoPassword");
		usuarioNuevo.setId(1);
		
		when(dao.findById(1)).thenReturn(usuarioViejo);
		//when(dao.updateUser(usuarioNuevo)).thenReturn(usuarioNuevo);
		when(dao.updateUser(any(User.class))).thenAnswer(new Answer <User>() {
			//Method within the class
			public User answer(InvocationOnMock invocation) throws Throwable{
				//Set behavior in every invocation
				User arg =(User) invocation.getArguments()[0];
				baseDatos.replace(arg.getId(), arg);
				
				//Return the invoked value
				return baseDatos.get(arg.getId());
			}
		}
				);
		
		//EJERCICIO
		User result = servicio.updateUser(usuarioNuevo);
		
		//VERIFICAR
		assertThat("nuevoPassword",is(result.getPassword()));
		assertThat("nuevoNombre",is(result.getName()));
	}
	//prueba12 

}
