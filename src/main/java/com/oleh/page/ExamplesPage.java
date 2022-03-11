package com.oleh.page;

import static com.codeborne.selenide.Selenide.$;

import org.openqa.selenium.By;

public class ExamplesPage {
private static final By DATE_PICKERS = By.cssSelector("i.glyphicon-chevron-right");

    public BootstrapDatePickerPage clickBootstrapDatePicker(){
        $(DATE_PICKERS).click();
        $(By.partialLinkText("Bootstrap Date Picker")).click();
        return new BootstrapDatePickerPage();
    }
}
