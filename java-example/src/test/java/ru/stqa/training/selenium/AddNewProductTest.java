package ru.stqa.training.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.File;
import java.util.List;
import java.util.stream.Collectors;

public class AddNewProductTest extends TestBase {

    @Test
    public void addNewProductTest() {
        goTo(property.getProperty("adm_home"));
        adminlogin(property.getProperty("ausername"), property.getProperty("apassword"));
        click(By.xpath("//span[text()='Catalog']"));
        click(By.xpath("//a[contains(text(), 'Add New Product')]"));
        fillNewProductForm();
        click(By.xpath("//a[text()='Rubber Ducks']"));
        List<String> listNames = driver.findElements(By.xpath("//tr[@class='row']//td[3]//a"))
                .stream()
                .map(WebElement::getText)
                .collect(Collectors.toList());
        Assert.assertTrue(listNames.contains("new product"));
    }

    private void fillNewProductForm() {
        click(By.xpath("//input[@type='radio']"));
        type(By.name("name[en]"), "new product");
        type(By.name("code"), "00000");
        click(By.xpath(String.format(("//td[text()='%s']/preceding-sibling::td/input"), " Rubber Ducks")));
        click(By.xpath(String.format(("//td[text()='%s']/preceding-sibling::td/input"), "Female")));
        type(By.name("quantity"), "44");
        Select soldOutStatus = new Select(driver.findElement(By.name("sold_out_status_id")));
        soldOutStatus.selectByVisibleText("Temporary sold out");
        File img = new File("src/main/resources/img.PNG");
        attach(By.name("new_images[]"), img);
        type(By.name("date_valid_from"), "01.01.2000");
        type(By.name("date_valid_to"), "01.01.2023");

        click(By.xpath("//a[text()='Information']"));
        Select manufacturer = new Select(driver.findElement(By.name("manufacturer_id")));
        manufacturer.selectByVisibleText("ACME Corp.");
        type(By.name("keywords"), "keywords");
        type(By.name("short_description[en]"), "test test");
        type(By.xpath("//div[@class='trumbowyg-editor']"), "TEST TEST TEST");
        type(By.name("head_title[en]"), "title");
        type(By.name("meta_description[en]"), "meta description");

        click(By.xpath("//a[text()='Prices']"));
        type(By.name("purchase_price"), "999");
        Select priceCurrencyCode = new Select(driver.findElement(By.name("purchase_price_currency_code")));
        priceCurrencyCode.selectByVisibleText("Euros");
        type(By.name("prices[USD]"), "1111");
        type(By.name("prices[EUR]"), "2222");

        click(By.name("save"));
    }
}
