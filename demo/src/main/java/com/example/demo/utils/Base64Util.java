package com.example.demo.utils;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

public class Base64Util {
    public static void main(String[] args) throws UnsupportedEncodingException {
        //String base64encodedString = Base64.getEncoder().encodeToString("AU3dfpsR6GHgSIE923O8Q_jNkRZi4qx25pF8c7VOPOF1PS6XaFDSpObVQXbmLySi_eaEhvcOqpOPbZMj:EF6GHmWJYViAeRnUgunewpIJ7pQkj-E2vE7yGnJjeZwp09PWE4GLSRLyKuMut3MnVonD2r8BKBKMnILq".getBytes("utf-8"));
        //String base64encodedString = Base64.getEncoder().encodeToString("application/json".getBytes("utf-8"));

        String base64encodedString = new String(Base64.getDecoder().decode("72c0a50d840626050145ccf72b733e32f31bb21323d493f9d00290b37676a138bcdf1d6415d9f08aad117450e23a4f4"),"utf-8");
        System.out.println("Base64 编码字符串 (基本) :" + base64encodedString);
    }
}
