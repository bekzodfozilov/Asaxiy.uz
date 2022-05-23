package com.example.asaxiy_uz.Helper;

public class StringHelper {

    public static boolean isValid(String s){
        if(s != null && s.trim().length() > 1){
            return true;
        }
        return false;
    }
}
