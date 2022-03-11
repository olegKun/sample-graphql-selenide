package com.oleh.page.date.picker;

import static com.codeborne.selenide.Selenide.executeJavaScript;

import com.oleh.page.PageObject;

public abstract class BaseDatePickerPage extends PageObject {

    public PageObject setDateByName(String name, String date) {

        executeJavaScript(
                String.format("$('[name=\"%s\"]').val('%s')",
                        name, date)
        );
        return this;
    }

    public PageObject setDateByClass(String name, String date) {
        executeJavaScript(
                String.format("$('[class=\"%s\"]').val('%s')",
                        name, date)
        );
        return this;
    }
}
