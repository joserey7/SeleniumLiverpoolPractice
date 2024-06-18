package utils;

import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import hooks.BaseTest;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Helpers {

    private static String getBase64Screenshot() {
        return ((TakesScreenshot) BaseTest.driver).getScreenshotAs(OutputType.BASE64);
    }

    public static void takeScreenShot(){
        ExtentCucumberAdapter.getCurrentStep()
                .info(MediaEntityBuilder.createScreenCaptureFromBase64String(getBase64Screenshot())
                        .build());
    }

    public static WebElement waitToBeVisible(By element){
        Wait<WebDriver> wait = new FluentWait<>(BaseTest.driver)
                .withTimeout(Duration.ofSeconds(10))
                .pollingEvery(Duration.ofMillis(500))
                .ignoring(NoSuchElementException.class);
        return wait.until(driver -> driver.findElement(element));
    }

    public static void scrollToElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) BaseTest.driver;
        js.executeScript("arguments[0].scrollIntoView(true);", element);
    }

}
