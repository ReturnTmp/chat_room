package com.main;

import com.chatroom.SendingListener;
import com.user.MyDao;
import com.user.UserInfo;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;

public class MainListener implements ActionListener {
    String panelName;
    JTextField jta1,jta2;
    JPasswordField jpf;
    JFrame jf;
    String ip="localhost";
    int port=9000;
    OutputStream ous=null;
    BufferedReader brd=null;
    Socket client=null;
    int id=0,pwd=0;
    String nickName=null;
    String serverReply=null;
    public MainListener(String panelName,JTextField jta1, JTextField jta2, JPasswordField jpf) {
        this.panelName = panelName;
        this.jta1 = jta1;
        this.jta2 = jta2;
        this.jpf = jpf;
    }
    public MainListener(String panelName,JTextField jta1, JPasswordField jpf,JFrame jf) {
        this.panelName = panelName;
        this.jta1 = jta1;
        this.jpf = jpf;
        this.jf=jf;
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        initUserInfo();
        initUserInAndOutStream();
        sendToServerUserInfo();
        readServerReply();
        if(panelName.equals("login")){
            if(serverReply.equals("idError")){
                PopWindowMethod.idErrorWindow();
            } else if(serverReply.equals("pwdError")){
                PopWindowMethod.pwdErrorWindow();
            } else if(serverReply.equals("idOnlineError")){
                PopWindowMethod.idOnlineWindow();
            } else if(serverReply.equals("sucess")){
                try {
                    nickName=brd.readLine();
                    initFriendDao();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                MyDao.myInfo.put(id,new UserInfo(id,pwd,nickName));
                PopWindowMethod.sucessWindow();
                //1.隐藏主页面
                jf.setVisible(false);
                //2.打开MenuFrame
                new MenuFrame(id);
                ClientThread.client=client;
                SendingListener.client=client;
            }

        } else if(panelName.equals("register")){
            if(serverReply.equals("idRepeat")){
                PopWindowMethod.idRepeatWindow();
            } else if(serverReply.equals("sucess")){
                PopWindowMethod.sucessWindow();
            }
        } else if(panelName.equals("change")){
            if(serverReply.equals("idError")){
                PopWindowMethod.idErrorWindow();
            } else if(serverReply.equals("sucess")){
                PopWindowMethod.sucessWindow();
            }
        }
        closeClient();
    }

    public void initFriendDao() {
        try {
            int friendNum=Integer.valueOf(brd.readLine());
            int id=0;
            String nickName=null;
            while(friendNum>0){
                friendNum--;
                id=Integer.valueOf(brd.readLine());
                nickName= brd.readLine();
                MyDao.myFriendInfo.put(id,new UserInfo(id,nickName));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void closeClient(){
        if(!(panelName.equals("login")&&serverReply.equals("sucess"))) {
            try {
                client.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    void sendToServerUserInfo(){
        try {
            ous.write((panelName+"\r\n").getBytes());
            ous.write((id+"\r\n").getBytes());
            ous.write((pwd+"\r\n").getBytes());
            if(nickName!=null)ous.write((nickName+"\r\n").getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    void readServerReply(){
        try {
            serverReply=brd.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    void initUserInfo(){
        id=Integer.valueOf(jta1.getText());
        pwd=Integer.valueOf(String.copyValueOf(jpf.getPassword()));
        if(jta2!=null)nickName=jta2.getText();
    }
    void initUserInAndOutStream(){
        try {
            client=new Socket(ip,port);
            ous=client.getOutputStream();
            brd=new BufferedReader(new InputStreamReader(client.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
