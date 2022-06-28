package com.chatroom;

import com.msg.MsgEmoji;
import com.msg.MsgHead;
import com.msg.MsgText;

import javax.swing.*;

public class MsgComponent {
    MsgHead msgHead;

    public MsgComponent(MsgHead msgHead) {
        this.msgHead = msgHead;
    }
    public JTextField getUserField(){
        return new JTextField(msgHead.srcId+" to "+msgHead.destId+"：");
    }
    public JTextField getDialogueField(){
        MsgText msgText = (MsgText) msgHead;
        return new JTextField(msgText.word);
    }
    public JLabel getEmojiLable(){
        MsgEmoji msgEmoji= (MsgEmoji) msgHead;
        return new JLabel(new ImageIcon(msgEmoji.bufferedImage));
    }

}
