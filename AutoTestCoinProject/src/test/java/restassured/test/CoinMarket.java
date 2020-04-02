package restassured.test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Test;
import org.junit.Assert;

import java.util.*;

import static io.restassured.RestAssured.given;

public class CoinMarket {

    private static final String API_KEY = "33a37cf2-0b65-4045-aa83-a019fe815230";
    private static final String CRYPTOCURRENCY_MAP = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/map";
    private static final String TOOLS_PRICE_CONVERSION = "https://pro-api.coinmarketcap.com/v1/tools/price-conversion";
    private static final String CRYPTOCURRENCY_INFO = "https://pro-api.coinmarketcap.com/v1/cryptocurrency/info";
    private static final String LOGO_INFO = "https://s2.coinmarketcap.com/static/img/coins/64x64/1027.png";
    private static final String TECHNICAL_DOC = "https://github.com/ethereum/wiki/wiki/White-Paper";
    private static final String SYMBOL_CURRENCY = "\"symbol\": \"ETH\"";
    private static final String DATE_ADDED = "\"date_added\": \"2015-08-07T00:00:00.000Z\"";
    private static final String PLATFORM = "\"platform\": null";
    private static final String MINEABLE = "mineable";


    private static Response doGetRequest() {
        RestAssured.defaultParser = Parser.JSON;
        return
                given().headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                        .header("X-CMC_PRO_API_KEY",API_KEY).
                        when().get(CRYPTOCURRENCY_MAP).
                        then().contentType(ContentType.JSON).extract().response();
    }

    private static Response doGetPriceConversion(int currencyId) {
        RestAssured.defaultParser = Parser.JSON;
        return
                given().headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                .header("X-CMC_PRO_API_KEY",API_KEY)
                        .queryParam("id", currencyId).queryParam("amount", "1")
                        .queryParam("convert", "BOB")
                        .when().get(CoinMarket.TOOLS_PRICE_CONVERSION)
                        .then().contentType(ContentType.JSON).extract().response();
    }

    private static Response getCryptoCurrencyInfo(String currencyId) {
        RestAssured.defaultParser = Parser.JSON;
        return
                given().headers("Content-Type", ContentType.JSON, "Accept", ContentType.JSON)
                        .header("X-CMC_PRO_API_KEY",API_KEY)
                        .queryParam("id", currencyId)
                        .when().get(CRYPTOCURRENCY_INFO).
                        then().contentType(ContentType.JSON).extract().response();
    }

    private static <K, V> K getKey(Map<K, V> map, V value) {
        return map.keySet().stream().filter(key -> value.equals(map.get(key))).findFirst().get();
    }

    @Test
    public void getCoinMarketIds() {
        Response response = doGetRequest();
        String responseAsString = response.asString();

        JSONObject jsonObject = new JSONObject(responseAsString);
        JSONArray myResponse = jsonObject.getJSONArray("data");
        System.out.println(myResponse);

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < myResponse.length(); i++) {
            jsonObject = myResponse.getJSONObject(i);
            map.put(jsonObject.getInt("rank"), jsonObject.getInt("id"));
        }

        map.forEach((k, v) -> System.out.println("Key: " + k + ", Value: " + v));
        System.out.println("The Bitcoin id is: " + map.get(1));
        System.out.println("The Ethereum id is: " + map.get(2));
        System.out.println("The Tether USD id is: " + map.get(4));
    }

    @Test
    public static void convertPriceToAnotherCurrency() {
        Response responseBitcoinConversionToBolivian = doGetPriceConversion(1);
        Response responseTetherConversionToBolivian = doGetPriceConversion(1027);
        Response responseEthereumConversionToBolivian = doGetPriceConversion(825);

        System.out.println(responseBitcoinConversionToBolivian.jsonPath().getString("data"));
        System.out.println(responseTetherConversionToBolivian.jsonPath().getString("data"));
        System.out.println(responseEthereumConversionToBolivian.jsonPath().getString("data"));
    }

    @Test
    public void getCryptoCurrencyInfo() {
        Response responseCryptoCurrencyInfo = getCryptoCurrencyInfo("1027");

        System.out.println(responseCryptoCurrencyInfo.jsonPath().getString("data"));
        String allInfo = responseCryptoCurrencyInfo.then().assertThat().statusCode(200).extract().asString();
        Assert.assertTrue(allInfo.contains(LOGO_INFO));
        Assert.assertTrue(allInfo.contains(TECHNICAL_DOC));
        Assert.assertTrue(allInfo.contains(SYMBOL_CURRENCY));
        Assert.assertTrue(allInfo.contains(DATE_ADDED));
        Assert.assertTrue(allInfo.contains(PLATFORM));
        Assert.assertTrue(allInfo.contains(MINEABLE));
    }

    @Test
    public void getCryptoCurrencyInfoCurrencies(String cryptocurrencyId, String cryptoCurrencyName) {
        Map <String, String> map = new HashMap<>();
        map.put(cryptocurrencyId, cryptoCurrencyName);

        map.forEach((k, v) -> {
                    Response responseCryptoCurrencyInfo = getCryptoCurrencyInfo(k);
                    JSONObject jsonObj = new JSONObject(responseCryptoCurrencyInfo.asString());

                    Assert.assertEquals("Name of crypto currency is not " + v +" ", v, jsonObj.getJSONObject("data").getJSONObject(k).getString("name"));
                    Assert.assertEquals("No mineable tag for " + v + " ","[\"mineable\"]", jsonObj.getJSONObject("data").getJSONObject(k).getJSONArray("tags").toString());
                }
        );
    }
}
