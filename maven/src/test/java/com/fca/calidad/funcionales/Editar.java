package com.fca.calidad.funcionales;

import java.util.concurrent.TimeUnit;
import org.junit.*;
import static org.junit.Assert.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import io.github.bonigarcia.wdm.WebDriverManager;
import java.time.Duration;

public class Editar {
    private WebDriver driver;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    JavascriptExecutor js;

    @Before
    public void setUp() throws Exception {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(60));
        js = (JavascriptExecutor) driver;
    }

    @Test
    public void testEd2() throws Exception {
        // Abrir la aplicación
        driver.get("https://mern-crud-mpfr.onrender.com/");

        // Crear un usuario nuevo
        driver.findElement(By.xpath("//div[@id='root']/div/div[2]/button")).click();Thread.sleep(1000);
        driver.findElement(By.name("name")).click();Thread.sleep(1000);
        driver.findElement(By.name("name")).clear();Thread.sleep(1000);
        driver.findElement(By.name("name")).sendKeys("nuevo");
        driver.findElement(By.name("email")).click();Thread.sleep(1000);
        driver.findElement(By.name("email")).clear();
        driver.findElement(By.name("email")).sendKeys("nuevo@gmail.com");
        driver.findElement(By.name("age")).click();Thread.sleep(1000);
        driver.findElement(By.name("age")).clear();Thread.sleep(1000);
        driver.findElement(By.name("age")).sendKeys("22");
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Gender'])[2]/following::div[1]")).click();Thread.sleep(1000);
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Male'])[1]/following::div[2]")).click();Thread.sleep(1000);
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Woah!'])[1]/following::button[1]")).click();Thread.sleep(1000);
        driver.findElement(By.xpath("//i")).click();Thread.sleep(1000);

        // Editar el usuario creado
        driver.findElement(By.xpath("//div[@id='root']/div/div[2]/table/tbody/tr/td[5]/button")).click();Thread.sleep(1000);
        driver.findElement(By.name("name")).click();Thread.sleep(1000);
        driver.findElement(By.name("name")).clear();
        driver.findElement(By.name("name")).sendKeys("Nuevocambio");
        driver.findElement(By.xpath("(.//*[normalize-space(text()) and normalize-space(.)='Woah!'])[1]/following::button[1]")).click();
        driver.findElement(By.xpath("//i")).click();Thread.sleep(1000);
 

        // Esperar a que el texto actualizado aparezca en la página
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(By.tagName("body"), "Nuevocambio"));

        // Verificar que el nombre se actualizó correctamente
        String bodyText = driver.findElement(By.tagName("body")).getText();
        assertTrue("Who", bodyText.contains("Nuevocambio"));
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
