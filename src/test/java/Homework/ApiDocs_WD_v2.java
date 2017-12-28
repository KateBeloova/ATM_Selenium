package Homework;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ApiDocs_WD_v2 {

    // private WebDriver driver;

    @Test(description = "Start browser")
    public void startBrowser() throws InterruptedException {

        //Start browser
        System.setProperty("webdriver.chrome.driver", "D:\\ATM\\Homework\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized"); // Maximize browser window via options, just an example
        WebDriver driver = new ChromeDriver(options);

        driver.get("https://amers1.apps.cp.reutest.com/Apps/APIDocs/1.726.1/#/");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        WebElement apiDocsLoginInput = driver.findElement(By.cssSelector("input[id=AAA-AS-SI1-SE003]"));
        apiDocsLoginInput.sendKeys("emqa16@thomsonreuters.com");

        WebElement apiDocsPasswordInput = driver.findElement(By.xpath("//div[@class='textbox_img textbox_img_normal']//input[@id = 'AAA-AS-SI1-SE006']"));
        apiDocsPasswordInput.sendKeys("Secret123");

        WebElement Sign = driver.findElement(By.xpath("//div[@class='button_img button_75 button_75_enabled']"));
        Sign.click();

        WebElement SignInBtn = driver.findElement(By.xpath("//div[@class='button_img button_75 button_75_enabled']"));
        SignInBtn.click();

        Thread.sleep(10000);

        WebElement TRLogo = driver.findElement(By.xpath("//a[@class='style-scope coral-link']//span[@class='brand']"));
        Assert.assertTrue(TRLogo.isDisplayed());


        //@Test(description = "Sent request to Docs API")
        //public void ExecuteDocsRequest() throws InterruptedException {

        //Choose first endpoint
        WebElement DocsEndpoint = driver.findElement(By.xpath("//div[@class='datapoint-tile-wrapper']//a[@class='category-api-item ng-binding'][@href='./#/endpoint/L2RvY3MvYmV0YTE=/L2RlZmluaXRpb25z/2']"));
        DocsEndpoint.click();

        Thread.sleep(20000);

        //Execute request
        WebElement TryItBtn = driver.findElement(By.xpath("//div[@class = 'panel-borders-all']//span[@class='icon-send style-scope coral-icon']"));
        TryItBtn.click();

        //Return to the main page
        WebElement ReturnBtn = driver.findElement(By.xpath("//div[@class='ng-scope']//a[@href='./']"));
        ReturnBtn.click();

        Thread.sleep(10000);

        //WebElement RespModel = driver.findElement(By.xpath("//span[@class='constructor-name ng-binding']"));
        //Assert.assertTrue(RespModel.isDisplayed());

        //WebElement TRLogo = driver.findElement(By.xpath("//a[@class='style-scope coral-link']//span[@class='brand']"));
        //Assert.assertTrue(TRLogo.isDisplayed());


        // @Test(description = "Sent request to Symbology API")
        //public void ExecuteSymbologyRequest() throws InterruptedException {
        //Choose second endpoint
        WebElement SymbEndpoint = driver.findElement(By.xpath("//div[@class='datapoint-tile-wrapper']//a[@class='category-api-item ng-binding'][@href='./#/endpoint/L2RhdGEvc3ltYm9sb2d5L2JldGEx/L2NvbnZlcnQ=/2']"));
        SymbEndpoint.click();

        Thread.sleep(10000);

        //Choose one of the examples
        WebElement ExamplegetSymb = driver.findElement(By.xpath("//div[@class='table-wrapper']//td//a[@class='links-alt-color style-scope coral-link ng-binding'][1]"));
        ExamplegetSymb.click();

        //Execute request
        WebElement BTN = driver.findElement(By.xpath("//button[@class = 'inline-icon cta style-scope coral-button']//span[@id= 'coral-button_label']"));
        BTN.click();
        Assert.assertTrue(BTN.isDisplayed(), "Error");

        //  }
    }

    /*@AfterClass(description = "Stop Browser")
	public void stopBrowser() {
		driver.quit();
		System.out.println("Browser was successfully quited.");
	}*/
}



