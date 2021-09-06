package ru.stqa.training.selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public abstract class TestBase {
    public static ThreadLocal<WebDriver> tlDriver = new ThreadLocal<>();
    public WebDriver driver;
    public WebDriverWait wait;
    FileInputStream fis;
    Properties property = new Properties();


    public void loadProperty() throws IOException {
        fis = new FileInputStream("src/main/resources/config.properties");
        property.load(fis);
    }

    public void Adminlogin(String url, String username, String password) throws IOException {
        loadProperty();
        driver.get(property.getProperty(url));
        driver.findElement(By.name("username")).sendKeys(property.getProperty(username));
        driver.findElement(By.name("password")).sendKeys(property.getProperty(password));
        driver.findElement(By.name("login")).click();
    }

    public void login(String url, String email, String password) throws IOException {
        loadProperty();
        driver.get(property.getProperty(url));
        driver.findElement(By.name("email")).sendKeys(property.getProperty(email));
        driver.findElement(By.name("password")).sendKeys(property.getProperty(password));
        driver.findElement(By.name("login")).click();
    }

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
    @BeforeTest
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

    @AfterTest
    public void stop() {
        driver.quit();
        driver = null;
    }
}
