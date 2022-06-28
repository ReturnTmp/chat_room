package com.main;

import com.chatroom.ChatRoomFrame;
import com.chatroom.ChatRoomUpPanel;
import com.chatroom.MsgComponent;
import com.msg.MsgFile;
import com.msg.MsgHead;
import com.msg.MsgType;
import com.user.MyDao;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.net.Socket;

public class ClientThread extends Thread{
    public static Socket client=null;
    public DataInputStream dins=null;
    public ChatRoomFrame chatRoomFrame=null;
    public ChatRoomUpPanel chatRoomUpPanel=null;
    public JScrollPane chatRoomUpPane=null;
    public ClientThread(ChatRoomFrame chatRoomFrame) {
        try {
            dins = new DataInputStream(client.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.chatRoomFrame=chatRoomFrame;
        this.chatRoomUpPanel= chatRoomFrame.chatRoomUpPanel;
        this.chatRoomUpPane= chatRoomFrame.chatRoomUpPane;
    }

    @Override
    /**
     *将读取到的bytes解包成MsgHead
     * 并使用Msg
     */
    public void run() {
        try {
            System.out.println("run");
            while(true){
                int totalLen= dins.readInt();
                byte[]data=new byte[totalLen-4];
                System.out.println("读取data");
                dins.readFully(data);
                System.out.println("读取data结束");
                MsgHead msgHead = MsgHead.parseMsg(data);
                MsgComponent msgComponent = new MsgComponent(msgHead);
                if(msgHead.type== MsgType.TEXT){
                    JTextField userField = msgComponent.getUserField();
                    JTextField dialogueField = msgComponent.getDialogueField();
                    chatRoomUpPanel.add(userField);
                    chatRoomUpPanel.add(dialogueField);
                }else if(msgHead.type== MsgType.EMOJI){
                    JTextField userField = msgComponent.getUserField();
                    JLabel emojiLable = msgComponent.getEmojiLable();
                    System.out.println("Emojirecive");
                    System.out.println(userField==null);
                    System.out.println(emojiLable==null);
                    chatRoomUpPanel.add(userField);
                    chatRoomUpPanel.add(emojiLable);
                }else if(msgHead.type== MsgType.FILE){
                    if(msgHead.srcId!= MyDao.getMyId()){
                        int option=JOptionPane.showConfirmDialog(null,
                                msgHead.srcId+"正在发送文件,请问是否接收", "文件", JOptionPane.YES_NO_OPTION);
                        if(option==0){
                            //确定接收
                            String savedFilePath = JOptionPane.showInputDialog("请输入文件保存路径： ");
                            File file = new File(savedFilePath);
                            MsgFile msgFile= (MsgFile) msgHead;
                            saveFile(file,msgFile.file);
                        }
                    }
                }

                chatRoomFrame.validate();
                chatRoomUpPane.getViewport().setViewPosition(new Point
                        (0,chatRoomUpPane.getVerticalScrollBar().getMaximum()));
            }
        }catch (IOException e){

        }

    }
    public void saveFile(File destFile,File srcFile){
        try {
            FileInputStream fis = new FileInputStream(srcFile);
            FileOutputStream fos = new FileOutputStream(destFile);
            byte[]b = new byte[1024];
            int len=0;
            while((len=fis.read(b))!=-1) {
                fos.write(b);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
