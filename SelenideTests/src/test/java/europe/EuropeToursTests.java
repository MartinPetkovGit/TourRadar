package europe;

import core.Base;
import org.testng.annotations.Test;
import pages.EuropeToursPage;
import pages.MyAccountPage;

public class EuropeToursTests extends Base {

    @Test
    public void verifyLengthSliderCorrectlyFiltersResults() {

        EuropeToursPage.setLengthTo("", "");
        EuropeToursPage.verifyResultsTourLength();
    }

    @Test
    public void verifyUserIsAbleToSuccessfullyChangeName(){
        MyAccountPage.loginWith();
        MyAccountPage.navigateTo("");
        MyAccountPage.saveUserCurrentName();
        MyAccountPage.changeFullNameTo("");
        MyAccountPage.verifySuccessMessage();
        MyAccountPage.navigateTo("");
        MyAccountPage.verifyNameChange();
    }

    @Test
    public void verifySortByPriceDesc() {
        EuropeToursPage.sortBy("");
        EuropeToursPage.verifyPriceSort("");
    }
}
