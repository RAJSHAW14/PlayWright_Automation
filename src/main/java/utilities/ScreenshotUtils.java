package utilities;

import com.microsoft.playwright.Page;

import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ScreenshotUtils {
    public static String captureScreenshot(Page page, String testName){
        String timeStamp = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
        String screenshotPath = "reports/screenshots/"+testName+"_"+timeStamp+".png";
        page.screenshot(new Page.ScreenshotOptions().setPath(Paths.get(screenshotPath)).setFullPage(true));
        return screenshotPath;
    }
}
