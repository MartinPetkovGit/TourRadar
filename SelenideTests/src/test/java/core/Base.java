package core;

import org.testng.annotations.BeforeMethod;
import utils.Browser;
import static com.codeborne.selenide.Selenide.*;

public class Base {

    @BeforeMethod
    public void setup(){
        Browser.setup();
        open("d/europe");
    }

}
