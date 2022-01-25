package ru.stqa.training.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.stqa.training.selenium.TestBase;

import java.util.List;

public class CartPage extends TestBase {

    WebDriver driver;
    public CartPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//em[text()='There are no items in your cart.']")
    WebElement noItemsScript;

    @FindBy(name = "quantity")
    WebElement quantityItem;

    @FindBy(name = "update_cart_item")
    WebElement updateButton;

    @FindBy(name = "remove_cart_item")
    WebElement removeButton;

    @FindBy(xpath = "//table[@class='dataTable rounded-corners']//td")
    List<WebElement> quantityItemsInTable;

    public void delProductFromCart() {
        System.out.println(quantityItem.getAttribute("value"));
        int value = Integer.parseInt(quantityItem.getAttribute("value"));
        if (value > 1) {
            type(By.name("quantity"), String.valueOf(value - 1));
            updateButton.click();
        } else {
            removeButton.click();
        }
        wait.until(ExpectedConditions.stalenessOf(quantityItemsInTable.get(0)));
    }
}
