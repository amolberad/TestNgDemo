package screenshot;

        import io.github.bonigarcia.wdm.WebDriverManager;
        import org.apache.commons.io.FileUtils;
        import org.openqa.selenium.OutputType;
        import org.openqa.selenium.TakesScreenshot;
        import org.openqa.selenium.WebDriver;
        import org.openqa.selenium.chrome.ChromeDriver;
        import org.testng.annotations.Test;

        import java.io.File;
        import java.io.IOException;
        import java.text.SimpleDateFormat;
        import java.util.Date;

public class ScreenShotDemo {
    static WebDriver driver=null;
    @Test
    public static void myScreenShot() throws IOException {
        WebDriverManager.chromedriver().setup();
        driver= new ChromeDriver();
        driver.get("http://www.facebook.com");

//Date
        Date dNow = new Date( );
        SimpleDateFormat ft =new SimpleDateFormat ("yyyy-MM-dd-hh-mm-ss");
        System.out.println("Current Date: " + ft.format(dNow));
        TakesScreenshot ts=(TakesScreenshot) driver;
        File srcFile=ts.getScreenshotAs(OutputType.FILE);

        FileUtils.copyFile(srcFile,new File("D:\\screenshot\\"+ft.format(dNow)+".png"));
        driver.close();
    }
}

