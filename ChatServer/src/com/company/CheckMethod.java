package com.company;

import com.user.UserDao;
import com.user.UserInfo;

public class CheckMethod {
    public static boolean checkId(int id){
        return UserDao.allUsersInfo.containsKey(id);
    }
    public static boolean checkPwd(int id,int pwd){
        if(UserDao.allUsersInfo.containsKey(id)){
            UserInfo userInfo = UserDao.allUsersInfo.get(id);
            if(userInfo.getPwd()==pwd){
                return true;
            } else {
                return false;
            }
        }else{
            return false;
        }
    }

    static void idPwdCheck(int id,int pwd){
        if(checkId(id)){
            if(!checkPwd(id,pwd))PopWindowMethod.pwdErrorWindow();
        } else {
            PopWindowMethod.idErrorWindow();
        }
    }
}
