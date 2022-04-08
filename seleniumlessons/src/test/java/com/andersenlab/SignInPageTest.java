package com.andersenlab;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class SignInPageTest {
    private WebDriver driver;
    private SignInPage signInPage;

    @BeforeEach
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get("https://session.bbc.com/session?ptrt=https%3A%2F%2Fwww.bbc.com%2F&context=homepage&userOrigin=HOMEPAGE_GNL");
        signInPage = new SignInPage(driver);
    }

    @Test
    public void authorization() {
        HomePage homePage = signInPage.loginWithCorrectCreds("ife87855@mzico.com", "123456q7");
        Assertions.assertEquals("BBC - Homepage", homePage.getTitle());
        Assertions.assertEquals("Your account", homePage.getAccountButtonText());
    }

    @AfterEach
    public void tearDown() {
        driver.close();
    }
}
