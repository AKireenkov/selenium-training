package ru.stqa.training.selenium;

import org.assertj.core.api.SoftAssertions;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CheckItemInfo extends TestBase {
    public SoftAssertions softly = new SoftAssertions();

    @Test
    public void checkItemInfo() throws IOException {

        login("home", "username", "password");
        List<String> before = getValues("#box-campaigns .name");
        driver.findElement(By.cssSelector("#box-campaigns .link")).click();
        List<String> after = getValues("#box-product .title");

        System.out.println(before);
        System.out.println(after);
        softly.assertThat(before).isEqualTo(after);
        softly.assertAll();

    }

    private List<String> getValues(String value) {
        List<String> values = new ArrayList<>();
        String name, regularPrice, campaignPrice, colorRegularPrice, colorCampaignPrice;
        int sizeRegularPriceWidth, sizeRegularPriceHeight, sizeCampaignPriceWidth, sizeCampaignPriceHeight;
        values.add(name = driver.findElement(By.cssSelector(value)).getText());
        values.add(regularPrice = driver.findElement(By.cssSelector(".regular-price")).getText().replace("$", ""));
        values.add(campaignPrice = driver.findElement(By.cssSelector(".campaign-price")).getText().replace("$", ""));

        /**
         * записываем цвет
         * считаем, что, цвет серый если: первые три числа - равны, цвет красный, если первое число не 0, второе и третье - 0
         */
        colorRegularPrice =
                driver.findElement(By.cssSelector(".regular-price"))
                        .getCssValue("color").replaceAll("[rgba(;);\\s]", "");
        String[] cr = colorRegularPrice.split(",");
        if (cr[0].equals(cr[1]) & cr[0].equals(cr[2])) {
            values.add("gray");
        } else {
            values.add("not gray");
        }

        colorCampaignPrice =
                driver.findElement(By.cssSelector(".campaign-price"))
                        .getCssValue("color").replaceAll("[rgba(;);\\s]", "");
        String[] cc = colorCampaignPrice.split(",");
        if (!cc[0].equals("0") & cc[1].equals("0") & cc[1].equals(cc[2])) {
            values.add("red");
        } else {
            values.add("not red");
        }

        /**
         * ширина и высота обычной цены
         */
        sizeRegularPriceWidth = driver.findElement(By.cssSelector(".regular-price")).getSize().getWidth();
        sizeRegularPriceHeight = driver.findElement(By.cssSelector(".regular-price")).getSize().getHeight();
        /**
         * ширина и высота акционной цены
         */
        sizeCampaignPriceWidth = driver.findElement(By.cssSelector(".campaign-price")).getSize().getWidth();
        sizeCampaignPriceHeight = driver.findElement(By.cssSelector(".campaign-price")).getSize().getHeight();
        if ((sizeRegularPriceWidth * sizeRegularPriceHeight) < (sizeCampaignPriceWidth * sizeCampaignPriceHeight)) {
            values.add("campaign price is higher than regular");
        } else {
            values.add("campaign price is less than regular");
        }
        return values;
    }
}

