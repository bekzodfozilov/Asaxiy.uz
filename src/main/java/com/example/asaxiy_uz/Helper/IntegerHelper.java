package com.example.asaxiy_uz.Helper;

public class IntegerHelper {

    public static boolean Isvalid(String s){
        try {
            if(s != null && Integer.parseInt(s) >= 0)
                return true;
        }catch (Exception e){
            return false;
        }
        return false;
    }

    public static Integer isInt(String s){
        if(Isvalid(s)){
            return Integer.parseInt(s);
        }
        return null;
    }
}
