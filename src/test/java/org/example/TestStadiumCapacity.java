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

public class TestStadiumCapacity {
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
        // Get the title of the page and check if it matches the expected title
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
    public void checkClubStadiumCapacity() throws InterruptedException {
        webDriver.get(baseUrl);

        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"advertClose\"]")).click();
        Thread.sleep(new Random().nextInt(2000) + 3000);


        webDriver.findElement(By.xpath("/html/body/header/div/nav/ul/li[12]/a")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[2]/div/div/div[1]/ul/li[11]/a")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/nav/ul/li[7]/a")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.cssSelector("#mainContent > div.wrapper.hasFixedSidebar > nav > div > div > div > ul > li:nth-child(2)")).click();
        Thread.sleep(2000);


        WebElement StadiumCapacity = webDriver.findElement(By.cssSelector("#mainContent > div.wrapper.hasFixedSidebar > div > div > div > article.club-article.club-article--stadium-info.tabbed-article.tabbed-article--stadium-info.active > p:nth-child(1)"));
        assertEquals("Capacity: 32,262", StadiumCapacity.getText(), "The capacity should be: 32.262");


    }














}

