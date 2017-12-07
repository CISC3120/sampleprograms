package edu.cuny.brooklyn.cisc3120.web.webapptest;

import java.net.MalformedURLException;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GpaAppWrapper {
    private final static int TIME_OUT_SECONDS = 5;
    private WebDriver webDriver;
    private String appUrlPrefix;
    
    public GpaAppWrapper(URL webDriverUrl
            ,  Capabilities browserCapabilities
            ,  String appUrlPrefix) {
        webDriver = new RemoteWebDriver(webDriverUrl, browserCapabilities);
        this.appUrlPrefix = appUrlPrefix;
    }
    
    public void goToAddCoursePage() throws MalformedURLException {
        URL url = new URL(appUrlPrefix + "/gpa/addcourse");
        webDriver.navigate().to(url);
    }
    
    public String getPageTitle() {
        return webDriver.getTitle();
    }
    
    public void fillTheAddCoursePage(String name) {
        WebElement elem = webDriver.findElement(By.id("inputStudentName"));
        elem.sendKeys(name);
        
        elem = webDriver.findElement(By.id("inputStudentId"));
        elem.sendKeys("987654321");
        
        elem = webDriver.findElement(By.id("inputCouseNumber"));
        elem.sendKeys("CISC 3140");
        
        elem = webDriver.findElement(By.id("inputCouseTitle"));
        elem.sendKeys("Design and Implementation II");
        
        elem = webDriver.findElement(By.id("inputTerm"));
        elem.sendKeys("Spring 2018");    
    
        elem = webDriver.findElement(By.id("inputCreditHours"));
        elem.sendKeys("3");

        elem = webDriver.findElement(By.id("inputGrade"));
        elem.sendKeys("A+");
    }

    public void submitTheAddCoursePage() {
        WebElement elem = webDriver.findElement(By.xpath("//div/form/button"));
        elem.submit();
    }


    public String getAddCoursePageFeedback() {
        WebElement elem = (new WebDriverWait(webDriver, TIME_OUT_SECONDS))
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//body/div/div/div/div/h4")));
        return elem.getText();
    }
    
    
    public void close() {
        webDriver.close();
    }

}
