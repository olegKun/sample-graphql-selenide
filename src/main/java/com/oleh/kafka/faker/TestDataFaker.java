package com.oleh.kafka.faker;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TestDataFaker {

    public String date() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
        return dateFormat.format(new Date());
    }

    public String amount(){
        return "2000";
    }
}
