package stepdefinitions;

import cucumber.api.DataTable;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import restassured.test.CoinMarket;

import java.util.List;

public class BackEndTask extends CoinMarket{
    @When("^Ids of crypto currencies are retrieved")
    public void getCoinIds() {
        getCoinMarketIds();
    }

    @Then("^Crypto Currencies are converted to Bolivian Boliviano currency")
    public void convertToOtherCurrency() {
        convertPriceToAnotherCurrency();
    }

    @And("^Crypto Currency info is performed")
    public void cryptoCurrencyInfo() {
        getCryptoCurrencyInfo();
    }

    @And("^Retrieve Currencies from Crypto Currency Info call")
        public void cryptoCurrencyInfoCurrenciesDataTable(DataTable dt) {
        List<List<String>> list = dt.asLists(String.class);
        for(int i=1; i<list.size(); i++) {
            getCryptoCurrencyInfoCurrencies(list.get(i).get(0), list.get(i).get(1));
        }
    }
}
