package ru.stqa.training.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;

import java.util.List;

public class AddProductToCart extends TestBase {
    @Test
    public void addProductToCartTest() {
        addProductToCart();
        addProductToCart();
        addProductToCart();
        click(By.xpath("//div[@id='cart']//a[3]"));
        delProductFromCart();
        delProductFromCart();
        delProductFromCart();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//em[text()='There are no items in your cart.']")));
    }

    private void addProductToCart() {
        goTo(property.getProperty("home"));
        click(By.xpath("//li[@class='product column shadow hover-light']/a"));
        int value = Integer.parseInt(driver.findElement(By.xpath("//span[@class='quantity']")).getText());
        if (driver.findElements(By.name("options[Size]")).size() > 0) {
            select(By.name("options[Size]")).selectByVisibleText("Medium +$2.50");
        }
        click(By.name("add_cart_product"));
        value++;
        wait.until(ExpectedConditions.textToBe(By.xpath("//span[@class='quantity']"), String.valueOf(value)));
    }

    private void delProductFromCart() {
        List<WebElement> list = driver.findElements(By.xpath("//table[@class='dataTable rounded-corners']//td"));
        System.out.println(driver.findElement(By.name("quantity")).getAttribute("value"));
        int value = Integer.parseInt(driver.findElement(By.name("quantity")).getAttribute("value"));
        if (value > 1) {
            type(By.name("quantity"), String.valueOf(value - 1));
            click(By.name("update_cart_item"));
        } else {
            click(By.name("remove_cart_item"));
        }
        wait.until(ExpectedConditions.stalenessOf(list.get(0)));
    }
}
