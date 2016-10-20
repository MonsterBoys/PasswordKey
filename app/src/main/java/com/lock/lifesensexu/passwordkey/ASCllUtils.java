package com.lock.lifesensexu.passwordkey;

/**
 * Created by lifesensexu on 16/10/20.
 */

public class ASCllUtils {
    public static String stringToAscii(String value)
    {
        StringBuffer sbu = new StringBuffer();
        char[] chars = value.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            if(i != chars.length - 1)
            {
                sbu.append(((int)chars[i])*2-9).append(",");
            }
            else {
                sbu.append(((int)chars[i])*2-9);
            }
        }
        return sbu.toString();
    }
    public static String asciiToString(String value)
    {
        StringBuffer sbu = new StringBuffer();
        String[] chars = value.split(",");
        for (int i = 0; i < chars.length; i++) {
            sbu.append((char) ((Integer.parseInt(chars[i])+9)/2));
        }
        return sbu.toString();
    }
}
