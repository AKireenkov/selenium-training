package ru.stqa.training.selenium.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.stqa.training.selenium.TestBase;

import java.util.List;

public class ItemPage extends TestBase {

    WebDriver driver;
    public ItemPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//span[@class='quantity']")
    WebElement quantityInCart;

    @FindBy(name = "options[Size]")
    List<WebElement> listSize;

    @FindBy(name = "add_cart_product")
    WebElement addToCartButton;

    public void clickAddToCart() {
        int value = Integer.parseInt(quantityInCart.getText());
        if (listSize.size() > 0) {
            select(By.name("options[Size]")).selectByVisibleText("Medium +$2.50");
        }
        addToCartButton.click();
        value++;
        String a = String.valueOf(value);
        wait.until(ExpectedConditions.textToBe(By.xpath("//span[@class='quantity']"), a));
    }
}
