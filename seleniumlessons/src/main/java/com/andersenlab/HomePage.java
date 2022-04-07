package com.andersenlab;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class HomePage {
    private WebDriver driver;

    private By accountButton = By.xpath("//span[@id='idcta-username']");

    HomePage(WebDriver driver) {
        this.driver = driver;
    }

    public String getTitle() {
        return driver.getTitle();
    }

    public String getAccountButtonText() {
        return driver.findElement(accountButton).getText();
    }
}
