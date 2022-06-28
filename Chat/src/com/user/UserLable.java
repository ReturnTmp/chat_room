package com.user;

import com.imageclass.AllImage;

import javax.swing.*;
import java.util.Random;

public class UserLable extends JLabel {
    public UserLable(int id) {
        UserInfo userInfo= MyDao.myInfo.get(id);
        setIcon(new ImageIcon(AllImage.privateImage
                [new Random().nextInt(AllImage.privateImage.length)]));
        String info="<html><body>"+"用户名："+userInfo.getNickName()+"<br>"
                +"ID："+userInfo.getId()+"<body></html>";
        setText(info);
    }
}
