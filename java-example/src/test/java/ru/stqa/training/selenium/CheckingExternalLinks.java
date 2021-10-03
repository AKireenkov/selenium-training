package ru.stqa.training.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

public class CheckingExternalLinks extends TestBase {
    @Test
    public void checkingExternalLinks() {
        goTo(property.getProperty("adm_home"));
        adminlogin(property.getProperty("ausername"), property.getProperty("apassword"));
        click(By.xpath("//span[text()='Countries']"));
        click(By.xpath("//a[text()=' Add New Country']"));
        List<WebElement> elements = driver.findElements(By.xpath("//i[@class='fa fa-external-link']"));
        for (WebElement element : elements) {
            String originalWindow = driver.getWindowHandle();
            Set<String> existingWindows = driver.getWindowHandles();
            element.click();
            String newWindow = wait.until(anyWindowOtherThan(existingWindows));
            driver.switchTo().window(newWindow);
            driver.close();
            driver.switchTo().window(originalWindow);
        }
    }
}
