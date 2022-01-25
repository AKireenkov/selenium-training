package ru.stqa.training.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.Test;
import ru.stqa.training.selenium.pages.CartPage;
import ru.stqa.training.selenium.pages.ItemPage;
import ru.stqa.training.selenium.pages.MainPage;

import java.util.List;

public class AddProductToCartPageObject extends TestBase {

    @Test
    public void addProductToCartTest() {
        addProductToCart();
        click(By.xpath("//div[@id='cart']//a[3]"));
        cartPage.delProductFromCart();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//em[text()='There are no items in your cart.']")));
    }

    private void addProductToCart() {
        goTo(property.getProperty("home"));
        mainPage.selectFirstItem();
        itemPage.clickAddToCart();
    }

}
