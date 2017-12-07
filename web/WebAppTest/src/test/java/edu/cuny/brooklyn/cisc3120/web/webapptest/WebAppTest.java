package edu.cuny.brooklyn.cisc3120.web.webapptest;

import static org.junit.Assert.assertEquals;

import java.net.MalformedURLException;
import java.net.URL;

import org.junit.Test;
import org.openqa.selenium.chrome.ChromeOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebAppTest {
    private final static Logger LOGGER = LoggerFactory.getLogger(WebAppTest.class);
    private final String REMOTE_WEBDRIVER_URL = "http://localhost:4444/wd/hub";
    private final String APP_URL_PREFIX = "https://localhost:8443";
    
    @Test 
    public void testAddCourse() throws MalformedURLException {
        GpaAppWrapper appWrapper 
            =  new GpaAppWrapper(new URL(REMOTE_WEBDRIVER_URL)
                    , new ChromeOptions()
                    , APP_URL_PREFIX);
        LOGGER.info("opened remote webdriver at " + REMOTE_WEBDRIVER_URL);
        
        appWrapper.goToAddCoursePage();
        String pageTitle = appWrapper.getPageTitle();
        assertEquals("Student GPA: Add Course", pageTitle);
        
        appWrapper.fillTheAddCoursePage("John Doe");
        LOGGER.info("filled the add course  page.");
        
        appWrapper.submitTheAddCoursePage();
        LOGGER.info("filled the add course  page.");
        
        String feedback = appWrapper.getAddCoursePageFeedback();
        assertEquals("Course added for student: " + "John Doe", feedback);
        LOGGER.info("checked the submission by student name.");
        
        // appWrapper.close();
    }
}
