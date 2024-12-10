package com.fca.calidad.service;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.any;
import java.util.HashMap;
import java.util.List;

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
	@Test
	 void findUserByEmailTest() {
	     // Preparar datos 
	     User usuarioExistente = new User("nombreExistente", "emailExistente@gmail.com", "contra123");
	     usuarioExistente.setId(1);
	     baseDatos.put(usuarioExistente.getId(), usuarioExistente);

	     // DAO
	     when(dao.findUserByEmail("emailExistente@gmail.com")).thenReturn(usuarioExistente);
	     when(dao.findUserByEmail("emailNoExistente@gmail.com")).thenReturn(null);  // Simular un email no registrado

	     // Llamar método de UserService
	     User result = servicio.findUserByEmail("emailExistente@gmail.com");

	     // Verificar 
	     assertThat(result, is(notNullValue()));
	     assertThat(result.getEmail(), is("emailExistente@gmail.com"));
	     assertThat(result.getName(), is("nombreExistente"));
	     User resultNoExistente = servicio.findUserByEmail("emailNoExistente@gmail.com");  // Verificar que no se encuentra un usuario
	     assertThat(resultNoExistente, is(nullValue()));  // Debe devolver null
	 }
	 
	 
	 @Test
	 void findAllUsersTest() {
	     User usuario1 = new User("Guadalupe", "guadalupe@gmail.com", "cont123");
	     usuario1.setId(1);
	     baseDatos.put(usuario1.getId(), usuario1);

	     User usuario2 = new User("Eduardo", "yajiss@gmail.com", "cont456");
	     usuario2.setId(2);
	     baseDatos.put(usuario2.getId(), usuario2);

	     when(dao.findAll()).thenReturn(baseDatos.values().stream().toList());

	     List<User> result = servicio.findAllUsers();

	     assertThat(result, hasSize(2));
	     assertThat(result, containsInAnyOrder(usuario1, usuario2));

	     // Caso sin usuarios
	     when(dao.findAll()).thenReturn(List.of());
	     List<User> emptyResult = servicio.findAllUsers();
	     assertThat(emptyResult, is(empty()));
	    
	 }
	 @Test
	 void createUserTest() {
        // INICIALIZACIÓN
        User nuevoUsuario = new User("nombre", "email", "password");
        when(dao.findUserByEmail("email")).thenReturn(null);  // Simula que no existe el usuario
        when(dao.save(any(User.class))).thenReturn(1);  // Simula que el ID 1 es asignado al usuario
 
        // EJECUTAR
        User resultado = servicio.createUser("nombre", "email", "password");
 
        // VERIFICAR
        assertThat(resultado.getName(), is("nombre"));
        assertThat(resultado.getEmail(), is("email"));
        assertThat(resultado.getPassword(), is("password"));
        assertThat(resultado.getId(), is(1));
    }	

	 @Test 	
	public void buscarIdTest1 () {
		// INICIALIZACION
		User resultadoEsperado = new User("name" , "email" , "password");
		resultadoEsperado.setId(1);
		when(dao.findById(1)).thenReturn(resultadoEsperado);
		
		  // EJERCICIO
        User resultado = servicio.findUserById(1);
 
        // VERIFICAR
        assertThat(resultado.getId(), is(resultadoEsperado.getId()));
        assertThat(resultado.getName(), is(resultadoEsperado.getName()));
        assertThat(resultado.getEmail(), is(resultadoEsperado.getEmail()));
    }

	}