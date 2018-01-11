package Homework;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ApiDocs_WD {


    private static final String APIDOCS_URL = "https://amers1.apps.cp.reutest.com/Apps/APIDocs/1.726.1/#/";
    private static final String DOCSAPI = "//a[@class='category-api-item ng-binding'][contains(text(),'/definitions')]";
    private static final String SYMBAPI = "//a[@class='category-api-item ng-binding'][contains(text(),'/convert')]";
    private static final String TRYITBTN = "//span[@id='coral-button_label']/b[contains(text(),'Try it!')]";
    private static final String RETURN = "//div[@class='ng-scope']//a[@href='./']";
    private static final String SIGNINBTN = "//div[@class='button_img button_75 button_75_enabled'][contains(text(),'Sign In')]";

    private WebDriver driver;

    @BeforeClass
    public void Before() {
        System.setProperty("webdriver.chrome.driver", "D:\\ATM\\Homework\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");

        driver = new ChromeDriver(options);

        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @Test(description = "Start browser", priority = 1)
    public void startBrowser() throws InterruptedException {

        //Start browser
        driver.get(APIDOCS_URL);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        //Enter login
        WebElement apiDocsLoginInput = driver.findElement(By.name("IDToken1"));
        apiDocsLoginInput.sendKeys("emqa16@thomsonreuters.com");

        //Enter password
        WebElement apiDocsPasswordInput = driver.findElement(By.name("IDToken2"));
        apiDocsPasswordInput.sendKeys("Secret123");

        WebElement Sign = driver.findElement(By.xpath(SIGNINBTN));
        Sign.click();

        WebElement SignInBtn = driver.findElement(By.xpath(SIGNINBTN));
        SignInBtn.click();

        Thread.sleep(10000);

    }

    @Test(description = "Sent request to Docs API", priority = 2)
    public void ExecuteDocsRequest() throws InterruptedException {

        final Wait<WebDriver> wait = new WebDriverWait(driver, 5).withMessage("Element was not found");

        //Choose first endpoint
        WebElement DocsEndpoint = driver.findElement(By.xpath(DOCSAPI));
        DocsEndpoint.click();

        Thread.sleep(20000);

        //Execute request
        WebElement TryItBtn = driver.findElement(By.xpath(TRYITBTN));
        TryItBtn.click();

        //Return to the main page
        WebElement ReturnBtn = driver.findElement(By.xpath(RETURN));
        ReturnBtn.click();

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.id("searchInput"))));

        WebElement SearchBtn = driver.findElement(By.id("searchInput"));
        highlightElement(SearchBtn);
        SearchBtn.sendKeys("beta");
        Assert.assertTrue(SearchBtn.isDisplayed(), "No search results!");

        WebElement ClearSearchBtn = driver.findElement(By.id("search-button"));
        ClearSearchBtn.click();

        Thread.sleep(10000);

    }

    @Test(description = "Sent request to Symbology API", priority = 3)
    public void ExecuteSymbologyRequest() throws InterruptedException {

        final Wait<WebDriver> wait = new WebDriverWait(driver, 5).withMessage("Element was not found");

        //Choose second endpoint
        WebElement SymbEndpoint = driver.findElement(By.xpath(SYMBAPI));
        SymbEndpoint.click();

        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.className("ng-binding"))));

        //Choose one of the examples
        WebElement ExamplegetSymb = driver.findElement(By.className("ng-binding"));
        ExamplegetSymb.click();

        //Execute request
        WebElement BTN = driver.findElement(By.xpath(TRYITBTN));
        BTN.click();
        Assert.assertTrue(BTN.isDisplayed(), "Error");

    }


    public void highlightElement(WebElement element) throws InterruptedException {
        String bg = element.getCssValue("backgroundColor");
        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("arguments[0].style.backgroundColor = '" + "yellow" + "'", element);
        // take screenshot here
        // or just pause/blink
        Thread.sleep(500);
        js.executeScript("arguments[0].style.backgroundColor = '" + bg + "'", element);

    }
    @AfterClass(description = "Stop Browser")
    public void stopBrowser() {
		driver.quit();
		System.out.println("Browser was successfully quited.");
	}
}



