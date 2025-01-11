package org.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.Random;

import static java.nio.file.Paths.get;
import static org.junit.jupiter.api.Assertions.*;

public class TestComparison {
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
    public void testPlayerComparison() throws InterruptedException {
        webDriver.get(baseUrl);
        Thread.sleep(2000);

        webDriver.findElement(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"advertClose\"]")).click();
        Thread.sleep(new Random().nextInt(2000) + 3000);
        webDriver.findElement(By.xpath("/html/body/header/div/nav/ul/li[7]/a")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/nav/ul/li[8]/a")).click();
        webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[2]/div/section[2]/div[1]")).click();
        Thread.sleep(4000);

        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].click();",webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[2]/div/section[2]/div[1]/div[1]/div/div[1]/span")));

        Thread.sleep(7000);

        webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[2]/div/section[1]/div[2]/div[1]/nav/ul/li[2]")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[2]/div/section[1]/div[2]/div[1]/div/div[2]/div/input")).sendKeys("Didier Drogba");
        Thread.sleep(2000);
        webDriver.findElement(By.cssSelector("#mainContent > div.wrapper.col-12 > div > section.player-comparison__modal > div.player-comparison__box > div.player-comparison__results-wrap.searchActive > div.player-comparison__search-results-scroll > div > ul > li > span.player-comparison__result-name")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[2]/div/section[2]/div[1]/div[2]/div/div[1]/span")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[2]/div/section[1]/div[2]/div[1]/div/div[2]/div/input")).sendKeys("Peter Crouch");
        webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[2]/div/section[1]/div[2]/div[3]/div[2]/div/ul/li")).click();
        Thread.sleep(4000);

        js.executeScript("window.scrollBy(0, 200);");
        Thread.sleep(5000);

        WebElement DrogbaGoals = webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[2]/div/section[2]/div[3]/ul/li[1]/div[2]/div[1]/div[2]/div[2]/span"));
        assertEquals("104", DrogbaGoals.getText(), "Amount of goals that Drogba scored should be 254");

        int crouchGoals = Integer.parseInt(webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[2]/div/section[2]/div[3]/ul/li[1]/div[2]/div[2]/div[2]/div[2]/span")).getText());
        int drogbaGoals = Integer.parseInt(DrogbaGoals.getText());

        assertTrue(drogbaGoals < crouchGoals, "Didier Drogba should have less goals than Peter Crouch");
    }

    @Test
    public void testClubComparison() throws InterruptedException {
        webDriver.get(baseUrl);
        Thread.sleep(2000);

        webDriver.findElement(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"advertClose\"]")).click();
        Thread.sleep(new Random().nextInt(2000) + 3000);
        webDriver.findElement(By.xpath("/html/body/header/div/nav/ul/li[7]/a")).click();
        webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/nav/ul/li[7]/a")).click();

        Thread.sleep(4000);
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("window.scrollBy(0, 450);");

        js.executeScript("arguments[0].click();",webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[2]/div/section[2]/div/ul/li[11]/div/div[1]/span/img")));
        Thread.sleep(4000);
        webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[2]/div/section[2]/div/ul/li[1]/div/div[1]/span/img")).click();
        Thread.sleep(2000);
        int arsenalGoals = Integer.parseInt(webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[2]/div/section[4]/div[1]/table/tbody/tr[1]/td[3]/p")).getText());
        int leicesterGoals = Integer.parseInt(webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[2]/div/section[4]/div[1]/table/tbody/tr[1]/td[1]/p")).getText());
        assertTrue(arsenalGoals > leicesterGoals);






    }

}
