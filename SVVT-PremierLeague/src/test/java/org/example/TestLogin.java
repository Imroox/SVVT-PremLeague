package org.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestReporter;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.Random;

import static java.nio.file.Paths.get;
import static org.junit.jupiter.api.Assertions.*;

public class TestLogin {
    private static WebDriver webDriver;
    private static String baseUrl;

    @BeforeAll
    public static void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Korisnik\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe"); // specify the path to chromedriver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-blink-features=AutomationControlled");
        webDriver = new ChromeDriver(options);
        baseUrl = "https://www.premierleague.com/";
        webDriver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        webDriver.manage().window().maximize();

    }

    @AfterAll
    public static void tearDown() {
        if (webDriver != null) {
            webDriver.quit();
        }
    }

    @Test
    public void testLogin() throws InterruptedException {
        webDriver.get(baseUrl);
        Thread.sleep(4000);
        webDriver.findElement(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"advertClose\"]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"mainNav\"]/a[1]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"ism-email\"]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"ism-email\"]")).sendKeys("aliimranalic6@gmail.com");
        webDriver.findElement(By.xpath("//*[@id=\"ism-password\"]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"ism-password\"]")).sendKeys("SecurePass123!");
        Thread.sleep(3000);
        webDriver.findElement(By.xpath("//*[@id=\"ismr-content\"]/form/div/div[1]/div[3]/button")).click();
        Thread.sleep(6000);
        assertEquals(webDriver.getCurrentUrl(), "https://www.premierleague.com/?state=success");
        Thread.sleep(3000);
        String username = webDriver.findElement(By.xpath("//*[@id=\"mainNav\"]/a[2]/span")).getText();
        assertEquals(username,"Ali Imran");
        Thread.sleep(3000);

    }
    @Test
    public void testLogOut() throws InterruptedException{
        webDriver.get(baseUrl);
        Thread.sleep(4000);
        webDriver.findElement(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"advertClose\"]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"mainNav\"]/a[1]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"ism-email\"]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"ism-email\"]")).sendKeys("aliimranalic6@gmail.com");
        webDriver.findElement(By.xpath("//*[@id=\"ism-password\"]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"ism-password\"]")).sendKeys("SecurePass123!");
        Thread.sleep(3000);
        webDriver.findElement(By.xpath("//*[@id=\"ismr-content\"]/form/div/div[1]/div[3]/button")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.xpath("//*[@id=\"mainNav\"]/a[2]")).click();
        Thread.sleep(3000);
        String logOutButton = webDriver.findElement(By.xpath("//*[@id=\"ismr-content\"]/div/div[2]/div/a[2]")).getText();
        assertEquals(logOutButton, "Sign Out");
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"ismr-content\"]/div/div[2]/div/a[2]")).click();
        assertEquals(webDriver.getCurrentUrl(), "https://users.premierleague.com/");
        Thread.sleep(3000);
    }
    @Test
    public void invalidPassword() throws InterruptedException {
        webDriver.get(baseUrl);
        Thread.sleep(new Random().nextInt(2000) + 3000);

        webDriver.findElement(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]")).click();
        Thread.sleep(new Random().nextInt(2000) + 3000);

        webDriver.findElement(By.xpath("//*[@id=\"advertClose\"]")).click();
        Thread.sleep(new Random().nextInt(2000) + 3000);

        webDriver.findElement(By.xpath("//*[@id=\"mainNav\"]/a[1]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"ism-email\"]")).sendKeys("aliimranalic6@gmail.com");
        webDriver.findElement(By.xpath("//*[@id=\"ism-password\"]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"ism-password\"]")).sendKeys("SecurePass123");

        Thread.sleep(new Random().nextInt(2000) + 3000);

        webDriver.findElement(By.xpath("//*[@id=\"ismr-content\"]/form/div/div[1]/div[3]/button")).click();
        Thread.sleep(12000);

        //String errorMessage = webDriver.findElement(By.xpath("//*[@id=\"ismr-content\"]/form/div/div[1]/div[1]")).getText();
       // assertEquals(errorMessage, "Incorrect email or password");
    }






}