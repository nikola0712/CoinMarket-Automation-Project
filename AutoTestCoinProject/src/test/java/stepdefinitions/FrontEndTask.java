package stepdefinitions;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pageobject.MarketCoinLandingPage;

import static org.junit.Assert.assertEquals;

public class FrontEndTask extends MarketCoinLandingPage {
    @Given("^Market coin landing page is open")
    public void marketCoinLandingPage() {
        setUp();
    }

    @When("^View All button is clicked")
    public void viewAllIsClicked() {
        clickOnViewAll();
    }

    @When("^Result of \"([^\"]*)\" crypto currencies is returned")
    public void numberOfShownCryptoCurrencies (int expectedNumberOfCryptoCurrencies) {
        assertEquals( expectedNumberOfCryptoCurrencies, actualNumberOfCryptoCurrencies());
    }

    @And("^Select \"([^\"]*)\" random crypto currencies from list and compare them on WatchList tab")
    public void numberOfRandomSelectedCryptoCurrencies(int givenNumberOfCryptoCurrencies) throws Exception{
        selectRandomCryptoCurrencyAndCompare(givenNumberOfCryptoCurrencies);
    }

    @Then("^Open dropdown of Crypto Currency tab and click on Full List")
    public void openDropDownAndClickOnGivenValue() {
        clickOnDropDownAndClickOnGivenValue();
    }

    @And("^Compare recorded data on full list and filtered data")
    public void recordDataAndFilterDataComparison() throws Exception {
        recordDataAndCompareWithFiltering();
        tearDown();
    }
}
