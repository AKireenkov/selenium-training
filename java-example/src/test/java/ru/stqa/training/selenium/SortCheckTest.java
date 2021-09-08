package ru.stqa.training.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class SortCheckTest extends TestBase {

    @Test
    public void sortCheckCountries() {
        goTo(property.getProperty("countries"));
        Adminlogin(property.getProperty("ausername"), property.getProperty("apassword"));
        findValueAndCheckSort(".row td:nth-child(5)");
        List<WebElement> rows = driver.findElements(By.cssSelector(".row"));
        List<String> countries = new ArrayList<>();
        for (WebElement row : rows) {
            List<WebElement> values = row.findElements(By.tagName("td"));
            if (Integer.parseInt(values.get(5).getText()) > 0) {
                countries.add(values.get(4).getText());
            }
        }
        selectListValue(countries);
    }

    @Test
    public void sortCheckGeoZones() {
        goTo(property.getProperty("geozones"));
        Adminlogin(property.getProperty("ausername"), property.getProperty("apassword"));
        List<WebElement> list = driver.findElements(By.cssSelector(".row td:nth-child(3)"));
        List<String> values = new ArrayList<>();
        list.forEach(t -> values.add(t.getText()));
        selectListValue(values);
    }

    private void selectListValue(List<String> values) {
        for (String value : values) {
            driver.findElement(By.xpath(String.format("//a[text()='%s']", value))).click();
            findValueAndCheckSort("#table-zones td:nth-child(3)");
            driver.navigate().back();
        }
    }

    private void findValueAndCheckSort(String value) {
        List<WebElement> list = driver.findElements(By.cssSelector(value));
        List<String> listValue = new ArrayList<>();
        list.forEach(t -> listValue.add(t.getText()));
        listValue.removeIf(t -> t.equals(""));
        List<String> sorted = listValue.stream().sorted().collect(Collectors.toList());
        Assert.assertEquals(listValue, sorted);
    }
}
