package ru.stqa.training.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

public class CheckingBrowserLogs extends TestBase {

    @Test
    public void CheckingBrowserLogsTest() {
        goTo(property.getProperty("adm_home"));
        adminlogin(property.getProperty("ausername"), property.getProperty("apassword"));
        click(By.xpath("//span[text()='Catalog']"));
        click(By.xpath("//a[text()='Rubber Ducks']"));
        List<WebElement> rows = driver.findElements(By.xpath("//img//following-sibling::a"));


        for (int i = 0; i < rows.size(); i++) {
            rows = driver.findElements(By.xpath("//img//following-sibling::a"));
            rows.get(i).click();
            System.out.println(driver.manage().logs().get("browser"));
            driver.navigate().back();
        }

    }
}
