package org.example;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestReporter;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Random;

import static java.nio.file.Paths.get;
import static org.junit.jupiter.api.Assertions.*;

public class TestOpenVacancies {
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
    public void testLatestVacancies() throws InterruptedException{
        webDriver.get(baseUrl);
        webDriver.findElement(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]")).click();
        Thread.sleep(new Random().nextInt(2000) + 3000);

        webDriver.findElement(By.xpath("//*[@id=\"advertClose\"]")).click();
        Thread.sleep(new Random().nextInt(2000) + 3000);

        WebElement element = webDriver.findElement(By.xpath("/html/body/footer/div[2]/div/div[3]/h3"));
        JavascriptExecutor js = (JavascriptExecutor) webDriver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
        Thread.sleep(3000);
        webDriver.findElement(By.xpath("/html/body/footer/div[2]/div/div[4]/ul/li[10]/a")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[3]/section/div[2]/p[3]/a")).click();
        Thread.sleep(3000);

        js.executeScript("var element = document.evaluate('//*[@id=\"cc-main\"]/div[1]/div/div[2]/div[2]/div[1]/button[1]', document, null, XPathResult.FIRST_ORDERED_NODE_TYPE, null).singleNodeValue; if (element) { element.click(); }");
        Thread.sleep(4000);
       // webDriver.findElement(By.xpath("//*[@id=\"cc-main\"]/div[1]/div/div[2]/div[2]/div[1]/button[1]")).click();

        WebElement interestButton = webDriver.findElement(By.xpath("//*[@id=\"js-careers-jobs-block\"]/div[3]/div/div/div/a"));
        interestButton.click();
        Thread.sleep(4000);
        webDriver.findElement(By.xpath("//*[@id=\"job_seeker_form_job_seeker_first_name\"]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"job_seeker_form_job_seeker_first_name\"]")).sendKeys("Testic");
        webDriver.findElement(By.xpath("//*[@id=\"job_seeker_form_job_seeker_last_name\"]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"job_seeker_form_job_seeker_last_name\"]")).sendKeys("Test");
        webDriver.findElement(By.xpath("//*[@id=\"job_seeker_form_job_seeker_email\"]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"job_seeker_form_job_seeker_email\"]")).sendKeys("testictest333@gmail.com");
        webDriver.findElement(By.xpath("//*[@id=\"job_seeker_form[job_seeker][cv]\"]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"job_seeker_form[job_seeker][cv]\"]")).sendKeys("C:\\Users\\Korisnik\\OneDrive\\Desktop\\CV.pdf");
        Thread.sleep(4000);
        webDriver.findElement(By.xpath("//*[@id=\"job_seeker_form_job_seeker_answers_attributes_0_boolean_answer_true\"]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"job_seeker_form_process_information\"]")).click();
        webDriver.findElement(By.xpath("//*[@id=\"talent-pipeline-fieldset-submit\"]/div/div[3]/button")).click();
        Thread.sleep(5000);




    }




}