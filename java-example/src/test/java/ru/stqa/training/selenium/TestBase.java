package ru.stqa.training.selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    public WebDriver driver;
    public WebDriverWait wait;

    public boolean isElementPresent(By locator) {    //если элемент не найден
        try {
            wait.until((WebDriver d) -> d.findElement(locator));
            // driver.findElement(locator);
            return true;
        } catch (TimeoutException ex) {
            return false;
        }
    }

    public boolean areElementsPresent(By locator) {      //исключение для некорректного локатора в findElements
        try {
            return driver.findElements(locator).size() > 0;
        } catch (StaleElementReferenceException ex) {
            return false;
        }
    }

    @Deprecated
    @BeforeMethod
    public void start() {
        if (tlDriver.get() != null) {
            driver = tlDriver.get();
            wait = new WebDriverWait(driver, 10);
            return;
        }
        driver = new ChromeDriver();
        //driver.manage().timeouts().implicitlyWait(5,TimeUnit.SECONDS);
        tlDriver.set(driver);
        System.out.println(((HasCapabilities) driver).getCapabilities());
        wait = new WebDriverWait(driver, 10);
    }

    @AfterMethod
    public void stop() {
        driver.quit();
        driver = null;
    }
}
