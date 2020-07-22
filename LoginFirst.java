package TestNGAssignmentLogin;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class LoginFirst {
    static WebDriver driver=null;
    @Parameters({"browser","url"})
    @BeforeMethod
    public void loginFirstTest(String myBrowser, String myUrl){

        if(myBrowser.equalsIgnoreCase("chrome")){
        WebDriverManager.chromedriver().setup();
             driver=new ChromeDriver();
        }
        else if(myBrowser.equalsIgnoreCase("firefox"))
        {
            WebDriverManager.firefoxdriver().setup();
            driver=new FirefoxDriver();
        }

        driver.manage().window().maximize();
        driver.get(myUrl);
    }

   @Test(dataProvider ="getData" )
    public void checkAuthentication(String user, String pass) throws InterruptedException {
     // System.out.println(user+":"+pass);
        WebElement loginuser=driver.findElement(By.xpath("//input[@type='email']"));
        loginuser.sendKeys(user);
         WebElement loginpassword=driver.findElement(By.xpath("//input[@type='password']"));
        loginpassword.sendKeys(pass);
        //driver.close();

         driver.findElement(By.xpath("//input[@type='submit']")).click();
        Thread.sleep(2000);
    }
    @DataProvider
    public Object[][] getData()
    {
        Object data[][]=new Object[2][2];
        //record 1
        data[0][0]="amolujagare@gmail.com";
        data[0][1]="admin123";
//record 2
        data[1][0]="amolberad@yahoo.com";
        data[1][1]="admin345";
        return data;
    }
}
