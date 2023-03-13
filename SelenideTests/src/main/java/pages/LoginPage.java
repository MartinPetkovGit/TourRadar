package pages;

import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class LoginPage {

    private static SelenideElement emailField = $(By.id("g_email"));
    private static SelenideElement passwordField = $(By.id("g_password"));
    private static SelenideElement loginButton = $(By.id("g_send"));

    public static void loginWith(String username, String password) {

        emailField.sendKeys(username);
        passwordField.sendKeys(password);
        loginButton.click();
    }
}
