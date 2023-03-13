package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.google.common.collect.Ordering;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import utils.HelperMethods;

import java.util.ArrayList;
import java.util.Collections;

import static com.codeborne.selenide.Selenide.*;

public class EuropeToursPage {

    private  static final SelenideElement lengthSliderMin = $(By.xpath("//div[H5[contains(text(), 'Length')]]//div[@data-handle= '0']"));
    private  static final SelenideElement lengthSliderMax = $(By.xpath("//div[H5[contains(text(), 'Length')]]//div[@data-handle= '1']"));
    private static final ElementsCollection tourLengthList = $$(By.xpath("//dt[text() = 'Tour length']/following-sibling::dd[1]"));
    private static final SelenideElement tourList = $(By.xpath("//div[@class = 'js-serp-tour-list list']"));
    private static final SelenideElement sortDropdown = $(By.xpath("//h5[text() ='Sort by']/following-sibling::select"));
    private static final ElementsCollection totalPriceList = $$(By.xpath("//span[text()='€']/following-sibling::span"));

    public static void setLengthTo(int min, int max) {

        if (min > 1 && min <= 21) {
            min = min -1;
        } else {min = 0;}

        if (max < 21 && max >= 1 && max >= min) {
            max = 21 - max;
        } else {max = 0;}

        for (int i = 1; i <= min ; i++) {
            lengthSliderMin.sendKeys(Keys.ARROW_RIGHT);
        }
        for (int i = 1; i <= max ; i++) {
            lengthSliderMax.sendKeys(Keys.ARROW_LEFT);
        }
    }


    public static void verifyResultsTourLengthBetween(Integer minDays, Integer maxDays) {

        refresh();
        tourList.shouldBe(Condition.visible);

        ArrayList<Integer> resultsTourLength = new ArrayList<>();
        for (SelenideElement result: tourLengthList){
            resultsTourLength.add(Integer.valueOf(result.getText().replace(" days", "")));
        }
        Assert.assertTrue(HelperMethods.checkValuesInRange(minDays, maxDays, resultsTourLength));
        }

    public static void sortBy(String sortOption) {
        sortDropdown.selectOption(sortOption);
        tourList.shouldBe(Condition.visible);
    }

    public static void verifyTotalPriceSort(Boolean descOrder) throws InterruptedException {
        ArrayList<Double> resultsTotalPrice = new ArrayList<>();

        for (SelenideElement result: totalPriceList){
            resultsTotalPrice.add(Double.valueOf(result.getText().replace(",", ".")));
        }

        if (descOrder) {Assert.assertTrue(Ordering.natural().reverse().isOrdered(resultsTotalPrice));
        } else {Assert.assertTrue(Ordering.natural().isOrdered(resultsTotalPrice));
        }
    }
}
