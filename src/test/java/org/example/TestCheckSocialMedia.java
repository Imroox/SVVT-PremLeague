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
    public void checkInstagram() throws InterruptedException {
        webDriver.get(baseUrl);
        JavascriptExecutor js = (JavascriptExecutor) webDriver;

        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"advertClose\"]")).click();
        Thread.sleep(new Random().nextInt(2000) + 3000);
        js.executeScript("arguments[0].scrollIntoView(true);", webDriver.findElement(By.xpath("/html/body/footer/div[2]/div/div[7]/h3")));
        WebElement linkElement = webDriver.findElement(By.xpath("/html/body/footer/div[2]/div/div[7]/ul/li[9]/a"));
        js.executeScript("arguments[0].removeAttribute('target');", linkElement);
        linkElement.click();
        Thread.sleep(5000);
        js.executeScript("arguments[0].click();",webDriver.findElement(By.xpath("/html/body/div[6]/div[2]/div/div/div[1]/div/div[2]/div/div/div/div/div[2]/div/div[1]/div/div")));
        String currentUrl = webDriver.getCurrentUrl();
        assertEquals(currentUrl, "https://www.instagram.com/premierleague/#");

    }

    @Test
    public void checkYoutube() throws InterruptedException{
        webDriver.get(baseUrl);
        JavascriptExecutor js = (JavascriptExecutor) webDriver;

        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"advertClose\"]")).click();
        Thread.sleep(new Random().nextInt(2000) + 3000);
        js.executeScript("arguments[0].scrollIntoView(true);", webDriver.findElement(By.xpath("/html/body/footer/div[2]/div/div[7]/h3")));
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

        WebElement youtubeLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("body > footer > div.footerContent > div > div:nth-child(7) > ul > li:nth-child(1) > a")));
        js.executeScript("arguments[0].removeAttribute('target');", youtubeLink);
        youtubeLink.click();
        Thread.sleep(7000);
        String currentUrl = webDriver.getCurrentUrl();
        assertEquals(currentUrl, "https://www.youtube.com/premierleague");

    }
    @Test
    public void checkSpotify() throws InterruptedException{
        webDriver.get(baseUrl);
        JavascriptExecutor js = (JavascriptExecutor) webDriver;

        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"advertClose\"]")).click();
        Thread.sleep(new Random().nextInt(2000) + 3000);
        js.executeScript("arguments[0].scrollIntoView(true);", webDriver.findElement(By.xpath("/html/body/footer/div[2]/div/div[7]/h3")));
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(10));

        WebElement youtubeLink = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("body > footer > div.footerContent > div > div:nth-child(7) > ul > li:nth-child(18) > a")));
        js.executeScript("arguments[0].removeAttribute('target');", youtubeLink);
        youtubeLink.click();
        Thread.sleep(7000);
        String currentUrl = webDriver.getCurrentUrl();
        assertEquals(currentUrl, "https://open.spotify.com/user/plplaylists");

    }

}