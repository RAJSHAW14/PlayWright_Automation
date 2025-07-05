package api;

import com.microsoft.playwright.APIRequest;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import com.microsoft.playwright.options.RequestOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class AuthAPITest extends ApiTestBase {
    @Test
    public void login_shouldReturnAuthToken() {
        String endpoint = "/auth/login";
        String body = "{\n" +
                "  \"username\": \"Admin\",\n" +
                "  \"password\": \"admin123\"\n" +
                "}";
        APIResponse response = apiRequestContext.post(endpoint, RequestOptions.create()
                .setData(body));
        Assert.assertEquals(response.status(),200);
        System.out.println("Auth Token Response: "+response.text());

    }
}