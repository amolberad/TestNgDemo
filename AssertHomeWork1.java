package AssertsDemo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;
//-------------------------------------------------------------------
//Dublicat Entry. Please Verify
////div[@class='error-box round']

////div[@class='confirmation-box round']
//[ Amol T Berad ] Customer Details Added !
//-------------------------------------------------------------------

public class AssertHomeWork1 {
    @Test(dataProvider = "getDataForCall")
    public void assertInsertRecord(String nm,String address, String contact1, String contact2)
    {
        //Test for checking Appropriate Page using Page Title after valid user login
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        //open Login Page for testing
        driver.manage().window().maximize();
        driver.get("http://stock.scriptinglogic.net/");
        //Enter username & Password and click to login into system
        WebElement txtuser= driver.findElement(By.id("login-username"));
        txtuser.sendKeys("admin");

        WebElement txtpass= driver.findElement(By.id("login-password"));
        txtpass.sendKeys("admin");
        driver.findElement(By.name("submit")).click();
        // Click on Add Customer Link
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.linkText("Add Customer")).click();

        // Insert Record into Fields Provided in Add Customer

        WebElement custnm=driver.findElement(By.xpath("//input[@id='name']"));
        custnm.sendKeys(nm);
        WebElement custadd= driver.findElement(By.xpath("//textarea[@placeholder='ENTER YOUR ADDRESS']"));
        custadd.sendKeys(address);
        WebElement custcont1=driver.findElement(By.xpath("//input[@id='buyingrate']"));
        custcont1.sendKeys(contact1);
        WebElement custcont2= driver.findElement(By.xpath("//input[@id='sellingrate']"));
        custcont2.sendKeys(contact2);

        driver.findElement(By.xpath("//input[@name='Submit']")).click();
        String expected="[ Sagar T Berad ] Customer Details Added !";
        String actual=null;
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        try{
            actual=driver.findElement(By.xpath("//div[contains(@class,'confirmation-box')]")).getText();

        }catch (Exception e)
        {
            actual="";
        }
        Assert.assertEquals(actual,expected,"Test is Failed : Unable to Save Record !");
        driver.close();
    }
    @Test(dataProvider = "getDataForCall1")
    public void assertInsertDuplicate(String nm,String address, String contact1, String contact2)
    {
        //Test for checking Appropriate Page using Page Title after valid user login
        WebDriverManager.firefoxdriver().setup();
        WebDriver driver = new FirefoxDriver();
        //open Login Page for testing
        driver.manage().window().maximize();
        driver.get("http://stock.scriptinglogic.net/");
        //Enter username & Password and click to login into system
        WebElement txtuser= driver.findElement(By.id("login-username"));
        txtuser.sendKeys("admin");

        WebElement txtpass= driver.findElement(By.id("login-password"));
        txtpass.sendKeys("admin");
        driver.findElement(By.name("submit")).click();
        // Click on Add Customer Link
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.linkText("Add Customer")).click();

        // Insert Record into Fields Provided in Add Customer

        WebElement custnm=driver.findElement(By.xpath("//input[@id='name']"));
        custnm.sendKeys(nm);
        WebElement custadd= driver.findElement(By.xpath("//textarea[@placeholder='ENTER YOUR ADDRESS']"));
        custadd.sendKeys(address);
        WebElement custcont1=driver.findElement(By.xpath("//input[@id='buyingrate']"));
        custcont1.sendKeys(contact1);
        WebElement custcont2= driver.findElement(By.xpath("//input[@id='sellingrate']"));
        custcont2.sendKeys(contact2);

        String expected="Dublicat Entry. Please Verify";
        String actual=null;
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        try{
            actual=driver.findElement(By.xpath("//div[contains(@class,'error-box')]")).getText();

        }catch (Exception e)
        {
            actual="";
        }
        Assert.assertEquals(actual,expected,"Test is Failed : Unable to Save Record !");
        driver.close();
    }
    @DataProvider
    public Object[][] getDataForCall()
    {
        Object data[][]=new Object[1][4];
        data[0][0]="Sagar D Berad";
        data[0][1]="sagar.ibmrd@gmail.com";
        data[0][2]="9778170327";
        data[0][3]="Mumbai";

        return data;
    }
    @DataProvider
    public Object[][] getDataForCall1()
    {
        Object data[][]=new Object[1][4];
        data[0][0]="Sagar D Berad";
        data[0][1]="sagar.ibmrd@gmail.com";
        data[0][2]="9778170327";
        data[0][3]="Mumbai";

        return data;
    }
}
