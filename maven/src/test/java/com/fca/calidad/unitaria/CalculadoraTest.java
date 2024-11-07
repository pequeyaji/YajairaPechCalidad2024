package com.fca.calidad.unitaria;

import static org.hamcrest.MatcherAssert.assertThat; 
import static org.hamcrest.Matchers.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CalculadoraTest {
	
	private double num1= 2;
	private double num2= 5;
	private Calculadora calculadora = null;
	
	@BeforeEach
	void setup() {
		double num1= 2;
		double num2= 5;
		calculadora = new Calculadora();
		
	}

	@Test
	void suma2numerosPsositivosTest () {
		//inicialización
		/*double num1= 2;
		double num2= 5;*/
		double resEsperado = 7;
		Calculadora calculadora = new Calculadora();
		
		//Ejercicio, llamar al método que queremos probar
		double resEjecucion = calculadora.suma(num1, num2);
		
		//verificar
		assertThat(resEsperado, is(resEjecucion));
	
	}
	@Test
	void restar2numerosPsositivosTest () {
		//inicialización
		double num1= 2;
		double num2= 5;
		double resEsperado = -3;
		Calculadora calculadora = new Calculadora();
		
		//Ejercicio, llamar al método que queremos probar
		double resEjecucion = calculadora.resta(num1, num2);
		
		//verificar
		assertThat(resEsperado, is(resEjecucion));
	
	}
	
	@Test
	void multiplicar2numerosPsositivosTest () {
		//inicialización
		double num1= 2;
		double num2= 5;
		double resEsperado = 10;
		Calculadora calculadora = new Calculadora();
		
		//Ejercicio, llamar al método que queremos probar
		double resEjecucion = calculadora.multiplica(num1, num2);
		
		//verificar
		assertThat(resEsperado, is(resEjecucion));
	
	}
	
	@Test
	void dividir2numerosPsositivosTest () {
		//inicialización
		double num1= 2;
		double num2= 5;
		double resEsperado = 0.4;
		Calculadora calculadora = new Calculadora();
		
		//Ejercicio, llamar al método que queremos probar
		double resEjecucion = calculadora.divide(num1, num2);
		
		//verificar
		assertThat(resEsperado, is(resEjecucion));
	
	}
	
	@AfterEach
	void print() {
		System.out.println("esto se imprime desp de cada prueba");
	}

}
