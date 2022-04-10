package com.oleh.selenium;


import org.testng.annotations.Test;
import com.codeborne.selenide.Selenide;
import com.oleh.page.JqueryDatePickerPage;
import com.oleh.page.MainTestPage;

public class DatePickerTest extends BaseTest {

    @Test
    public void sampleTest() {
        getWebDriver().get("https://www.youtube.com/");
//        if (true){
//            throw new RuntimeException();
//        }
        JqueryDatePickerPage jqueryDatePickerPage = new JqueryDatePickerPage();
//        jqueryDatePickerPage.openJqueryDatePickerPage()
//                .setDateByName("from","16.01.2009");

//        MainTestPage.open().clickStart()
//                .clickBootstrapDatePicker()
//                .setDateByClass("form-control","16.01.2009");
//
//        BootstrapDatePickerPage page = new BootstrapDatePickerPage();
    }

    @Test
    public void testGoogle() throws InterruptedException {
        BaseTest.getWebDriver().get("https://www.google.com/");

    }

//    public static void main(String[] args) {
//        Set<String> strings = new TreeSet<>();
//        strings.add("c");
//        strings.add("b");
//        strings.add("a");
//        strings.forEach(System.out::println);
//        int[] ints = new int[]{1,2,3};
////        Arrays.rev(ints,Collections.reverseOrder());
////        System.out.println(Arrays.toString(ints));
//    }
}
