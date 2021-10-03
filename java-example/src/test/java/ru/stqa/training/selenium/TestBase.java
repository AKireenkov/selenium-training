package ru.stqa.training.selenium;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.Set;

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

    public void adminlogin(String username, String password) {
        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("login")).click();
    }

    public void login(String email, String password) {
        driver.findElement(By.name("email")).sendKeys(email);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("login")).click();
    }

    public void goTo(String url) {
        driver.get(url);
    }

    public void click(By locator) {
        driver.findElement(locator).click();
    }

    public void type(By locator, String text) {
        click(locator);
        if (text != null) {
            String existingText = driver.findElement(locator).getAttribute("value");
            if (!text.equals(existingText)) {
                driver.findElement(locator).clear();
                driver.findElement(locator).sendKeys(text);
            }
        }
    }

    public void attach(By locator, File file) {
        if (file != null) {
            driver.findElement(locator).sendKeys(file.getAbsolutePath());
        }
    }

    public Select select(By locator) {
        Select value = new Select(driver.findElement(locator));
        return value;
    }

    public ExpectedCondition<String> anyWindowOtherThan(Set<String> oldWindows) {   //возвращает id нового открытого окна
        return new ExpectedCondition<String>() {
            public String apply(WebDriver driver) {
                Set<String> handles = driver.getWindowHandles();
                handles.removeAll(oldWindows);
                return handles.size() > 0 ? handles.iterator().next() : null;
            }
        };
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
    public void start() throws IOException {
        loadProperty();
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
