package com.fca.calidad.integracion;

import static org.junit.jupiter.api.Assertions.*;
import java.io.FileInputStream;

import org.dbunit.Assertion;
import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.filter.DefaultColumnFilter;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.fca.calidad.dao.DAOUserSQLite;
import com.fca.calidad.model.User;
import com.fca.calidad.service.UserService;

class UserServiceTest extends DBTestCase {
	//private static final String String = null;
	private DAOUserSQLite dao;
	private UserService userService;

	public UserServiceTest(){
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_DRIVER_CLASS, "org.sqlite.JDBC");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_CONNECTION_URL, "jdbc:sqlite:\\Users\\YAJAI\\OneDrive\\Escritorio\\user.db");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_USERNAME, "");
		System.setProperty(PropertiesBasedJdbcDatabaseTester.DBUNIT_PASSWORD, "");
	}

	@BeforeEach
	public void setUp() {
		//crear instancia
		dao= new DAOUserSQLite();
		userService =new UserService(dao);
		
		//inicializar la base
		IDatabaseConnection connection;
		try {
			connection = getConnection();
			DatabaseOperation.CLEAN_INSERT.execute(connection, getDataSet());
			//IDataSet databaseDataSet =connection.createDataSet();
		//	ITable actualTable = databaseDataSet.getTable("user");
		
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			fail("fallo setup");
		}
	}
		protected IDataSet getDataSet() throws Exception {
			return new FlatXmlDataSetBuilder().build(new FileInputStream("C:\\Users\\YAJAI\\git\\repository\\maven\\src\\resources\\initDB.xml"));
		}
		@Test
		void createUserTest() {
			
			//ejercicio de codigo
			User usuario = userService.createUser("Name", "email", "password");
			
			//Assertion
			int resultadoEsperado = 1;
			
			IDatabaseConnection connection;
			try {
				connection = getConnection();
				IDataSet databaseDataSet =connection.createDataSet();
				ITable tablaReal = databaseDataSet.getTable("user");
				tablaReal = databaseDataSet.getTable("user");
				String nombreReal = (String) tablaReal.getValue(0, "Name");
				String nombreEsperado = "Name";
				assertEquals(nombreReal, nombreEsperado);
				
				String emailReal = (String) tablaReal.getValue(0, "email");
				String emailEsperado = "email";
				assertEquals(emailReal, emailEsperado);
				
				String passwordReal = (String) tablaReal.getValue(0, "password");
				String passwordEsperado = "password";
				assertEquals(passwordReal, passwordEsperado);
				
				int resultadoAcutual = tablaReal.getRowCount();
				assertEquals(resultadoEsperado, resultadoAcutual );
			} catch (Exception e) {
				// TODO Auto-generated catch block
				fail("fallo");
				e.printStackTrace();
			}
			
		
	}
		@Test
		void createUser2Test() {
			User usuario = userService.createUser("Name", "email", "password");
			
			IDatabaseConnection connection;
			try {
				connection = getConnection();
				IDataSet databaseDataSet = connection.createDataSet();
				ITable tablaReal = databaseDataSet.getTable("user");
				String nombreReal = (String) tablaReal.getValue(0, "Name");
				String nombreEsperado = "Name";
				//System.out.println("Real =" + nombreReal);
				assertEquals(nombreReal, nombreEsperado);
				String emailReal = (String) tablaReal.getValue(0, "email");
				String emailEsperado = "email";
				assertEquals(emailReal, emailEsperado);
				String passwordReal = (String) tablaReal.getValue(0, "password");
				String passwordEsperado = "password";
				assertEquals(passwordReal, passwordEsperado);
				//System.out.println("E=" + (String) tablaReal.getValue(0, "email"));
				//System.out.println("P=" + (String) tablaReal.getValue(0, "password"));
				//assertEquals((String), tablaReal.getValue(0, "email"),"email");
				//assertEquals((String), tablaReal.getValue(0, "password"),"password");
			}catch (Exception e) {	
				e.printStackTrace();
				fail("Fallo");
			}
		}
		@Test
		void createUser3Test() {
			User usuario = userService.createUser("Name", "email", "password");
			IDatabaseConnection connection;
			try {
				connection = getConnection();
				IDataSet databaseDataSet = connection.createDataSet();
				ITable tablaReal = databaseDataSet.getTable("user");
				IDataSet exceptedDataSet = new FlatXmlDataSetBuilder().build(new FileInputStream("src/resources/initDB.xml"));
				ITable exceptedTable = exceptedDataSet.getTable("user");
				
				ITable filteredTable = DefaultColumnFilter.includedColumnsTable(tablaReal, 
						exceptedTable.getTableMetaData().getColumns());
				
				Assertion.assertEquals(filteredTable, exceptedTable);
			}catch (Exception e) {
				
				e.printStackTrace();
				fail("Fallo");
			}
		
			
		}
}