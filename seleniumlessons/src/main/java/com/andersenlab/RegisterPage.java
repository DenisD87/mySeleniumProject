package com.andersenlab;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegisterPage {
    private WebDriver driver;

    private By ageButton = By.xpath("//span[contains(text(),'16 or over')]");
    private By dayField = By.xpath("//input[@id='day-input']");
    private By monthField = By.xpath("//input[@id='month-input']");
    private By yearField = By.xpath("//input[@id='year-input']");
    private By continueButton = By.xpath("//button[@id='submit-button']");
    private By passwordField = By.xpath("//input[@id='password-input']");
    private By registerButton = By.xpath("//button[@id='submit-button']");
    private By passwordMessageField = By.xpath("//div[@id='form-message-password']");

    RegisterPage(WebDriver driver) {
        this.driver = driver;
    }

    public void ageButtonClick() {
        sleep(1000);
        driver.findElement(ageButton).click();
    }

    public RegisterPage typeDay(String day) {
        driver.findElement(dayField).sendKeys(day);
        return this;
    }

    public RegisterPage typeMonth(String month) {
        driver.findElement(monthField).sendKeys(month);
        return this;
    }

    public RegisterPage typeYear(String year) {
        driver.findElement(yearField).sendKeys(year);
        return this;
    }

    public RegisterPage dateOfBirthInput(String day, String month, String year) {
        this.typeDay(day);
        this.typeMonth(month);
        this.typeYear(year);
        driver.findElement(continueButton).submit();
        return new RegisterPage(driver);
    }

    public RegisterPage typePassword(String password) {
        driver.findElement(passwordField).sendKeys(password);
        return this;
    }

    public RegisterPage clickRegisterButton(String password) {
        this.typePassword(password);
        driver.findElement(registerButton).submit();
        return new RegisterPage(driver);
    }

    public String getPasswordMessage() {
        return driver.findElement(passwordMessageField).getText();
    }

// Знаю что данное решение не очень, но пока в поисках лучшего...
// Пробовал явное ожидание, но оно не работает, результат такой же как и без него:
// element click intercepted: Element <span>...</span> is not clickable at point
    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
