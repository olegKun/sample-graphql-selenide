package com.oleh.page;

import org.openqa.selenium.By;
import com.codeborne.selenide.Selenide;

public class MainTestPage {
    private static final By START_BUTTON = By.id("btn_basic_example");
    public static MainTestPage open(){
        Selenide.open();
        return new MainTestPage();
    }

    public ExamplesPage clickStart(){
        Selenide.$(START_BUTTON).click();
        return new ExamplesPage();
    }
}
