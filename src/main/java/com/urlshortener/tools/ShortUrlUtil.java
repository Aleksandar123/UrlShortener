package com.urlshortener.tools;

import java.util.Random;

/**
 * Created by aleksandar on 28.12.16.
 */
public class ShortUrlUtil {
    public static String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";



    public String randomString(){
         Random random = new Random();

        String randomString ="";

        for(int i=0;i<6;i++){
            randomString+= characters.charAt(random.nextInt(characters.length()));
        }

        return randomString;
    }
}
