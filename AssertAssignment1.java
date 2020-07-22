package AssertsDemo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class AssertAssignment1 {
    @Test(enabled = false)
    public void assertDemo()
    {
        //Test for checking Invalid Username & Password
    WebDriverManager.chromedriver().setup();
    WebDriver driver = new ChromeDriver();
    //open Login Page for testing
        driver.manage().window().maximize();
        driver.get("http://stock.scriptinglogic.net/");
    //Enter username & Password and click to login into system
    WebElement txtuser= driver.findElement(By.id("login-username"));
        txtuser.sendKeys("admin1");

    WebElement txtpass= driver.findElement(By.id("login-password"));
        txtpass.sendKeys("admin");
        driver.findElement(By.name("submit")).click();

        String expected="Wrong Username or Password";
        String actual=null;
       // driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        try{
             actual=driver.findElement(By.xpath("//div[@class='error-box round']")).getText();
             }catch (Exception e)
        {
            actual="";
        }
        Assert.assertEquals(actual,expected,"Test is Failed : Unable to check message !");
driver.close();
    }
    @Test(enabled = false)
    public void assertDemo1()
    {
        //Test for checking Appropriate Dashboard after valid user login
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        //open Login Page for testing
        driver.manage().window().maximize();
        driver.get("http://stock.scriptinglogic.net/");
        //Enter username & Password and click to login into system
        WebElement txtuser= driver.findElement(By.id("login-username"));
        txtuser.sendKeys("admin");

        WebElement txtpass= driver.findElement(By.id("login-password"));
        txtpass.sendKeys("admin");
        driver.findElement(By.name("submit")).click();

        String expected="http://stock.scriptinglogic.net/dashboard.php";
        String actual=null;
        // driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        try{
            actual=driver.getCurrentUrl().toString();
        }catch (Exception e)
        {
            actual="";
        }
        Assert.assertEquals(actual,expected,"Test is Failed : Unable to load Home Page Wrong URL!");
        driver.close();
    }
    @Test
    public void assertDemo2()
    {
        //Test for checking Appropriate Page using Page Title after valid user login
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        //open Login Page for testing
        driver.manage().window().maximize();
        driver.get("http://stock.scriptinglogic.net/");
        //Enter username & Password and click to login into system
        WebElement txtuser= driver.findElement(By.id("login-username"));
        txtuser.sendKeys("admin");

        WebElement txtpass= driver.findElement(By.id("login-password"));
        txtpass.sendKeys("admin");
        driver.findElement(By.name("submit")).click();

        String expected="POSNIC - Dashboard";
        String actual=null;
        // driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        try{
            actual=driver.getTitle().toString();
        }catch (Exception e)
        {
            actual="";
        }
        Assert.assertEquals(actual,expected,"Test is Failed : Unable to load Home Page Wrong Title !");
        driver.close();
    }
    @Test
    public void assertDemo3()
    {
        //Test for checking Appropriate Page using Page Title after valid user login
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        //open Login Page for testing
        driver.manage().window().maximize();
        driver.get("http://stock.scriptinglogic.net/");
        //Enter username & Password and click to login into system
        WebElement txtuser= driver.findElement(By.id("login-username"));
        txtuser.sendKeys("admin");

        WebElement txtpass= driver.findElement(By.id("login-password"));
        txtpass.sendKeys("admin");
        driver.findElement(By.name("submit")).click();

        String expected="QUICK LINKS";
        String actual=null;
        driver.manage().timeouts().implicitlyWait(100, TimeUnit.SECONDS);
        try{
            actual=driver.findElement(By.xpath("//h3[contains(text(),'Quick Links')]")).getText();
            System.out.println(actual);
        }catch (Exception e)
        {
            actual="";
        }
        Assert.assertEquals(actual,expected,"Test is Failed : Unable to load Home Page Wrong Title !");
        driver.close();
    }
}
