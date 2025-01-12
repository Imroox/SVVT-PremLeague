package org.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class TestSearch {
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
    public void testAutoSuggestion () throws InterruptedException {
        webDriver.get(baseUrl);

        webDriver.findElement(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"advertClose\"]")).click();
        Thread.sleep(new Random().nextInt(2000) + 3000);
        webDriver.findElement(By.xpath("//*[@id=\"mainNav\"]/div[2]/div/div")).click();
        Thread.sleep(3000);

        webDriver.findElement(By.cssSelector("#searchPremierLeagueInput")).sendKeys("Manchester");
        Thread.sleep(5000);
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("/html/body/header/div/div/div[1]/div[1]/div/div/div[1]/button")));

        List<WebElement> suggestionButtons = webDriver.findElements(By.xpath("/html/body/header/div/div/div[1]/div[1]/div/div/div[1]/button"));

        boolean foundManchesterUnited = false;
        boolean foundManchesterCity = false;

        for (WebElement button : suggestionButtons) {
            String buttonText = button.getText().trim();
            if (buttonText.equals("Manchester United")) {
                foundManchesterUnited = true;
            }
            if (buttonText.equals("Manchester City")) {
                foundManchesterCity = true;
            }
        }

       assertTrue(foundManchesterUnited && foundManchesterCity, "Expected both 'Manchester United' and 'Manchester City' in the auto-suggestions.");
    }
    @Test
    public void testSearch() throws InterruptedException {
        webDriver.get(baseUrl);

        webDriver.findElement(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"advertClose\"]")).click();
        Thread.sleep(new Random().nextInt(2000) + 3000);
        webDriver.findElement(By.xpath("//*[@id=\"mainNav\"]/div[2]/div/div")).click();
        Thread.sleep(3000);

        webDriver.findElement(By.cssSelector("#searchPremierLeagueInput")).sendKeys("Jamie Vardy");
        Thread.sleep(5000);

        webDriver.findElement(By.xpath("/html/body/header/div/div/div[1]/div[1]/div/span[2]")).click();
        Thread.sleep(5000);

        WebElement SearchResultForJamieVardy = webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[2]/header"));
        assertTrue(SearchResultForJamieVardy.isDisplayed(), "Expected Output is: Search results for: Jamie Vardy");

        WebElement SearchResult = webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[2]/header"));
        assertTrue(SearchResult.isDisplayed(), "Expected output is: Search result for: Jamie Vardy");


        }
    @Test
    public void testResult () throws InterruptedException {
        webDriver.get(baseUrl);

        webDriver.findElement(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"advertClose\"]")).click();
        Thread.sleep(new Random().nextInt(2000) + 3000);
        webDriver.findElement(By.xpath("//*[@id=\"mainNav\"]/div[2]/div/div")).click();
        Thread.sleep(3000);

        webDriver.findElement(By.cssSelector("#searchPremierLeagueInput")).sendKeys("Manchester United");
        Thread.sleep(3000);

        webDriver.findElement(By.xpath("/html/body/header/div/div/div[1]/div[1]/div/span[2]")).click();
        Thread.sleep(3000);
        int totalResults = Integer.parseInt(webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[2]/section/div/div/div/div[1]/p/span")).getText());
        webDriver.getCurrentUrl();
        Thread.sleep(3000);
        webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[2]/header/div/div/div/button")).click();
        Thread.sleep(4000);
        webDriver.findElement(By.xpath("//*[@id=\"searchPremierLeagueInput\"]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"searchPremierLeagueInput\"]")).sendKeys("Man - Utd");
        Thread.sleep(5000);
        int totalResult2 = Integer.parseInt(webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[2]/section/div/div/div/div[1]/p/span")).getText());

        Thread.sleep(4000);
        assertTrue(totalResults > totalResult2);
    }



    }



