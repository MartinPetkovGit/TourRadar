package utils;

import com.codeborne.selenide.Selenide;

import static com.codeborne.selenide.Configuration.baseUrl;
import static com.codeborne.selenide.Configuration.browserSize;

public class Browser {

    public static void setup(){
        browserSize = "1920x1080";
        baseUrl = "https://www.tourradar.com/";
    }

    public static void teardown(){
        Selenide.closeWebDriver();
    }
}
