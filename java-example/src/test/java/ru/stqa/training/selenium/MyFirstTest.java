package ru.stqa.training.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class MyFirstTest extends TestBase {
    
    @Test
    public void waitTest() {
        driver.get("https://www.google.com");
        driver.findElement(By.name("q")).sendKeys("123");
        WebElement btnG = driver.findElement(By.name("btnK"));
        btnG.click();
        Assert.assertTrue(isElementPresent(By.cssSelector(".rc")));
    }

    @Test
    public void liteCartTest() {
        driver.get("http://localhost/litecart/en/");
        driver.findElement(By.name("email")).sendKeys("test@test.ru");
        driver.findElement(By.name("password")).sendKeys("test");
        driver.findElement(By.name("login")).click();
        wait.until(elementToBeClickable(By.linkText("Logout")));
    }

    @Test
    public void myFirstTest() {
        driver.get("https://www.google.com");
        driver.findElement(By.name("q")).sendKeys("123");
        driver.findElement(By.name("login")).click();
    }
}
