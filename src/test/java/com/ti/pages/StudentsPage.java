package com.ti.pages;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public class StudentsPage extends MainPage{

    @FindBy(linkText = "Students")
    private WebElement navStudents;

    @FindBy(name = "s_gender")
    private List<WebElement> rdnGender;

    @FindBy(id = "firstname")
    private WebElement txtFirstName;

    @FindBy(id = "lastname")
    private WebElement txtLastName;

    @FindBy(id = "Dob")
    private WebElement dtpDateOfBirth;

    @FindBy(xpath = "//td[contains(@class,'day')]")
    private List<WebElement> tdSelectDay;

    @FindBy(id = "current_address")
    private WebElement txtCurrentAddress;

    @FindBy(id = "Email")
    private WebElement txtEmailAddress;

    @FindBy(name ="Username" )
    private WebElement txtUserName;

    @FindBy(name = "Password")
    private WebElement txtPassword;

    @FindBy(id = "ConfirmPassword")
    private WebElement txtConfirmPass;

    @FindBy(id = "Rollno")
    private WebElement txtRollNumber;

    @FindBy(xpath = "//tr[contains(@role,'row')]")
    private List<WebElement> trStudentsRows;

    public void clickStudents(){
        navStudents.click();
        preLoading();
    }

    private void selectGender(String gender){
        for (WebElement optGender:rdnGender) {
            if (optGender.getAttribute("value").equals(gender)){
                optGender.click();
                break;
            }
        }
    }

    private void setFirstName(String firstName){
        txtFirstName.clear();
        txtFirstName.sendKeys(firstName);
    }

    private void setLastName(String lastName){
        txtLastName.clear();
        txtLastName.sendKeys(lastName);
    }

    private void setDayOfBirth(String selectedDay){
        dtpDateOfBirth.click();

        new WebDriverWait(driver, Duration.ofSeconds(8))
                .until(ExpectedConditions.visibilityOfAllElements(tdSelectDay));
        for (WebElement day:tdSelectDay) {
            if (day.getText().equals(selectedDay)){
                day.click();
                break;
            }
        }
    }

    private void setCurrentAddress(String currentAddress){
        txtCurrentAddress.clear();
        txtCurrentAddress.sendKeys(currentAddress);
    }

    public void studentPersonalDetails(String ... detail){
        selectGender(detail[0]);
        setFirstName(detail[1]);
        setLastName(detail[2]);
        setDayOfBirth(detail[3]);
        setCurrentAddress(detail[4]);
    }

    private void setEmailAddress(String email){
        txtEmailAddress.clear();
        txtEmailAddress.sendKeys(email);
    }

    private void setUserName(String userName){
        txtUserName.clear();
        txtUserName.sendKeys(userName);
    }

    private void setPassword(String password){
        txtPassword.clear();
        txtPassword.sendKeys(password);
    }

    private void setConfirmPassword(String confPass){
        txtConfirmPass.clear();
        txtConfirmPass.sendKeys(confPass);
    }

    public void accountInformation(Map<String,String> accountInfo){
        setEmailAddress(accountInfo.get("email"));
        setUserName(accountInfo.get("user"));
        setPassword(accountInfo.get("password"));
        setConfirmPassword(accountInfo.get("password"));
    }

    public void schoolDetails(String rollNumber){
        txtRollNumber.clear();
        txtRollNumber.sendKeys(rollNumber);
        txtRollNumber.submit();
        preLoading();
    }

    public void validateStudentIsAdded(String name){
        try {
            new WebDriverWait(driver, Duration.ofSeconds(8))
                    .until(ExpectedConditions.visibilityOfAllElements(trStudentsRows));
        }catch (TimeoutException te){
            preLoading();
            new WebDriverWait(driver, Duration.ofSeconds(8))
                    .until(ExpectedConditions.visibilityOfAllElements(trStudentsRows));
        }

        WebElement newStudentRow = trStudentsRows.get(trStudentsRows.size()-1);
        Assert.assertTrue(newStudentRow.getText().contains(name));
    }

    public void deleteStudent(){
        deleteRow();
        confirmationWindow();
    }
}
