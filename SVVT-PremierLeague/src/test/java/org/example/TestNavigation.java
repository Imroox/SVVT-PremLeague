package org.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestReporter;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

import static java.nio.file.Paths.get;
import static org.junit.jupiter.api.Assertions.*;

public class TestNavigation {
    private static WebDriver webDriver;
    private static String baseUrl;
    private static String helpUrl;

    @BeforeAll
    public static void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\Korisnik\\Downloads\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe"); // specify the path to chromedriver
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        options.addArguments("--disable-blink-features=AutomationControlled");
        webDriver = new ChromeDriver(options);
        baseUrl = "https://www.premierleague.com/";
        helpUrl = "https://www.premierleague.com/match/116013";
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
    public void testNavigationLinks() throws InterruptedException {
        webDriver.get(baseUrl);
        Thread.sleep(2000);

        webDriver.findElement(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"advertClose\"]")).click();
        Thread.sleep(new Random().nextInt(2000) + 3000);

        webDriver.findElement(By.xpath("/html/body/header/div/nav/ul/li[2]/a")).click();
        Thread.sleep(2000);
        assertTrue(webDriver.getTitle().contains("Fixtures"), "Fixtures page title should contain 'Fixtures'");

        webDriver.findElement(By.xpath("/html/body/header/div/nav/ul/li[3]/a")).click();
        Thread.sleep(2000);
        assertTrue(webDriver.getTitle().contains("Results"), "Results page title should contain 'Results'");

        webDriver.findElement(By.xpath("/html/body/header/div/nav/ul/li[13]/a")).click();
        Thread.sleep(2000);
        assertTrue(webDriver.getTitle().contains("Players"), "Players page title should contain 'Players'");

        webDriver.findElement(By.xpath("/html/body/header/div/nav/ul/li[8]/a")).click();
        Thread.sleep(2000);
        assertTrue(webDriver.getTitle().contains("News"), "News page title should contain 'News'");


        Thread.sleep(1000);
    }
    @Test

      public void testPlayerStats() throws InterruptedException{
        webDriver.get(baseUrl);
        webDriver.findElement(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"advertClose\"]")).click();


        webDriver.findElement(By.xpath("/html/body/header/div/nav/ul/li[3]/a")).click();
        Thread.sleep(5000);

        WebElement element = webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[3]/div[1]/div[2]/section/div[5]/div[1]/time[1]"));
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(4000);

        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        WebElement matchFixture = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id=\"mainContent\"]/div[3]/div[1]/div[2]/section/div[6]/div[2]/ul/li[1]")));
        matchFixture.click();
        webDriver.get(helpUrl);
        Thread.sleep(3000);

        webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/section[2]/div[2]/div/div[1]/div/div/ul/li[2]")).click();

        Thread.sleep(4000);
        js.executeScript("arguments[0].scrollIntoView(true);",webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/section[2]/div[2]/div/div[2]/section[2]/div/div/div[1]/div/div/h3[3]")));
        Thread.sleep(3000);
        webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div/section[2]/div[2]/div/div[2]/section[2]/div/div/div[1]/div/div/ul[4]/li/a")).click();Thread.sleep(3000);
        String goals = webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[2]/div/div/div[3]/table/tbody/tr[1]/td[4]")).getText();
        webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/section/div[2]/a")).click();
        Thread.sleep(4000);
        webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/nav/ul/li[2]/a")).click();
        js.executeScript("arguments[0].scrollIntoView(true);", webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[3]/div/ul/div[4]/ul/li[9]/a/div/div[1]/div[3]/img")));
        Thread.sleep(4000);
        String goals2 = webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[3]/div/ul/div[5]/ul/li[1]/a/div/div[1]/div[2]/ul/li[2]/div[2]")).getText();
        assertEquals(goals, goals2);






    }
}