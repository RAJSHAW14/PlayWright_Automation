package pages;

import com.microsoft.playwright.Page;

public class LoginPage {
    private final Page page;

    private final String usernameTxt = "input[name='username']";
    private final String passwordTxt = "input[name='password']";
    private final String submitBtn = "button:has-text('Login')";
    private final String errorMessage = ".oxd-alert-content";

    public LoginPage(Page page){
        this.page = page;
    }

    public void enterUsername(String username){
        page.fill(usernameTxt, username);
    }

    public void enterPassword(String password){
        page.fill(passwordTxt, password);
    }

    public String gerErrorMessage(){
        return page.textContent(errorMessage);
    }

    public void clickLogin(){
        page.click(submitBtn);
    }

    public void loginValidUser(String username, String password){
        enterUsername(username);
        enterPassword(password);
        clickLogin();

    }
}
