package com.lmcertfication.util;

public class Utilities {

public boolean isNumeric(String s) {  
    return s != null && s.matches("[-+]?\\d*\\.?\\d+");  
}

}