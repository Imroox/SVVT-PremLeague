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

public class TestPlayerStats {
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
    public void checkStatsOfPlayer() throws InterruptedException {
        webDriver.get("https://www.premierleague.com/");
        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.xpath("//*[@id=\"advertClose\"]")).click();
        Thread.sleep(new Random().nextInt(2000) + 3000);


        webDriver.findElement(By.xpath("/html/body/header/div/nav/ul/li[13]/a")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.xpath("//*[@id=\"search-input\"]")).sendKeys("Jamie Vardy");
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[2]/header/div/div/div/div")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[2]/div/div/div/table/tbody/tr/td[1]/a")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.cssSelector("#mainContent > div.wrapper.hasFixedSidebar > div > div > nav > ul > li:nth-child(2) > a")).click();
        Thread.sleep(10000);

        WebElement totalGoalsStat = webDriver.findElement(By.cssSelector("#mainContent > div.wrapper.hasFixedSidebar > div > div > div > div > div > div:nth-child(2) > span > span"));
        String actualTotalGoals = totalGoalsStat.getText();
        String expectedTotalGoals = "142";
        assertEquals(expectedTotalGoals, actualTotalGoals);


        WebElement totalAssistStat = webDriver.findElement(By.cssSelector("#mainContent > div.wrapper.hasFixedSidebar > div > div > div > div > ul > li:nth-child(2) > div > div:nth-child(2) > span"));
        String actualAssistStat = totalAssistStat.getText();
        String expectedAssistStat = "47";
        assertEquals(expectedAssistStat, actualAssistStat);


    }







}

