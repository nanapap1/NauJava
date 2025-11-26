package ru.MarkMoskvitin.NauJava;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class LoginTest {

    WebDriver driver;
    WebDriverWait wait;

    @BeforeAll
    static void setupClass(){
          WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setup(){
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        driver.manage().window().maximize();
    }

    @AfterEach
    public void tearDown(){
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    void TestSuccessLogin(){
        driver.get("http://localhost:8091/");
        WebElement userfield = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        userfield.sendKeys("admin");

        WebElement pass =  driver.findElement(By.id("password"));
        pass.sendKeys("admin");

        WebElement login = driver.findElement(By.className("primary"));
        login.click();

        WebElement mainText = wait.until(ExpectedConditions.visibilityOfElementLocated(
                By.xpath("//p[contains(text(), 'This is main page. If you want, you can logout.')]")
        ));

        WebElement logoutLink = driver.findElement(By.xpath("//a[@href='/logout']"));
        assertTrue(mainText.isDisplayed());
        assertTrue(logoutLink.isDisplayed());
        assertEquals("http://localhost:8091/logout", logoutLink.getAttribute("href"));
        assertEquals("here", logoutLink.getText());

        logoutLink.click();
        WebElement end = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("username")));
        WebElement end2 =  driver.findElement(By.id("password"));
        WebElement end3 = driver.findElement(By.className("primary"));
        assertTrue(end.isDisplayed());
        assertTrue(end2.isDisplayed());
        assertTrue(end3.isDisplayed());


    }

}
