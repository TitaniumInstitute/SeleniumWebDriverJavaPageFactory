package com.ti.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends MainPage{

    @FindBy(id = "user_login")
    private WebElement txtUser;

    @FindBy(name = "pwd")
    private WebElement txtPassword;

    @FindBy(css = "#rememberme")
    private WebElement chkRememberMe;

    @FindBy(xpath = "//input[contains(@value, 'Log')]")
    private WebElement btnLogin;

    private void setUserName(String userName){
        txtUser.clear();
        txtUser.sendKeys(userName);
    }

    private void setPassword(String password){
        txtPassword.clear();
        txtPassword.sendKeys(password);
    }

    private void checkRememberMe(){
        chkRememberMe.click();
    }

    private void clickLogin(){
        btnLogin.click();
    }

    public void login(String userName, String password){
        setUserName(userName);
        setPassword(password);
        checkRememberMe();
        clickLogin();
        preLoading();
    }

}
