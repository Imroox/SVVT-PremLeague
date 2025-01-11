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

public class TestRegistration {
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
    public void testFailedRegistration() throws InterruptedException {

        webDriver.get("https://users.premierleague.com/users/register/personal");
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]")).click();

        webDriver.findElement(By.id("ism-first-name")).sendKeys("Test");
        webDriver.findElement(By.id("ism-last-name")).sendKeys("User");

        Thread.sleep(2000);
        webDriver.findElement(By.id("ism-email")).sendKeys("aliimranalic6@gmail.com");
        webDriver.findElement(By.id("ism-new-password")).sendKeys("SecurePass123!");
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/form/div[6]/div/ul/li[1]/label/span")).click();


        webDriver.findElement(By.id("ism-dob-day")).sendKeys("01");
        webDriver.findElement(By.id("ism-dob-month")).sendKeys("01");
        webDriver.findElement(By.id("ism-dob-year")).sendKeys("2000");

        webDriver.findElement(By.xpath("//*[@id=\"ism-region\"]/option[2]")).click();

        Thread.sleep(3000);

        webDriver.findElement(By.cssSelector("#root > div > div > div:nth-child(4) > form > div.Button__ButtonWrap-sc-1vjeq7p-0.jxfNhP > button")).click();
        Thread.sleep(3000);

        webDriver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/form/div[1]/fieldset/ul/li[11]/label/div/div")).click();

        Thread.sleep(2000);

        webDriver.findElement(By.cssSelector("#root > div > div > div:nth-child(4) > form > div:nth-child(5) > div:nth-child(1) > div > button")).click();

        Thread.sleep(3000);


        webDriver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/form/div[5]/div/div/label/span")).click();

        Thread.sleep(2000);

        webDriver.findElement(By.cssSelector("#root > div > div > div:nth-child(4) > form > div:nth-child(11) > div:nth-child(1) > div > button")).click();

        Thread.sleep(3000);


        WebElement errorMessageElement = webDriver.findElement(By.cssSelector("#root > div > div > div:nth-child(4) > form > div:nth-child(9) > div:nth-child(2) > div.Copy-sc-1hw5ifr-0.eXoUVy > p")); // Adjust the selector if needed
        String actualErrorMessage = errorMessageElement.getText();

        String expectedErrorMessage = "aliimranalic6@gmail.com already has a premierleague account. Please choose a different email address or login to your existing account.";
        assertEquals(expectedErrorMessage, actualErrorMessage);
    }
    @Test
    public void testInvalidEmail() throws InterruptedException {
        webDriver.get("https://users.premierleague.com/users/register/personal");
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]")).click();

        webDriver.findElement(By.id("ism-first-name")).sendKeys("Test");
        webDriver.findElement(By.id("ism-last-name")).sendKeys("User");

        Thread.sleep(2000);
        webDriver.findElement(By.id("ism-email")).sendKeys("aliimranalic6gmail.com");
        webDriver.findElement(By.xpath("//*[@id=\"ism-new-password\"]")).click();
        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//*[@id=\"ism-new-password\"]")).sendKeys("SecurePass123!");
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/form/div[6]/div/ul/li[1]/label/span")).click();


        webDriver.findElement(By.id("ism-dob-day")).sendKeys("01");
        webDriver.findElement(By.id("ism-dob-month")).sendKeys("01");
        webDriver.findElement(By.id("ism-dob-year")).sendKeys("2000");

        webDriver.findElement(By.xpath("//*[@id=\"ism-region\"]/option[2]")).click();

        Thread.sleep(3000);

        webDriver.findElement(By.cssSelector("#root > div > div > div:nth-child(4) > form > div.Button__ButtonWrap-sc-1vjeq7p-0.jxfNhP > button")).click();
        Thread.sleep(3000);

        WebElement errorMessage = webDriver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/form/div[3]/div/div[2]/div/p"));
        assertTrue(errorMessage.isDisplayed(), "Invalid Email Address should be displayed");
    }


    @Test
    public void testInvalidDate() throws InterruptedException {
        webDriver.get("https://users.premierleague.com/users/register/personal");
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]")).click();

        webDriver.findElement(By.id("ism-first-name")).sendKeys("Test");
        webDriver.findElement(By.id("ism-last-name")).sendKeys("User");

        Thread.sleep(2000);
        webDriver.findElement(By.id("ism-email")).sendKeys("imrox545@gmail.com");
        webDriver.findElement(By.xpath("//*[@id=\"ism-new-password\"]")).click();
        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//*[@id=\"ism-new-password\"]")).sendKeys("JamieVardy223!");
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/form/div[6]/div/ul/li[1]/label/span")).click();


        webDriver.findElement(By.id("ism-dob-day")).sendKeys("01");
        webDriver.findElement(By.id("ism-dob-month")).sendKeys("011");
        webDriver.findElement(By.id("ism-dob-year")).click();
        webDriver.findElement(By.id("ism-dob-year")).sendKeys("2000");

        webDriver.findElement(By.xpath("//*[@id=\"ism-region\"]/option[2]")).click();

        Thread.sleep(3000);

        webDriver.findElement(By.cssSelector("#root > div > div > div:nth-child(4) > form > div.Button__ButtonWrap-sc-1vjeq7p-0.jxfNhP > button")).click();
        Thread.sleep(3000);

        WebElement errorMessage = webDriver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/form/div[7]/div/div[2]"));
        assertTrue(errorMessage.isDisplayed(), "Enter a number between 1-12 should be displayed");

        WebElement errorMessage2 = webDriver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/form/div[7]/div/div[3]/p"));
        assertTrue(errorMessage2.isDisplayed(),"Invalid Date should be displayed");

    }
    @Test
    public void testRegistration() throws InterruptedException {

        webDriver.get("https://users.premierleague.com/users/register/personal");
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]")).click();

        webDriver.findElement(By.id("ism-first-name")).sendKeys("Test");
        webDriver.findElement(By.id("ism-last-name")).sendKeys("User");

        Thread.sleep(2000);
        webDriver.findElement(By.id("ism-email")).sendKeys("imrox545@gmail.com");
        webDriver.findElement(By.id("ism-new-password")).sendKeys("SecurePass123!");
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/form/div[6]/div/ul/li[1]/label/span")).click();


        webDriver.findElement(By.id("ism-dob-day")).sendKeys("01");
        webDriver.findElement(By.id("ism-dob-month")).sendKeys("01");
        webDriver.findElement(By.id("ism-dob-year")).sendKeys("2000");

        webDriver.findElement(By.xpath("//*[@id=\"ism-region\"]/option[2]")).click();

        Thread.sleep(3000);

        webDriver.findElement(By.cssSelector("#root > div > div > div:nth-child(4) > form > div.Button__ButtonWrap-sc-1vjeq7p-0.jxfNhP > button")).click();
        Thread.sleep(3000);

        webDriver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/form/div[1]/fieldset/ul/li[11]/label/div/div")).click();

        Thread.sleep(2000);

        webDriver.findElement(By.cssSelector("#root > div > div > div:nth-child(4) > form > div:nth-child(5) > div:nth-child(1) > div > button")).click();

        Thread.sleep(3000);


        webDriver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/form/div[5]/div/div/label/span")).click();

        Thread.sleep(2000);

        webDriver.findElement(By.cssSelector("#root > div > div > div:nth-child(4) > form > div:nth-child(11) > div:nth-child(1) > div > button")).click();

        Thread.sleep(3000);
        webDriver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/form/div[1]/fieldset/ul/li[1]/label/div/div/div/img")).click();
        webDriver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/form/div[3]/div[1]/div/button")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.xpath("//*[@id=\"ism-terms-agreed\"]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/form/div[6]/div[1]/div/button")).click();
        Thread.sleep(4000);
        //assertTrue(webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[3]/div/ul/div[5]/ul/li[1]/a/div/div[1]/div[2]/ul/li[2]/div[2]")).getText().contains("Thanks for registering."));



    }




}