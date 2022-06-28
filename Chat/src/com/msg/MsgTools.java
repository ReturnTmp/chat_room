package com.msg;

import java.io.IOException;

public class MsgTools {

    public static byte[]String2byte(String word,int maxLen){
        byte[]data=word.getBytes();
        if(data.length>maxLen){
            new IOException("word超长");
        }else{
            byte[]res=new byte[maxLen];
            for (int i = 0; i < data.length; i++) {
                res[i] = data[i];
            }
            return res;
        }
        return null;
    }
    public static String byte2String(byte[]data){
        int len=0;
        for (int i = 0; i < data.length; i++) {
            if(data[i]=='\0')break;
            len++;
        }
        byte[]res=new byte[len];
        for (int i = 0; i < res.length; i++) {
            res[i]=data[i];
        }
        return new String(res);
    }
}
