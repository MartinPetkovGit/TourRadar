package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import com.codeborne.selenide.SelenideElement;
import com.sun.source.tree.AssertTree;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import utils.HelperMethods;

import java.util.ArrayList;

import static com.codeborne.selenide.Selenide.*;

public class EuropeToursPage {

    private  static SelenideElement lengthSliderMin = $(By.xpath("//div[H5[contains(text(), 'Length')]]//div[@data-handle= '0']"));
    private  static SelenideElement lengthSliderMax = $(By.xpath("//div[H5[contains(text(), 'Length')]]//div[@data-handle= '1']"));
    private static ElementsCollection tourLengthList = $$(By.xpath("//dt[text() = 'Tour length']/following-sibling::dd[1]"));
    private static SelenideElement tourList = $(By.xpath("//div[@class = 'js-serp-tour-list list']"));

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
    }

    public static void verifyPriceSort(String ascOrDesc) {
    }
}
