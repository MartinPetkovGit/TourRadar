package europe;

import core.Base;
import org.testng.annotations.Test;
import pages.EuropeToursPage;
import pages.LoginPage;
import pages.MyAccountPage;
import utils.Credentials;

public class EuropeToursTests extends Base {

    @Test
    public void verifyLengthSliderCorrectlyFiltersResults() {

        EuropeToursPage.setLengthTo(2, 17);
        EuropeToursPage.verifyResultsTourLengthBetween(2, 17);
    }

    @Test
    public void verifyUserIsAbleToSuccessfullyChangeName() {
        MyAccountPage.goToLoginPage();
        LoginPage.loginWith(Credentials.validUsername, Credentials.validPassword);
        MyAccountPage.navigateTo("My Account");
        MyAccountPage.executeANameChangeAndVerifyItsSuccessful();
    }

    @Test
    public void verifySortByPriceDesc() throws InterruptedException {
        EuropeToursPage.sortBy("Total price: Highest first");
        EuropeToursPage.verifyTotalPriceSort(true);
    }
}
