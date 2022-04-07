package com.andersenlab;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignInPage {
    private WebDriver driver;

    private By emailField = By.xpath("//div[@id='username']//input");
    private By passwordField = By.xpath("//div[@id='password']//input");
    private By signInButton = By.xpath("//button[@type='submit']");

    SignInPage(WebDriver driver) {
        this.driver = driver;
    }

    public SignInPage typeEmail(String email) {
        driver.findElement(emailField).sendKeys(email);
        return this;
    }

    public SignInPage typePassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    public HomePage loginWithCorrectCreds(String email, String password) {
        this.typeEmail(email);
        this.typePassword(password);
        driver.findElement(signInButton).submit();
        return new HomePage(driver);
    }
}
