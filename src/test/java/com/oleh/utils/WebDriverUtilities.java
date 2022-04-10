package com.oleh.utils;

import static io.vavr.control.Validation.combine;

import io.vavr.Tuple;

import java.time.Duration;
import java.util.function.Function;
import java.util.stream.Stream;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtilities {

    static WebDriverWait getWebDriverWait(WebDriver webDriver){
        return new WebDriverWait(webDriver, 1000L);
    }

//    public static WebElement waitFor(By locator, WaitCondition condition) {
//        getWebDriverWait().until(ExpectedConditions.visibilityOfElementLocated(locator));
//        return getWebDriverWait(new ChromeDriver()).until(condition.getType().apply(locator));
//    }
//
//    public static WebElement waitFor(By locator, WaitCondition condition) {
//        combine(
//                Tuple.of("",9)
//        )
//    }
}
