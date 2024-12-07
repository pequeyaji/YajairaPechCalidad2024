package com.fca.calidad.funcionales;

import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class Buscar {
    private WebDriver driver;
    private String baseUrl;
    private StringBuffer verificationErrors = new StringBuffer();
    JavascriptExecutor js;

    @Before
    public void setUp() throws Exception {
        // Configuración del WebDriver
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        baseUrl = "https://mern-crud-mpfr.onrender.com/";
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        js = (JavascriptExecutor) driver;
    }

    @Test
    public void testBuscarUsuarioPrueba1() throws Exception {
        // Navegar a la página base
        driver.get(baseUrl);

        // Agregar usuario "prueba1"
        driver.findElement(By.xpath("//div[@id='root']/div/div[2]/button")).click();
        driver.findElement(By.name("name")).sendKeys("prueba1");
        driver.findElement(By.name("email")).sendKeys("prueba1@gmail.com");
        driver.findElement(By.name("age")).sendKeys("22");
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Gender'])[2]/following::div[1]")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Male'])[2]/following::div[1]")).click();
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Woah!'])[1]/following::button[1]")).click();

        // Pausar brevemente para asegurarse de que los datos se cargan (solo para pruebas)
        Thread.sleep(2000);

        // Buscar en la columna "Name" de la tabla
        boolean usuarioEncontrado = false;

        try {
            WebElement tablaUsuarios = driver.findElement(By.xpath("//div[@id='root']/div/div[2]/table/tbody"));
            for (WebElement fila : tablaUsuarios.findElements(By.tagName("tr"))) {
                WebElement columnaName = fila.findElement(By.xpath("./td[1]")); // Cambia el índice de la columna según el orden de Name
                String textoColumna = columnaName.getText();
                if (textoColumna.equalsIgnoreCase("prueba1")) {
                    usuarioEncontrado = true;
                    System.out.println("Usuario 'prueba1' encontrado en la columna Name.");
                    break;
                }
            }
        } catch (NoSuchElementException e) {
            System.out.println("No se encontró la tabla o la columna Name.");
        }

        // Verificar si el usuario fue encontrado
        if (!usuarioEncontrado) {
            System.out.println("Usuario 'prueba1' NO fue encontrado en la columna Name.");
        }

        // Afirmar que el usuario está en la tabla
        assertTrue("El usuario 'prueba1' no fue encontrado en la columna Name.", usuarioEncontrado);
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }
}
