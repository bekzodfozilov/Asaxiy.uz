package com.example.asaxiy_uz.Helper;

import java.text.SimpleDateFormat;

public class DateHelper {
    public static String isValid(String date){
        return new SimpleDateFormat("yyy-MM-dd").format(date);
    }
}
