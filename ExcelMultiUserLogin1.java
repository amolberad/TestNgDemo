import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.IOException;

public class ExcelMultiUserLogin1 {

    @Test(dataProvider = "getData")
    public void multiUser(String userdata, String passdata) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://billing.scriptinglogic.net/");
        WebElement user=driver.findElement(By.xpath("//input[@id='email']"));
        user.sendKeys(userdata);
        WebElement  pass=driver.findElement(By.xpath("//input[@id='password']"));
        pass.sendKeys(passdata);
        driver.findElement(By.xpath("//input[@name='btn_login']")).click();
        Thread.sleep(2000);
        driver.close();
    }
    @DataProvider
    public Object[][] getData() throws IOException {
        FileInputStream fs=new FileInputStream("data\\myData1.xlsx");
        XSSFWorkbook worksheet= new XSSFWorkbook(fs);
        XSSFSheet sheet=worksheet.getSheet("Sheet1");
        int rowCount=sheet.getPhysicalNumberOfRows();
        Object[][] data= new Object[rowCount][2];
        for(int i=0;i<rowCount;i++)
        {
            XSSFRow row=sheet.getRow(i);

            XSSFCell username=row.getCell(0);
            data[i][0]=username.toString().trim();

            XSSFCell password=row.getCell(1);
            data[i][1]=password.toString().trim();


        }
        return data;
    }

}
