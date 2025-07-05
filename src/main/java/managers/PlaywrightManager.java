package managers;

import com.microsoft.playwright.*;
import utilities.ConfigManager;

import java.util.HashMap;
import java.util.Map;

public class PlaywrightManager {
    private static ThreadLocal<Playwright> playwright = new ThreadLocal<>();
    private static ThreadLocal<Browser> browser = new ThreadLocal<>();
    private static ThreadLocal<BrowserContext> browserContext = new ThreadLocal<>();
    private static ThreadLocal<Page> page = new ThreadLocal<>();
    private static ThreadLocal<APIRequestContext> apiRequest = new ThreadLocal<>();

    public static Page getPage(){
        if (page.get() == null) initializeBrowser();
        return page.get();
    }

    public static APIRequestContext api(){
        if (apiRequest.get() == null) initializeAPI();
        return apiRequest.get();
    }
    private static void initializeBrowser(){
        playwright.set(Playwright.create());

        String browserType = ConfigManager.getProperty("browser");
        BrowserType.LaunchOptions options = new BrowserType.LaunchOptions()
                .setHeadless(Boolean.parseBoolean(ConfigManager.getProperty("headless")));
        switch (browserType.toLowerCase()){
            case "firefox" -> browser.set(playwright.get().firefox().launch(options));
            case "webkit" -> browser.set(playwright.get().webkit().launch(options));
            default -> browser.set(playwright.get().chromium().launch(options));
        }
        browserContext.set(browser.get().newContext(new Browser.NewContextOptions().setViewportSize(1280,720)));
        page.set(browserContext.get().newPage());
    }

    private static void initializeAPI(){
        playwright.set(Playwright.create());
        Map<String, String> headers = new HashMap<>();
        headers.put("Authorization", "Bearer " + ConfigManager.getProperty("api_token"));

        apiRequest.set(playwright.get().request().newContext(new APIRequest.NewContextOptions()
                .setBaseURL(ConfigManager.getProperty("api_base_url"))
                .setExtraHTTPHeaders(headers)));
    }

    public static void close(){
        if (page.get() != null) page.get().close();
        if (browserContext.get() != null) browserContext.get().close();
        if (browser.get() != null) browser.get().close();
        if (playwright.get() != null) playwright.get().close();
    }

}
