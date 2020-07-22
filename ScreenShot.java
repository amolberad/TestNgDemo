package ExtentDemo.util;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenShot {
    public static String getScreenShot(WebDriver driver) throws IOException {
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("_yyyy_MM_dd_hh_mm_ss");
        //System.out.println("Current Date: " + ft.format(dNow));
        String filename="image"+ft.format(dNow)+".png";
        TakesScreenshot ts = (TakesScreenshot) driver;
        File srcFile = ts.getScreenshotAs(OutputType.FILE);

        FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir")+"\\Reports\\screenshot\\" + filename));

  return filename;
    }
}
