package com.user;

import java.util.HashMap;
import java.util.Map;

public class MyDao {
    public static Map<Integer,UserInfo> myInfo = new HashMap<>();
    public static Map<Integer,UserInfo> myFriendInfo = new HashMap<>();
    public static int getMyId(){
        int id=0;
        for(Map.Entry<Integer,UserInfo>entry:myInfo.entrySet()){
            id = entry.getKey();
        }
        return id;
    }
}
