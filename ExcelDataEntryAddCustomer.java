package AssertsDemo;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

public class ExcelDataEntryAddCustomer {
    @Test(dataProvider = "getData")
    public void assertInsertRecord(String nm,String address, String contact1, String contact2, String testexpect, String testxpath) throws IOException {
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
        // Click on Add Customer Link
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.linkText("Add Customer")).click();

        // Insert Record into Fields Provided in Add Customer
        driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS);
        WebElement custnm=driver.findElement(By.xpath("//input[@id='name']"));
        custnm.sendKeys(nm);
        WebElement custadd= driver.findElement(By.xpath("//textarea[@placeholder='ENTER YOUR ADDRESS']"));
        custadd.sendKeys(address);
        WebElement custcont1=driver.findElement(By.xpath("//input[@id='buyingrate']"));
        custcont1.sendKeys(contact1);
        WebElement custcont2= driver.findElement(By.xpath("//input[@id='sellingrate']"));
        custcont2.sendKeys(contact2);
        driver.findElement(By.xpath("//input[@name='Submit']")).click();
        String actual=" ";
        try {
             actual = driver.findElement(By.xpath(testxpath)).getText();
        }
        catch(Exception e)
        {
            System.out.print(e.getMessage());
        }

     boolean result=actual.contains(testexpect);
        Assert.assertTrue(result,"Test is Failed");


        TakesScreenshot ts=(TakesScreenshot) driver;
        File srcfile= ts.getScreenshotAs(OutputType.FILE);
       FileUtils.copyFile(srcfile, new File("d:\\screenshot\\image.png"));
        driver.close();
    }
    @DataProvider
    public Object[][] getData() throws IOException {
        FileInputStream fs=new FileInputStream("data\\myData1.xlsx");
        XSSFWorkbook myWorkbook=new XSSFWorkbook(fs);
        XSSFSheet mySheet=myWorkbook.getSheet("Sheet2");
        int rowCount=mySheet.getPhysicalNumberOfRows();
        Object data[][]=new Object[rowCount-1][6];

        for(int i=0;i<rowCount-1;i++) {
            XSSFRow myRow=mySheet.getRow(i+1);

            XSSFCell nameData=myRow.getCell(0);
            data[i][0] = nameData.toString().trim();
            XSSFCell addData=myRow.getCell(1);
            data[i][1] = addData.toString().trim();

            XSSFCell contact1Data=myRow.getCell(2);
            contact1Data.setCellType(CellType.STRING);
            data[i][2] = contact1Data.toString().trim();

            XSSFCell contact2Data=myRow.getCell(3);
            contact2Data.setCellType(CellType.STRING);
            data[i][3] = contact2Data.toString().trim();

            XSSFCell expected1=myRow.getCell(4);
            data[i][4] = expected1.toString();
            XSSFCell xpath1=myRow.getCell(5);
            data[i][5] = xpath1.toString().trim();
        }
        return data;
    }
}