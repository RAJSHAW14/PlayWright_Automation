package base;

import com.microsoft.playwright.*;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import utilities.ConfigManager;

import java.util.List;

public class TestBase {
    protected Playwright playwright;
    protected Browser browser;
    protected BrowserContext context;
    protected Page page;
    protected String browsername;
    @BeforeMethod
    public void setup(){
        browsername = ConfigManager.getProperty("browser");
        BrowserType browserType;

        playwright = Playwright.create();

        if (browsername.equalsIgnoreCase("chromium")){
            browserType = playwright.chromium();
        } else if (browsername.equalsIgnoreCase("firefox")) {
            browserType = playwright.firefox();
        } else if (browsername.equalsIgnoreCase("webkit")){
            browserType = playwright.webkit();
        } else {
            browserType = playwright.chromium();
        }
        browser = browserType.launch(new BrowserType.LaunchOptions()
                        .setArgs(List.of("--start-maximized"))
                .setHeadless(Boolean.parseBoolean(ConfigManager.getProperty("headless"))));
        context = browser.newContext(new Browser.NewContextOptions().setViewportSize(null));
        page = context.newPage();
        page.navigate(ConfigManager.getProperty("base.url"));
    }

    @AfterMethod
    public void tearDown(){
        if (playwright != null){
            playwright.close();
        }
    }
}
