package com.chatroom;

import com.user.MyDao;

import javax.swing.*;
import java.awt.*;

public class ChatRoomDownPanel extends JPanel {
    JLabel jLabel=new JLabel("send to:");
    JComboBox idBox=new JComboBox();
    JButton sendButton=new JButton("send");
    public ChatRoomDownPanel(int id){
        super();
        setSize(ChatRoomUpPanel.width,ChatRoomUpPanel.anotherHeight/5);
        setLayout(new FlowLayout(FlowLayout.RIGHT));
        idBox.addItem("AllFriends");
        MyDao.myFriendInfo.forEach((key,value)->{
            idBox.addItem(key+"");
        });
        idBox.setSelectedItem(id+"");
        add(jLabel);
        add(idBox);
        add(sendButton);
    }


}
