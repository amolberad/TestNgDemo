package ExtentDemo;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static ExtentDemo.util.ScreenShot.getScreenShot;

public class ExtentReportDemo1 {
    ExtentSparkReporter reporter;
    ExtentReports extent;
    @BeforeClass
    public void init()
    {
        String path=System.getProperty("user.dir")+"\\Reports\\report.html";
        reporter=new ExtentSparkReporter(path);
        reporter.config().setDocumentTitle("Billing Management System");
        reporter.config().setReportName("Login Regression Report");

        extent=new ExtentReports();
        extent.attachReporter(reporter);
        extent.setSystemInfo("Tester","Amol Berad");
        extent.setSystemInfo("Company","IBMRD");
        extent.setSystemInfo("Developer","Amol Ujagare");


    }
    @Test
    public void multiUser() throws InterruptedException, IOException {

        ExtentTest test=extent.createTest("Login Test");

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        test.info("Browser Open");
        driver.manage().window().maximize();
        test.info("Brouser Maximized");
        driver.get("http://billing.scriptinglogic.net/");
        test.info("Testing Page loaded");
        WebElement  user=driver.findElement(By.xpath("//input[@id='email']"));
        user.sendKeys("amolujagare@gmail.com");
        test.info("Username Entered");
        WebElement  pass=driver.findElement(By.xpath("//input[@id='password']"));
        pass.sendKeys("admin123");
        test.info("Password Entered");
        driver.findElement(By.xpath("//input[@name='btn_login']")).click();
        test.info("Login Button Clicked");

        String expected="http://billing.scriptinglogic.net/index.php/dashboard";
        String actual=driver.getCurrentUrl();
        try {
            Assert.assertEquals(actual, expected, "Unable to load Dashboard");
            test.pass("We are on DashBoard");
        }catch(AssertionError e)
        {
            test.fail(e.getMessage());
        }
        Date dNow = new Date( );
        SimpleDateFormat ft =new SimpleDateFormat ("yyyy-MM-dd-hh-mm-ss");
        System.out.println("Current Date: " + ft.format(dNow));
        TakesScreenshot ts=(TakesScreenshot) driver;
        File srcFile=ts.getScreenshotAs(OutputType.FILE);

        FileUtils.copyFile(srcFile,new File(System.getProperty("user.dir")+"\\Reports\\screenshot\\"+ft.format(dNow)+".png"));
        test.addScreenCaptureFromPath("./screenshot/"+getScreenShot(driver));
        extent.flush();
        driver.close();
    }
}

