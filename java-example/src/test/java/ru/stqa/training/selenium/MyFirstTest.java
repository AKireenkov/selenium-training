package ru.stqa.training.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;

public class MyFirstTest extends TestBase {

    @Test
    public void liteCartCheckSection() {
        driver.get("http://localhost/litecart/admin/?app=appearance&doc=template");
        driver.findElement(By.name("username")).sendKeys("admin");
        driver.findElement(By.name("password")).sendKeys("admin");
        driver.findElement(By.name("login")).click();
        Assert.assertTrue(isElementPresent(By.cssSelector(".logotype")));

        List<WebElement> elementsOf1Tier = driver.findElements(By.cssSelector("#app-"));
        int sizeOf1Tier = elementsOf1Tier.size();

        for (int i = 0; i < sizeOf1Tier; i++) {
            elementsOf1Tier = driver.findElements(By.cssSelector("#app-"));
            elementsOf1Tier.get(i).click();
            Assert.assertTrue(isElementPresent(By.cssSelector("h1[style]")));

            List<WebElement> elementsOf2Tier = driver.findElements(By.cssSelector(".docs li"));
            int sizeOf2Tier = elementsOf2Tier.size();
            if (sizeOf2Tier > 0) {
                for (int a = 0; a < sizeOf2Tier; a++) {
                    elementsOf2Tier = driver.findElements(By.cssSelector(".docs li"));
                    elementsOf2Tier.get(a).click();
                    Assert.assertTrue(isElementPresent(By.cssSelector("h1[style]")));
                }
            }
        }
    }

    @Test
    public void liteCartCheckStickers() {
        driver.get("http://localhost/litecart/en/");
        driver.findElement(By.name("email")).sendKeys("test@test.ru");
        driver.findElement(By.name("password")).sendKeys("test");
        driver.findElement(By.name("login")).click();
        wait.until(elementToBeClickable(By.linkText("Logout")));

        List<WebElement> elements = driver.findElements(By.cssSelector(".image-wrapper div"));
        for (int i = 0; i < elements.size(); i++) {
            System.out.println(i);
            Assert.assertTrue(elements.get(i).getAttribute("class").contains("sticker "));
        }
    }

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
