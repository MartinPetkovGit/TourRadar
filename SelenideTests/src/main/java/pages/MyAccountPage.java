package pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.WebDriverRunner.getWebDriver;


public class MyAccountPage {

    private static SelenideElement myAccountIcon = $(By.xpath("//nav[h4[contains(text(), 'My Account')]]/../preceding-sibling::div"));
    private static SelenideElement myAccountIconLoggedIn = $(By.xpath("//a[span[@class = 'initials']]"));
    private static SelenideElement myAccountLoginButton = $(By.xpath("//nav[h4[contains(text(), 'My Account')]]//a[contains(text(), 'Log In')]"));
    private static SelenideElement myAccountPageUsername = $(By.xpath("//h1[contains(text() , 'Hello ')]"));
    private static SelenideElement editSettingsButton = $(By.xpath("//a[text() = 'Edit Account Settings']"));
    private static SelenideElement fullNameFiled = $(By.id("full_name"));
    private static SelenideElement saveChangesPersonalDetails = $(By.xpath("(//button[contains(text() ,' Save changes')])[1]"));
    private static SelenideElement personDetailsSuccess = $(By.xpath("//h2[contains(text(), 'Personal details')]//following-sibling::div//p[text() = 'Settings saved successfully']"));


    public static void goToLoginPage() {

        myAccountIcon.click();
        myAccountLoginButton.click();
    }

    // This method selects an option from the My Account dropdown based on a tester provided input
    public static void navigateTo(String myAccountOption)   {
        SelenideElement dropdownOption = $(By.xpath("//h4[contains(text(), 'Hi,')]/following-sibling:: li[a[contains(text(), '"+myAccountOption+"')]]"));
        Actions action = new Actions(getWebDriver());

        action.moveToElement(myAccountIconLoggedIn).perform();
        dropdownOption.click();
    }

    //While on the My Account page this method will get the current user's full name, strip unsanitary characters and return the processed string
    private static String getUserCurrentName() {

        String userName = myAccountPageUsername.getText();
        userName = userName.substring(6, userName.length()-1);

        return userName;
    }

    //This method navigates for the My Account page to the edit settings one. After that it generates a random alphanumeric string and saves it as a full name. The method also asserts that both Successful save message is present
    //The method also asserts that both Successful save message is present as well that the name change is successful by navigating back the My Account page.
    public static void executeANameChangeAndVerifyItsSuccessful() {
        String nameBeforeChange = getUserCurrentName();
        String newName = RandomStringUtils.randomAlphanumeric(12);

        editSettingsButton.click();
        fullNameFiled.clear();
        fullNameFiled.sendKeys(newName);
        saveChangesPersonalDetails.click();
        personDetailsSuccess.shouldBe(Condition.visible);
        navigateTo("My Account");
        Assert.assertNotEquals(nameBeforeChange, newName);
        Assert.assertEquals(newName, getUserCurrentName());
    }
}
