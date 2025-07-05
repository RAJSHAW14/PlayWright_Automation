package tests.api;

import api.ApiTestBase;
import com.microsoft.playwright.APIRequestContext;
import com.microsoft.playwright.options.RequestOptions;
import managers.PlaywrightManager;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class EmployeeApiTests extends ApiTestBase {
    @Test
    public void createEmployee(){
        //Creating request body
        Map<String, String> data = new HashMap<>();
        data.put("firstName", "John");
        data.put("lastName", "Doe");
        //Making Post request
       /* response = PlaywrightManager.api().post("/api/v1/pim/employees",
                RequestOptions.create()
                        .setHeader("Content-Type", "application/json")
                        .setData(data));
        //Verify response
        verifyStatusCode(200);
        verifyResponseContains("status","success");
        verifyResponseContains("data.employeeName", "John Doe");*/
    }
}
