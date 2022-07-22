package com.example.SpringbootApi.utility;

import java.time.format.DateTimeFormatter;

public class DateTimeUtility {
    
    private DateTimeUtility() {}
    
    public static final DateTimeFormatter DATE_TIME_UI = DateTimeFormatter.ofPattern("yyyy/MM/dd　HH:mm:ss");

}
