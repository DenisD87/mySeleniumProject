package com.andersenlab;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

public class RegisterPageTest {
    private WebDriver driver;
    private RegisterPage registerPage;

    @BeforeEach
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

    @DisplayName("Ввод пароля при регистрации")
    @ParameterizedTest
    @CsvSource({"'Чего-то не хватает. Пожалуйста, проверьте и попробуйте ещё раз.',''",
            "'Извините, этот пароль слишком короткий. В нём должно быть не менее 8 символов.', '1'",
            "'Извините, этот пароль слишком короткий. В нём должно быть не менее 8 символов.','123q456'",
            "'Извините, этот пароль недействителен. Пожалуйста, включите одну букву.','12345678'",
            "'Извините, этот пароль недействителен. Пожалуйста, включите одну букву.','!@#$%ячс'",
            "'Извините, этот пароль недействителен. Пожалуйста, включите одну букву.','!@#1%2чс'",
            "'Извините, этот пароль недействителен. Пожалуйста, включите что-нибудь, кроме букв.','qwertyui'"})
    public void enteringPasswordDuringRegistration(String expected, String actural) {
        registerPage.clickRegisterButton(actural);
        Assertions.assertEquals(expected, registerPage.getPasswordMessage());
    }

    @AfterEach
    public void tearDown() {
        driver.close();
    }
}
