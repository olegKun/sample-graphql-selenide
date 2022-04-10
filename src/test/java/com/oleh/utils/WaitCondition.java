package com.oleh.utils;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.function.Function;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

@RequiredArgsConstructor
@Getter
public enum WaitCondition {
    visible(ExpectedConditions::visibilityOfElementLocated);

    private final Function<By, ExpectedCondition<WebElement>> type;


}
