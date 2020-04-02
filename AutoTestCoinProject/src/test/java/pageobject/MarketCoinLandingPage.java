package pageobject;

import org.junit.Test;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.*;

import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class MarketCoinLandingPage extends BaseClass {

    @Test
    public void clickOnViewAll() {
        driver.findElement(By.xpath("//div[@class='cmc-table-listing__pagination']//a[@href='/all/views/all/']")).click();
    }

    @Test
    public int actualNumberOfCryptoCurrencies() {
        return  driver.findElements(By.xpath("//tr[@class='cmc-table-row']//td[@class='cmc-table__cell cmc-table__cell--sticky cmc-table__cell--sortable cmc-table__cell--left cmc-table__cell--sort-by__rank']")).size();

    }

    @Test
    public void selectRandomCryptoCurrencyAndCompare(int n) throws Exception{
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(visibilityOfElementLocated(By.xpath("//tr[@class='cmc-table-row'][1]//div[@class='cmc-popover__trigger']")));
        Thread.sleep(5000);

        List<WebElement> allProducts = driver.findElements(By.xpath("//tr[@class='cmc-table-row']"));
        List<WebElement> allValuesInCryptoRow = driver.findElements(By.xpath("//tr[@class='cmc-table-row']"));

        for (int i=1; i<=n; i++) {
            Random rand = new Random();
            int randomProduct = rand.nextInt(allProducts.size());

            WebElement ellipsis = allProducts.get(randomProduct).findElement(By.xpath("//tr[@class='cmc-table-row'][" + randomProduct + "]//div[@class='cmc-popover__trigger']"));

            //use here js to get element into view by scrolling to it and plus rising a scroll a bit (10 pixels) so the element is always visible/found
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", ellipsis);
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0, -10);", ellipsis);
            wait.until(elementToBeClickable(By.xpath("//tr[@class='cmc-table-row'][" + randomProduct + "]//div[@class='cmc-popover__trigger']")));
            ellipsis.click();

            wait.until(visibilityOfElementLocated(By.xpath("//div[@class='cmc-popover__dropdown']//span")));
            allProducts.get(randomProduct).findElement(By.xpath("//div[@class='cmc-popover__dropdown']//span")).click();
        }

        getOpenWatchListAnotherTab();
        List<WebElement> allProductsOnWatchlist = driver.findElements(By.xpath("//div[@class='cmc-table__table-wrapper-outer']//tbody"));
        allProductsOnWatchlist.containsAll(allValuesInCryptoRow);
        tearDown();
    }

    private void getOpenWatchListAnotherTab() {
        WebElement link= driver.findElement(By.xpath("//a[@href='/watchlist/']"));
        String keyString =  Keys.CONTROL+Keys.SHIFT.toString()+Keys.ENTER.toString();
        link.sendKeys(keyString);
    }

    private void getFilteringOnCryptoCurrencies() {
        driver.findElement(By.xpath("//button[@data-qa-id='table-listing-filters-toggle']")).click();
        driver.findElement(By.xpath("//div[@class='sc-1hlf0zq-0 gGtlxv'][2]//button[@data-qa-id='filter-dd-toggle']")).click();
        driver.findElement(By.xpath("//input[@data-qa-id='range-filter-input-min']")).click();
        driver.findElement(By.xpath("//input[@data-qa-id='range-filter-input-min']")).sendKeys("10000");
        driver.findElement(By.xpath("//button[@data-qa-id='filter-dd-button-apply']")).click();
    }

    @Test
    public void clickOnDropDownAndClickOnGivenValue() {
        driver.findElement(By.xpath("//li[@class='cmc-tab__trigger cmc-tab__trigger--selected']")).click();
        driver.findElement(By.xpath("//li[@class='cmc-menu-item sc-1wniqvx-0 kBDFqk'][2]")).click();
    }

    @Test
    public void recordDataAndCompareWithFiltering() throws Exception {
        Thread.sleep(3000);
        List<WebElement> allDataRecorded = driver.findElements(By.xpath("//div[@class='cmc-table__table-wrapper-outer']//tbody"));
        getFilteringOnCryptoCurrencies();
        List<WebElement> allDataRecordedFiltered = driver.findElements(By.xpath("//div[@class='cmc-table__table-wrapper-outer']//tbody"));

        allDataRecorded.containsAll(allDataRecordedFiltered);
    }

}
