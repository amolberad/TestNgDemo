import io.github.bonigarcia.wdm.WebDriverManager;
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

public class ExcelRecordAddtitioxslx {
    @Test(dataProvider = "getDataForCall")
    public void requestCall(String nm,String email, String contact, String city) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver=new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://scriptinglogic.org/request-call-demo-class-online/");
        //System.out.println(nm+"\t"+email+"\t"+contact+"\t"+city);
        WebElement name=driver.findElement(By.xpath("//input[@placeholder='Name']"));
        name.sendKeys(nm);

        WebElement emailid=driver.findElement(By.xpath("//input[@placeholder='Email']"));
        emailid.sendKeys(email);

        WebElement phone=driver.findElement(By.xpath("//input[contains(@placeholder,'Contact no./ Whatsapp No.')]"));
        phone.sendKeys(contact);

        WebElement place=driver.findElement(By.xpath("//input[@placeholder='City']"));
        place.sendKeys(city);

        driver.findElement(By.xpath("//input[contains(@value,'Selenium')]")).click();
        driver.findElement(By.xpath("//input[@name='fields[send]']")).click();


        Thread.sleep(2000);
        driver.close();
    }
    @DataProvider
    public Object[][] getDataForCall() throws IOException {
        FileInputStream fs=new FileInputStream("data\\myData1.xlsx");
        XSSFWorkbook myWorkbook=new XSSFWorkbook(fs);
       XSSFSheet mySheet=myWorkbook.getSheet("Sheet2");
        int rowCount=mySheet.getPhysicalNumberOfRows();
        Object data[][]=new Object[rowCount][4];

        for(int i=0;i<rowCount;i++) {
            XSSFRow myRow=mySheet.getRow(i);

           XSSFCell nameData=myRow.getCell(0);
            data[i][0] = nameData.toString().trim();
            XSSFCell emailData=myRow.getCell(1);
            data[i][1] = emailData.toString().trim();
           XSSFCell contactData=myRow.getCell(2);
            data[i][2] = contactData.toString().trim();
           XSSFCell cityData=myRow.getCell(3);
            data[i][3] = cityData.toString().trim();
        }
        return data;
    }
}
