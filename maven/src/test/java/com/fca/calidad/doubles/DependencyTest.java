package com.fca.calidad.doubles;

import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.stubbing.Answer;

class DependencyTest {
	private Dependency dependency;
	private SubDependency sub;
	
	@BeforeEach
	void setup() {
		sub = mock(SubDependency.class); //la variable es un mock, no regresa nada porque esta vacio
		dependency = new Dependency(sub);
		dependency = mock (Dependency.class);
	}
	/*@Test
	void test() {
		System.out.println(sub.getClassName());
	}*/
	
	@Test
	void testDependency() {
		//inicializacion
		when(dependency.getClassName()).thenReturn("Hola");
		String resultadoEsperado = "Hola";
		
		//llamada
		String resultadoReal = dependency.getClassName();
		
		//verificacion			wwww
		assertThat(resultadoReal, is(resultadoEsperado));
		
	}
	@Test
	void addTwoTest() {	//anyInt definir para cualquier entero, cualquiera entre entero
	
		//inicializacion
		when(dependency.addTwo(anyInt())).thenReturn(10); /*cuando tu llamas al primero es para qie aparezca en return*/
		int resultadoEsperado = 12;
		assertThat(resultadoEsperado, is(dependency.addTwo(5)));
	}
	@Test
	void addTwoAnswerTest() {	
	
		//inicializacion
		when(dependency.addTwo(anyInt())).thenAnswer(new Answer<Integer>() {
			
		public Integer answer(InvocationOnMock invocation) throws Throwable{
			int arg = (Integer)invocation.getArguments()[0];
			return 20 * 2 + 10 + arg;
		}
		
		
	});
		

}
	

}
