package org.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.time.Duration;
import java.util.Random;

import static java.nio.file.Paths.get;
import static org.junit.jupiter.api.Assertions.*;

public class TestFantasy {
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
    public void testFantasy() throws InterruptedException {
        webDriver.get(baseUrl);
        Thread.sleep(2000);

        webDriver.findElement(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"advertClose\"]")).click();
        Thread.sleep(3000);

        WebElement hoverElement = webDriver.findElement(By.xpath("//*[@id=\"mainNav\"]/nav/ul/li[2]/div[1]"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(hoverElement).perform();
        Thread.sleep(5000);

        webDriver.findElement(By.xpath("//*[@id=\"mainNav\"]/nav/ul/li[2]/div[2]/ul/li[1]/a")).click();
        Thread.sleep(5000);

        webDriver.findElement(By.xpath("//*[@id=\"loginUsername\"]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"loginUsername\"]")).sendKeys("imrox545@gmail.com");
        webDriver.findElement(By.xpath("//*[@id=\"loginLoginWrap\"]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"loginLoginWrap\"]")).sendKeys("SecurePass123!");

        webDriver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div/form/div[3]/button")).click();
        Thread.sleep(4000);
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0, 600);");
        Thread.sleep(5000);
        webDriver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div/div/div[1]/div[2]/div[3]/button[1]")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div/div/div[1]/div[2]/div[3]/button[3]")).click();

        Thread.sleep(3000);
        webDriver.findElement(By.xpath("//*[@id=\"entryName\"]")).sendKeys("SVVT Team");
        WebElement dropdown = webDriver.findElement(By.xpath("//*[@id='entryFave']"));
        Select select = new Select(dropdown);
        select.selectByValue("11");
        Thread.sleep(4000);
        js.executeScript("arguments[0].click();",webDriver.findElement(By.xpath("//*[@id=\"entryTerms\"]")));
        Thread.sleep(3000);
        webDriver.findElement(By.xpath("//*[@id=\"root-dialog\"]/div/dialog/div/div[2]/form/button")).click();
        Thread.sleep(4000);

        webDriver.navigate().to("https://fantasy.premierleague.com/my-team");
        String teamName = webDriver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[2]/div[2]/div[1]/div/div[1]/h3/div[2]")).getText();
        assertEquals(teamName, "SVVT Team");

    }
    @Test
    public void testSwitch () throws InterruptedException{
        webDriver.get(baseUrl);
        Thread.sleep(2000);

        webDriver.findElement(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"advertClose\"]")).click();
        Thread.sleep(3000);

        WebElement hoverElement = webDriver.findElement(By.xpath("//*[@id=\"mainNav\"]/nav/ul/li[2]/div[1]"));
        Actions actions = new Actions(webDriver);
        actions.moveToElement(hoverElement).perform();
        Thread.sleep(5000);

        webDriver.findElement(By.xpath("//*[@id=\"mainNav\"]/nav/ul/li[2]/div[2]/ul/li[1]/a")).click();
        Thread.sleep(5000);

        webDriver.findElement(By.xpath("//*[@id=\"loginUsername\"]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"loginUsername\"]")).sendKeys("imrox545@gmail.com");
        webDriver.findElement(By.xpath("//*[@id=\"loginLoginWrap\"]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"loginLoginWrap\"]")).sendKeys("SecurePass123!");

        webDriver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div/div/form/div[3]/button")).click();
        Thread.sleep(4000);

        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0, 250);");
        String firstPlayer = webDriver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[2]/div[1]/div[2]/div[3]/div/div/div/div[2]/div[2]/div/button/span/span[2]/span")).getText();
        webDriver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[2]/div[1]/div[2]/div[3]/div/div/div/div[2]/div[2]/div/button")).click();
        webDriver.findElement(By.xpath("//*[@id=\"root-dialog\"]/div/dialog/div/div[2]/ul/li[1]/button")).click();

        js.executeScript("window.scrollBy(250, 550);");
        webDriver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[2]/div[1]/div[2]/div[3]/div/div/div/div[5]/div/div/div[3]/div/button/span/span[1]/picture/img")).click();
        webDriver.findElement(By.xpath("//*[@id=\"root-dialog\"]/div/dialog/div/div[2]/ul/li[1]/button")).click();
        String switchPlayer = webDriver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[2]/div[1]/div[2]/div[3]/div/div/div/div[2]/div[2]/div/button/span/span[2]/span")).getText();
        Thread.sleep(4000);
        assertNotEquals(firstPlayer, switchPlayer);
        //assertTrue(webDriver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[2]/div[1]/div[2]/div[3]/div/div/div/div[2]/div[2]/div/button/span/span[2]/span")).getText().contains("Kerkez"));
        Thread.sleep(4000);
        js.executeScript("window.scrollBy(550, 700);");

        webDriver.findElement(By.xpath("//*[@id=\"root\"]/div[2]/div[2]/div[1]/div[3]/button")).click();


    }



}
