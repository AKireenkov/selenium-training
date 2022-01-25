package ru.stqa.training.selenium.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import ru.stqa.training.selenium.TestBase;

public class MainPage extends TestBase {
    /**
     * конструктор класса, занимающийся инициализацией полей класса
     */
    //ПОРАБОТАТЬ С WEBDRIVER
    WebDriver driver;
    public MainPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        this.driver = driver;
    }

    @FindBy(xpath = "//li[@class='product column shadow hover-light']/a")
    public WebElement item;


    public void selectFirstItem() {
        item.click();
    }
}
