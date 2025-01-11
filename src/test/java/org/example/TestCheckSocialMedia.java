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

public class TestCheckSocialMedia {
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
    public void testEnforceHttps() {
        webDriver.get(baseUrl);
        String currentUrl = webDriver.getCurrentUrl();
        assertTrue(currentUrl.startsWith("https://"), "URL should be HTTPS");
    }

    @Test
    public void testHttpToHttpsRedirection() throws InterruptedException{

        String insecureUrl = baseUrl.replace("https://", "http://");
        webDriver.get(insecureUrl);
        Thread.sleep(5000);
        String redirectedUrl = webDriver.getCurrentUrl();
        assertTrue(redirectedUrl.startsWith("https://"), "HTTP URL should be redirected to HTTPS");
    }





}