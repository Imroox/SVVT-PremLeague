package org.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestReporter;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.time.Duration;
import java.util.Random;

import static java.nio.file.Paths.get;
import static org.junit.jupiter.api.Assertions.*;

public class TestManager {
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
    public void checkManager() throws InterruptedException {
        webDriver.get(baseUrl);
        JavascriptExecutor js = (JavascriptExecutor) webDriver;

        Thread.sleep(4000);
        webDriver.findElement(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"advertClose\"]")).click();

        WebElement hoverElement = webDriver.findElement(By.xpath("//*[@id=\"mainNav\"]/nav/ul/li[6]/div[1]"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(hoverElement).perform();
        Thread.sleep(5000);
        webDriver.findElement(By.xpath("//*[@id=\"mainNav\"]/nav/ul/li[6]/div[2]/ul/li[4]/a")).click();
        webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[2]/div/div[1]/section/div[2]/div[2]")).click();
        Thread.sleep(3000);
        js.executeScript("arguments[0].click();",webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[2]/div/div[1]/section/div[2]/div[2]")));


        webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[2]/div/div[1]/section/div[2]/div[3]/ul/li[3]")).click();
        Thread.sleep(4000);
        webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[2]/div/div[1]/div/table/tbody/tr/td[1]/a")).click();
        Thread.sleep(4000);

        int winStat = Integer.parseInt(webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[2]/nav/div/section[2]/div/div[2]/div[2]")).getText());
        assertEquals(winStat, 69);

        int drawStat = Integer.parseInt(webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[2]/nav/div/section[2]/div/div[3]/div[2]")).getText());
        assertEquals(drawStat, 30);

        int loseStat = Integer.parseInt(webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[2]/nav/div/section[2]/div/div[4]/div[2]")).getText());
        assertEquals(loseStat, 35);


    }




}