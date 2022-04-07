package com.andersenlab;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class RegisterPageTest {
    private WebDriver driver;
    private RegisterPage registerPage;

    @Before
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        driver.manage().window().maximize();
        driver.get("https://account.bbc.com/register?action=sign-in&clientId=Account&context=homepage&isCasso=false&nonce=EV5axn2y-hbwadfDi120gr_-OoT0uVeEaYmU&ptrt=https%3A%2F%2Fwww.bbc.com%2F&realm=%2F&redirectUri=https%3A%2F%2Fsession.bbc.com%2Fsession%2Fcallback%3Frealm%3D%2F&service=IdSignInService&userOrigin=HOMEPAGE_GNL");
        registerPage = new RegisterPage(driver);
        registerPage.ageButtonClick();
        registerPage.dateOfBirthInput("09", "11", "1987");
    }

    @Test
    public void enteringEmptyPasswordDuringRegistration() {
        registerPage.clickRegisterButton("");
        Assert.assertEquals("Чего-то не хватает. Пожалуйста, проверьте и попробуйте ещё раз.", registerPage.getPasswordMessage());
    }

    @Test
    public void enteringShortOnePasswordDuringRegistration() {
        registerPage.clickRegisterButton("1");
        Assert.assertEquals("Извините, этот пароль слишком короткий. В нём должно быть не менее 8 символов.", registerPage.getPasswordMessage());
    }

    @Test
    public void enteringShortSevenPasswordDuringRegistration() {
        registerPage.clickRegisterButton("123q456");
        Assert.assertEquals("Извините, этот пароль слишком короткий. В нём должно быть не менее 8 символов.", registerPage.getPasswordMessage());
    }

    @Test
    public void enteringRandomNumbersPasswordDuringRegistration() {
        registerPage.clickRegisterButton("12345678");
        Assert.assertEquals("Извините, этот пароль недействителен. Пожалуйста, включите одну букву.", registerPage.getPasswordMessage());
    }

    @Test
    public void enteringRandomSymbolsPasswordDuringRegistration() {
        registerPage.clickRegisterButton("!@#$%ячс");
        Assert.assertEquals("Извините, этот пароль недействителен. Пожалуйста, включите одну букву.", registerPage.getPasswordMessage());
    }

    @Test
    public void enteringRandomSymbolsAndNumbersPasswordDuringRegistration() {
        registerPage.clickRegisterButton("!@#1%2чс");
        Assert.assertEquals("Извините, этот пароль недействителен. Пожалуйста, включите одну букву.", registerPage.getPasswordMessage());
    }

    @Test
    public void enteringRandomLettersPasswordDuringRegistration() {
        registerPage.clickRegisterButton("qwertyui");
        Assert.assertEquals("Извините, этот пароль недействителен. Пожалуйста, включите что-нибудь, кроме букв.", registerPage.getPasswordMessage());
    }

    @After
    public void tearDown() {
        driver.close();
    }
}
