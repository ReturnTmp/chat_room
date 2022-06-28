package com.chatroom;

import com.emojiUI.EmojiListener;
import com.msg.*;
import com.user.MyDao;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public class SendingListener implements ActionListener {
    ChatRoomUpPanel chatRoomUpPanel;
    ChatRoomTabbedPane chatRoomTabbedPane;
    ChatRoomDownPanel chatRoomDownPanel;
    int id=0;
    int myId=0;
    String panelName=null;
    JTextArea textSendingArea;
    JTextField filePathField;
    EmojiListener emojiListener=null;
    public static Socket client;
    public SendingListener(ChatRoomUpPanel chatRoomUpPanel,
                           ChatRoomTabbedPane chatRoomTabbedPane,
                           ChatRoomDownPanel chatRoomDownPanel,
                           EmojiListener emojiListener) {
        this.chatRoomUpPanel = chatRoomUpPanel;
        this.chatRoomTabbedPane = chatRoomTabbedPane;
        this.chatRoomDownPanel = chatRoomDownPanel;
        this.textSendingArea = chatRoomTabbedPane.textPanel.jTextArea;
        this.filePathField = chatRoomTabbedPane.filePanel.jTextField;
        this.emojiListener = emojiListener;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        try{
            initId();
            initMyId();
            panelName=chatRoomTabbedPane.getSelectedComponent().getName();
            OutputStream ous = client.getOutputStream();
            DataOutputStream dous = new DataOutputStream(ous);

            if(panelName.equals(chatRoomTabbedPane.textPanel.getName())){
                String sendingWord=textSendingArea.getText();
                textSendingArea.setText("");
                MsgText msgText = new MsgText(4 + 1 + 4 + 4 + 1024,
                        MsgType.TEXT, id, myId, sendingWord);
                byte[]data=MsgHead.packMsg(msgText);
                dous.write(data);
                dous.flush();
            } else if(panelName.equals(chatRoomTabbedPane.filePanel.getName())){
                String filePath=filePathField.getText();
                filePathField.setText("");
                File file = new File(filePath);
                int fileLen=MsgFile.File2byte(file).length;
                MsgFile msgFile = new MsgFile(4 + 1 + 4 + 4 + 1024 + fileLen,
                        MsgType.FILE, id, myId, filePath, file);
                byte[]data = MsgHead.packMsg(msgFile);
                dous.write(data);
                dous.flush();
            } else if(panelName.equals(chatRoomTabbedPane.emojiPanel.getName())){
                System.out.println("sendEmoji");
                ImageIcon icon=emojiListener.icon;
                int imageLen = MsgEmoji.image2byte(MsgEmoji.imageToBufferedImage(icon.getImage())).length;
                MsgEmoji msgEmoji = new MsgEmoji(4 + 1 + 4 + 4 + imageLen,
                        MsgType.EMOJI, id, myId, MsgEmoji.imageToBufferedImage(icon.getImage()));
                byte[] data = MsgHead.packMsg(msgEmoji);
                dous.write(data);
                dous.flush();
            }
        }catch(IOException e){
            e.printStackTrace();
        }

        //

    }
    public void initId(){
        if(chatRoomDownPanel.idBox.getSelectedItem().equals("AllFriends")){
            id=0;
        } else {
            id= Integer.valueOf((String) chatRoomDownPanel.idBox.getSelectedItem());
        }
    }
    public void initMyId(){
        MyDao.myInfo.forEach((key,value)->{
            myId=key;
        });
    }
}
