package com.user;

import java.util.HashMap;
import java.util.Map;

public class UserDao {
    public static Map<Integer,UserInfo> allUsersInfo = new HashMap<>();
    public static String fileName="userInfo.txt";
    static {
        //初始化userDao
        MyUserTools.initAllUsersInfo(fileName);
    }
}
