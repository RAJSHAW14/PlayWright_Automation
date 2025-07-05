package api;

import com.microsoft.playwright.APIResponse;
import com.microsoft.playwright.Playwright;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class UserAPITest extends ApiTestBase{


    @Test
    public void getUser_shouldReturn200(){
        APIResponse response = apiRequestContext.get("/users");
        Assert.assertEquals(response.status(),200,"Status code mismatch");
        System.out.println("Response Body: "+response.text());
    }


}
