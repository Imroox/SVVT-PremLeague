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

public class SeleniumIntroTest {
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
    public void testRegistration() throws InterruptedException {

        webDriver.get("https://users.premierleague.com/users/register/personal");
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]")).click();
        // Fill out the registration form
        webDriver.findElement(By.id("ism-first-name")).sendKeys("Test");
        webDriver.findElement(By.id("ism-last-name")).sendKeys("User");

        Thread.sleep(2000);
        webDriver.findElement(By.id("ism-email")).sendKeys("aliimranalic6@gmail.com");
        webDriver.findElement(By.id("ism-new-password")).sendKeys("SecurePass123!");
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/form/div[6]/div/ul/li[1]/label/span")).click();


        // Select date of birth (example format; update selectors as needed)
        webDriver.findElement(By.id("ism-dob-day")).sendKeys("01");
        webDriver.findElement(By.id("ism-dob-month")).sendKeys("01");
        webDriver.findElement(By.id("ism-dob-year")).sendKeys("2000");

        webDriver.findElement(By.xpath("//*[@id=\"ism-region\"]/option[2]")).click();

        Thread.sleep(3000);

        webDriver.findElement(By.cssSelector("#root > div > div > div:nth-child(4) > form > div.Button__ButtonWrap-sc-1vjeq7p-0.jxfNhP > button")).click();
        Thread.sleep(3000);

        webDriver.findElement(By.xpath("/html/body/div[1]/div/div/div[2]/form/div[1]/fieldset/ul/li[11]/label/div/div")).click();

        Thread.sleep(2000);

        webDriver.findElement(By.cssSelector("#root > div > div > div:nth-child(4) > form > div:nth-child(5) > div:nth-child(1) > div > button")).click();

        Thread.sleep(3000);


        webDriver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/form/div[5]/div/div/label/span")).click();

        Thread.sleep(2000);

        webDriver.findElement(By.cssSelector("#root > div > div > div:nth-child(4) > form > div:nth-child(11) > div:nth-child(1) > div > button")).click();

        Thread.sleep(3000);


        WebElement errorMessageElement = webDriver.findElement(By.cssSelector("#root > div > div > div:nth-child(4) > form > div:nth-child(9) > div:nth-child(2) > div.Copy-sc-1hw5ifr-0.eXoUVy > p")); // Adjust the selector if needed
        String actualErrorMessage = errorMessageElement.getText();


        String expectedErrorMessage = "aliimranalic6@gmail.com already has a premierleague account. Please choose a different email address or login to your existing account.";
        assertEquals(expectedErrorMessage, actualErrorMessage);
    }


    @Test
    public void testLogin() throws InterruptedException {
        webDriver.get("https://users.premierleague.com/?redirect_uri=https://www.premierleague.com/&app=pl-web");

        webDriver.findElement(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]")).click();


        Thread.sleep(3000);

        WebElement emailField = webDriver.findElement(By.xpath("//*[@id=\"ism-email\"]"));
        WebElement passwordField = webDriver.findElement(By.xpath("//*[@id=\"ism-password\"]"));

        // Type email slower with delay between each character
        String email = "aliimranalic6@gmail.com";
        for (char c : email.toCharArray()) {
            emailField.sendKeys(String.valueOf(c));
            Thread.sleep(500); // Wait 100ms between each character
        }
        Thread.sleep(400);

        // Type password slower with delay between each character
        String password = "JamieVardy9$";
        for (char c : password.toCharArray()) {
            passwordField.sendKeys(String.valueOf(c));
            Thread.sleep(700); // Wait 100ms between each character
        }

        Thread.sleep(2000);


        webDriver.findElement(By.cssSelector("#ismr-content > form > div > div:nth-child(1) > div:nth-child(4) > button")).click();

        Thread.sleep(10000);

        WebElement passwordField1 = webDriver.findElement(By.xpath("//*[@id=\"ism-password\"]"));


        String password1 = "JamieVardy9$";
        for (char c : password1.toCharArray()) {
            passwordField1.sendKeys(String.valueOf(c));
            Thread.sleep(500); // Wait 100ms between each character
        }

        Thread.sleep(3000);


        webDriver.findElement(By.cssSelector("#ismr-content > form > div > div:nth-child(1) > div:nth-child(4) > button")).click();

        Thread.sleep(6000);


        webDriver.findElement(By.cssSelector("#mainNav > a.navLink.navOption.navOption--no-border.fantasySignOut > span")).click();


    }

    @Test
    public void checkStatsOfPlayer() throws InterruptedException {
        webDriver.get("https://www.premierleague.com/");
        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.xpath("/html/body/header/div/nav/ul/li[13]/a")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.xpath("//*[@id=\"search-input\"]")).sendKeys("Jamie Vardy");
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[2]/header/div/div/div/div")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[2]/div/div/div/table/tbody/tr/td[1]/a")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.cssSelector("#mainContent > div.wrapper.hasFixedSidebar > div > div > nav > ul > li:nth-child(2) > a")).click();
        Thread.sleep(10000);

        WebElement totalGoalsStat = webDriver.findElement(By.cssSelector("#mainContent > div.wrapper.hasFixedSidebar > div > div > div > div > div > div:nth-child(2) > span > span"));
        String actualTotalGoals = totalGoalsStat.getText();
        String expectedTotalGoals = "142";
        assertEquals(expectedTotalGoals, actualTotalGoals);


        WebElement totalAsistStat = webDriver.findElement(By.cssSelector("#mainContent > div.wrapper.hasFixedSidebar > div > div > div > div > ul > li:nth-child(2) > div > div:nth-child(2) > span"));
        String actualAsistStat = totalAsistStat.getText();
        String expectedAsistStat = "47";
        assertEquals(expectedAsistStat, actualAsistStat);


    }


    @Test
    public void checkClubStadiumCapacity() throws InterruptedException {
        webDriver.get("https://www.premierleague.com/");

        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]")).click();
        Thread.sleep(2000);
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


    @Test
    public void checkMostPowerfulGoal() throws InterruptedException {
        webDriver.get("https://www.premierleague.com/");
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("/html/body/header/div/nav/ul/li[14]/a")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/nav/ul/li[2]/a")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[3]/div/section/div[1]/div[2]")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[3]/div/section/div[1]/div[3]/ul/li[2]")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[3]/div/section/div[2]/div[2]")).click();
        Thread.sleep(4000);
        webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[3]/div/section/div[2]/div[3]/ul/li[15]")).click();
        Thread.sleep(4000);

        WebElement Fernandinho = webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[3]/div/div[1]/div[1]/div/ul/li[1]/div[1]/span[1]"));
        assertEquals("Fernandinho", Fernandinho.getText(), "Player's name should match Fernandinho.");

        WebElement MoussaDiaby = webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[3]/div/div[1]/div[1]/div/ul/li[3]/div[1]/span[1]"));
        assertTrue(MoussaDiaby.isDisplayed(), "Moussa Diaby's name should be displayed.");

        WebElement SaïdBenrahma = webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[3]/div/div[1]/div[1]/div/ul/li[2]/div[1]/span[1]"));
        assertNotNull(SaïdBenrahma, "Saïd Benrahma's element should not be null.");

    }


    @Test
    public void checkClubDirectory() throws InterruptedException {
        webDriver.get("https://www.premierleague.com/");
        Thread.sleep(3000);
        webDriver.findElement(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.cssSelector("body > header > div > nav > ul > li:nth-child(12) > a")).click();
        Thread.sleep(4000);
        webDriver.findElement(By.cssSelector("#search-input")).sendKeys("Leicester City");
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[2]/header/div/div/div/div")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[2]/div/div/div[2]/div/table/tbody/tr/td[1]/a/div[1]/div[1]")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.cssSelector("#mainContent > nav > ul > li:nth-child(9) > a")).click();
        Thread.sleep(3000);

        WebElement Chairman = webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[2]/section/div[2]/section/div[2]/div[1]/div/div[2]/p"));
        assertEquals("Aiyawatt Srivaddhanaprabha ", Chairman.getText(), "Chairman's name should match Aiyawatt Srivaddhanaprabha");

        WebElement ChiefExecutive = webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[2]/section/div[2]/section/div[2]/div[2]/div[2]/div[1]/div/div[2]/p"));
        assertTrue(ChiefExecutive.isDisplayed(), "Chief Executive's name should match Susan Whelan");

    }

    @Test
    public void checkPlayerComparison() throws InterruptedException {
        webDriver.get("https://www.premierleague.com/");
        Thread.sleep(3000);
        webDriver.findElement(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]")).click();
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("/html/body/header/div/nav/ul/li[7]/a")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/nav/ul/li[8]/a")).click();
        Thread.sleep(100);
        webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[2]/div/section[2]/div[1]/div[1]/div/div[1]/span")).click();
        Thread.sleep(10000);
        webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[2]/div/section[1]/div[2]/div[1]/nav/ul/li[2]")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[2]/div/section[1]/div[2]/div[1]/div/div[2]/div/input")).sendKeys("Didier Drogba");
        Thread.sleep(2000);
        webDriver.findElement(By.cssSelector("#mainContent > div.wrapper.col-12 > div > section.player-comparison__modal > div.player-comparison__box > div.player-comparison__results-wrap.searchActive > div.player-comparison__search-results-scroll > div > ul > li > span.player-comparison__result-name")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[2]/div/section[2]/div[1]/div[2]/div/div[1]/span")).click();
        Thread.sleep(3000);
        webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[2]/div/section[1]/div[2]/div[1]/div/div[2]/div/input")).sendKeys("Peter Crouch");
        Thread.sleep(2000);

        WebElement DrogbaGoals = webDriver.findElement(By.xpath("//*[@id=\"mainContent\"]/div[2]/div/section[2]/div[3]/ul/li[1]/div[2]/div[1]/div[1]/div[2]/span"));
        assertEquals("254", DrogbaGoals.getText(), "Amount of goals that Drogba scored should be 254");




    }

    @Test
    public void testSearchFunctionality() throws InterruptedException {
        webDriver.get("https://www.premierleague.com/");
        Thread.sleep(2000);

        webDriver.findElement(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]")).click();
        Thread.sleep(2000);

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
    public void testNavigationLinks() throws InterruptedException {
        webDriver.get("https://www.premierleague.com/");
        Thread.sleep(2000);

        webDriver.findElement(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]")).click();
        Thread.sleep(2000);

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
    public void testEnforceHttps() {
        webDriver.get("http://www.premierleague.com/");
        String currentUrl = webDriver.getCurrentUrl();
        assertTrue(currentUrl.startsWith("https://"), "URL should be HTTPS");
    }

    @Test
    public void testInvalidEmail() throws InterruptedException {
        webDriver.get("https://users.premierleague.com/users/register/personal");
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]")).click();
        // Fill out the registration form
        webDriver.findElement(By.id("ism-first-name")).sendKeys("Test");
        webDriver.findElement(By.id("ism-last-name")).sendKeys("User");

        Thread.sleep(2000);
        webDriver.findElement(By.id("ism-email")).sendKeys("aliimranalic6gmail.com");
        webDriver.findElement(By.xpath("//*[@id=\"ism-new-password\"]")).click();
        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//*[@id=\"ism-new-password\"]")).sendKeys("SecurePass123!");
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/form/div[6]/div/ul/li[1]/label/span")).click();


        // Select date of birth (example format; update selectors as needed)
        webDriver.findElement(By.id("ism-dob-day")).sendKeys("01");
        webDriver.findElement(By.id("ism-dob-month")).sendKeys("01");
        webDriver.findElement(By.id("ism-dob-year")).sendKeys("2000");

        webDriver.findElement(By.xpath("//*[@id=\"ism-region\"]/option[2]")).click();

        Thread.sleep(3000);

        webDriver.findElement(By.cssSelector("#root > div > div > div:nth-child(4) > form > div.Button__ButtonWrap-sc-1vjeq7p-0.jxfNhP > button")).click();
        Thread.sleep(3000);

        WebElement errorMessage = webDriver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/form/div[3]/div/div[2]/div/p"));
        assertTrue(errorMessage.isDisplayed(), "Invalid Email Address should be displayed");
    }


    @Test
    public void testInvalidDate() throws InterruptedException {
        webDriver.get("https://users.premierleague.com/users/register/personal");
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"onetrust-accept-btn-handler\"]")).click();
        // Fill out the registration form
        webDriver.findElement(By.id("ism-first-name")).sendKeys("Test");
        webDriver.findElement(By.id("ism-last-name")).sendKeys("User");

        Thread.sleep(2000);
        webDriver.findElement(By.id("ism-email")).sendKeys("aliimranalic6@gmail.com");
        webDriver.findElement(By.xpath("//*[@id=\"ism-new-password\"]")).click();
        Thread.sleep(1000);
        webDriver.findElement(By.xpath("//*[@id=\"ism-new-password\"]")).sendKeys("JamieVardy223!");
        Thread.sleep(2000);
        webDriver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/form/div[6]/div/ul/li[1]/label/span")).click();


        // Select date of birth (example format; update selectors as needed)
        webDriver.findElement(By.id("ism-dob-day")).sendKeys("01");
        webDriver.findElement(By.id("ism-dob-month")).sendKeys("011");
        webDriver.findElement(By.id("ism-dob-year")).click();
        webDriver.findElement(By.id("ism-dob-year")).sendKeys("2000");

        webDriver.findElement(By.xpath("//*[@id=\"ism-region\"]/option[2]")).click();

        Thread.sleep(3000);

        webDriver.findElement(By.cssSelector("#root > div > div > div:nth-child(4) > form > div.Button__ButtonWrap-sc-1vjeq7p-0.jxfNhP > button")).click();
        Thread.sleep(3000);

        WebElement errorMessage = webDriver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/form/div[7]/div/div[2]"));
        assertTrue(errorMessage.isDisplayed(), "Enter a number between 1-12 should be displayed");

        WebElement errorMessage2 = webDriver.findElement(By.xpath("//*[@id=\"root\"]/div/div/div[2]/form/div[7]/div/div[3]/p"));
        assertTrue(errorMessage2.isDisplayed(),"Invalid Date should be displayed");

    }










}

