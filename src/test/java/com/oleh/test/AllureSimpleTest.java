package com.oleh.test;

import static io.qameta.allure.Allure.step;

import io.qameta.allure.Step;
import io.qameta.allure.model.Status;

import org.testng.annotations.Test;

public class AllureSimpleTest {

    @Test(description = "allureSimpleTest displayName")
    public void allureSimpleTest() {
        step("Simple step");
        step("Simple step with status", Status.FAILED);
        step("Simple lambda step", () -> {
            step("Simple step inside lambda step");
        });
        simpleTestMethod("method parameter");
    }

    @Step("Simple test method with step annotation")
    public void simpleTestMethod(String param) {
        step("Method parameter: " + param);
        step("Simple step inside test method");
    }
}
