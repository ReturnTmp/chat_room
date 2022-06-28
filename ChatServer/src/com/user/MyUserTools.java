package com.user;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;

public class MyUserTools {

    public static void initAllUsersInfo(String fileName){
        BufferedReader bufferedReader=null;
        try{
            bufferedReader = new BufferedReader(new FileReader(fileName));
            while(true){
                String[]sArr=new String[3];
                for (int i = 0; i < 3; i++) {
                    sArr[i]=bufferedReader.readLine();
                    if(sArr[i]==null)break;
                }
                if(sArr[2]==null)break;
//                allUsersInfo.add(new UserInfo(Integer.valueOf(sArr[0]),
//                        Integer.valueOf(sArr[1]),sArr[2]));
                UserDao.allUsersInfo.put(Integer.valueOf(sArr[0]),new UserInfo(Integer.valueOf(sArr[0]),
                        Integer.valueOf(sArr[1]),sArr[2]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void changeUserInfo(int id,int pwd,String nickName){
        UserDao.allUsersInfo.put(id,new UserInfo(id,pwd,nickName));
        updataUserDao();
    }
    public static void updataUserDao(){
        StringBuffer stringBuffer = new StringBuffer();
        UserDao.allUsersInfo.forEach((key,value)->{
            stringBuffer.append(key+"\r\n"+ value.getPwd()+"\r\n"+value.getNickName()+"\r\n");
        });
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(UserDao.fileName);
            fileOutputStream.write(stringBuffer.toString().getBytes());
            fileOutputStream.flush();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
