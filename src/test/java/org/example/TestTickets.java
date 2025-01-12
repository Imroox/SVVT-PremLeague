package org.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class TestTickets {
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
    public void testAmount () throws InterruptedException {
        webDriver.get(baseUrl);
        Thread.sleep(2000);

        webDriver.findElement(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"advertClose\"]")).click();
        Thread.sleep(new Random().nextInt(2000) + 3000);
        webDriver.findElement(By.xpath("/html/body/header/div/nav/ul/li[11]/a")).click();
        Thread.sleep(5000);
        webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[3]/section[2]/div[2]/button[10]")).click();
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0, 300);");
        Thread.sleep(5000);

        WebElement linkElement = webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[3]/section[3]/div[10]/div[2]/div/a[1]"));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].removeAttribute('target');", linkElement);
        linkElement.click();

        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"navMenu_Main_SubscriptionList_item\"]")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"ctl00$body$SeriesListControl_btnAddToBasket_0\"]")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.xpath("//*[@id=\"ctl00_body_SelectTickets_Container\"]/div[5]/div[3]/div[2]/span/a[1]")).click();
        assertEquals(webDriver.findElement(By.xpath("//*[@id=\"ctl00_body_SelectTickets_Container\"]/div[6]/span[3]")).getText(), "£65.00");

    }
    @Test
    public void testInvalidBalance () throws InterruptedException {
        webDriver.get(baseUrl);
        Thread.sleep(2000);

        webDriver.findElement(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"advertClose\"]")).click();
        Thread.sleep(new Random().nextInt(2000) + 3000);
        webDriver.findElement(By.xpath("/html/body/header/div/nav/ul/li[11]/a")).click();
        Thread.sleep(5000);
        webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[3]/section[2]/div[2]/button[10]")).click();
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0, 300);");
        Thread.sleep(5000);

        WebElement linkElement = webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[3]/section[3]/div[10]/div[2]/div/a[1]"));
        ((JavascriptExecutor) webDriver).executeScript("arguments[0].removeAttribute('target');", linkElement);
        linkElement.click();

        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"navMenu_Main_SubscriptionList_item\"]")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"ctl00$body$SeriesListControl_btnAddToBasket_0\"]")).click();

        webDriver.findElement(By.xpath("//*[@id=\"ctl00_body_SelectTickets_Container\"]/div[5]/div[5]/div[2]/span/input")).sendKeys("00000000000000");
        Thread.sleep(3000);

        WebElement spanElement = webDriver.findElement(By.xpath("//*[@id=\"ctl00_body_SelectTickets_Container\"]/div[6]/span[1]"));

        assertFalse(spanElement.isDisplayed(), "The span element is visible, but it shouldn't be.");

    }

}



