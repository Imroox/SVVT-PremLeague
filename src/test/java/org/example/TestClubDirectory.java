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

public class TestClubDirectory {
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

    @Test
    public void testTitle() throws InterruptedException {
        webDriver.get(baseUrl);
        String actualTitle = webDriver.getTitle();
        System.out.println("Actual title: " + actualTitle);
        assertEquals("Premier League Football News, Fixtures, Scores & Results", actualTitle, "Title does not match");
        Thread.sleep(1000);
    }

    @Test
    void testRedirect() throws InterruptedException {
        webDriver.get("https://www.premierleague.com/");
        Thread.sleep(2000);
        String currentUrl = webDriver.getCurrentUrl();
        System.out.println("Current URL: " + currentUrl);
        assertEquals("https://www.premierleague.com/", currentUrl);
    }
    @AfterAll
    public static void tearDown() {
        // Close the browser
        if (webDriver != null) {
            webDriver.quit();
        }
    }
    @Test
    public void checkClubDirectory() throws InterruptedException {
        webDriver.get("https://www.premierleague.com/");
        Thread.sleep(3000);
        webDriver.findElement(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"advertClose\"]")).click();
        Thread.sleep(new Random().nextInt(2000) + 3000);


        webDriver.findElement(By.cssSelector("body > header > div > nav > ul > li:nth-child(12) > a")).click();
        Thread.sleep(4000);
        webDriver.findElement(By.cssSelector("#search-input")).sendKeys("Leicester City");
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[2]/header/div/div/div/div")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[2]/div/div/div[2]/div/table/tbody/tr/td[1]/a/div[1]/div[1]")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.cssSelector("#mainContent > nav > ul > li:nth-child(9) > a")).click();
        Thread.sleep(3000);

        WebElement Chairman = webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[2]/section/div[2]/section/div[2]/div[1]/div/div[2]/p"));
        assertEquals("Aiyawatt Srivaddhanaprabha ", Chairman.getText(), "Chairman's name should match Aiyawatt Srivaddhanaprabha");

        WebElement ChiefExecutive = webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[2]/section/div[2]/section/div[2]/div[2]/div[2]/div[1]/div/div[2]/p"));
        assertTrue(ChiefExecutive.isDisplayed(), "Chief Executive's name should match Susan Whelan");

    }


    }









