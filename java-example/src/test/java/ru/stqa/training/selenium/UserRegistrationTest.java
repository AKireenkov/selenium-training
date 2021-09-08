package ru.stqa.training.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import java.io.IOException;

public class UserRegistrationTest extends TestBase {

    @Test
    public void userRegistrationTest() throws IOException {
        long value = System.currentTimeMillis();
        goTo(property.getProperty("home"));
        click(By.xpath("//a[text()='New customers click here']"));
        fillUserForm(value);
        click(By.name("create_account"));
        click(By.xpath("//a[text()='Logout']"));
        login(value + property.getProperty("email"), property.getProperty("password"));
        click(By.xpath("//a[text()='Logout']"));
    }

    private void fillUserForm(long value) {
        driver.findElement(By.name("firstname")).sendKeys(property.getProperty("firstname"));
        driver.findElement(By.name("lastname")).sendKeys(property.getProperty("lastname"));
        driver.findElement(By.name("address1")).sendKeys(property.getProperty("address1"));
        driver.findElement(By.name("postcode")).sendKeys(property.getProperty("postcode"));
        driver.findElement(By.name("city")).sendKeys(property.getProperty("city"));
        Select dropdown = new Select(driver.findElement(By.name("country_code")));
        dropdown.selectByVisibleText(property.getProperty("country"));
        driver.findElement(By.name("email")).sendKeys(value + property.getProperty("email"));
        driver.findElement(By.name("phone")).sendKeys(property.getProperty("phone"));
        driver.findElement(By.name("password")).sendKeys(property.getProperty("password"));
        driver.findElement(By.name("confirmed_password")).sendKeys(property.getProperty("password"));
    }
}
