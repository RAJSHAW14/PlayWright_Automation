package api;

import base.TestBase;
import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.Playwright;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import utilities.ConfigManager;

import java.util.Map;

public class ApiTestBase extends TestBase {
    public static APIRequestContext apiRequestContext;
    Playwright playwright;
    public static void initAPIContext(Playwright playwright){
        APIRequest.NewContextOptions options = new APIRequest.NewContextOptions()
                .setBaseURL(ConfigManager.getProperty("api.base.url"))
                .setExtraHTTPHeaders(Map.of("Content-Type", "application/json"));
        apiRequestContext = playwright.request().newContext(options);
    }

    @BeforeClass
    public void setupAPI(){
        playwright = Playwright.create();
        ApiTestBase.initAPIContext(playwright);
    }

    @AfterClass
    public void dispose(){
        if (apiRequestContext != null){
            apiRequestContext.dispose();
        }
        if (playwright != null){
            playwright.close();
        }
    }
}