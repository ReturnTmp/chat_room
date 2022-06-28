package com.company;

import com.user.MyUserTools;
import com.user.UserDao;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;

public class ClientThread extends Thread{
    Socket client;
    OutputStream ous;
    BufferedReader brd=null;
    DataOutputStream dous=null;
    DataInputStream dins=null;
    int id = 0,pwd = 0;
    String nickName=null;
    boolean checkFlag=false;//代表是否用户登陆成功的标志
    public ClientThread(Socket client) {
        this.client = client;
        try {
            this.brd = new BufferedReader(new InputStreamReader(client.getInputStream()));
            ous=client.getOutputStream();
//            dous=new DataOutputStream(ous);
            dins=new DataInputStream(client.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while(true){
            try {
                Thread.sleep(30);
                if(!checkFlag){
                String order=null;
                order = brd.readLine();
                id = Integer.valueOf(brd.readLine());
                pwd = Integer.valueOf(brd.readLine());
                if(!order.equals("login"))nickName=brd.readLine();
                if(order.equals("login")){
                    if(checkIdError(id)){
                        ous.write("idError".getBytes());
                    } else if(checkPwdError(id,pwd)){
                        ous.write("pwdError".getBytes());
                    }else if(checkIdOnlineError(id)){
                        ous.write("idOnlineError".getBytes());
                    } else {
                        ous.write("sucess\r\n".getBytes());
                        ous.write((UserDao.allUsersInfo.get(id)
                                .getNickName()+"\r\n").getBytes());
                        sendFriendInfo();
                        ClientThreadMap.allClientThread.put(id,this);
                        checkFlag=true;
                    }
//                    ous.write("\r\n".getBytes());
                    ous.flush();
                    if(!checkFlag)return;
                }else if(order.equals("register")){
                    if(checkIdRepeat(id)){
                        ous.write("idRepeat".getBytes());
                    } else{
                        ous.write("sucess".getBytes());
                        MyUserTools.changeUserInfo(id,pwd,nickName);
                    }
                    ous.write("\r\n".getBytes());
                    ous.flush();
                    return;
                }else if(order.equals("change")){
                    if(checkIdError(id)){
                        ous.write("idError".getBytes());
                    } else{
                        ous.write("sucess".getBytes());
                        MyUserTools.changeUserInfo(id,pwd,nickName);
                    }
                    ous.write("\r\n".getBytes());
                    ous.flush();
                    return;
                }
            } else {
                Integer totalLen=null;
                int destId=0;  
                    ByteArrayOutputStream bous = new ByteArrayOutputStream();
                    dous=new DataOutputStream(bous);
                    try{
                    totalLen=dins.readInt();
                    byte type=dins.readByte();
                    destId=dins.readInt();
                    int srcId=dins.readInt();
                    byte[]data = new byte[totalLen - 4 - 1 - 4 - 4];
                    dins.readFully(data);
//                    dins.read(data);
                    dous.writeInt(totalLen);
                    dous.writeByte(type);
                    dous.writeInt(destId);
                    dous.writeInt(srcId);
                    dous.write(data);
                    dous.flush();
                }finally {
                    if(totalLen==null){
                       ClientThreadMap.allClientThread.remove(id);
                       return;
                    } else{
                        //全体成员id为0
                        if(destId==0){
                            //发送给全体成员

                        }else if(ClientThreadMap.allClientThread.containsKey(destId)){
                            //destId在线
                            //获取destId的Socket和srcId的Socket
                            Socket destClient = ClientThreadMap.allClientThread.get(destId).client;
                            Socket srcClient = ClientThreadMap.allClientThread.get(id).client;
                            DataOutputStream destDous = new DataOutputStream(destClient.getOutputStream());
                            DataOutputStream srcDous = new DataOutputStream(srcClient.getOutputStream());
                            destDous.write(bous.toByteArray());
                            srcDous.write(bous.toByteArray());
                            destDous.flush();
                            srcDous.flush();

                        }
                    }
                }
//                String s=null;
//                try{
//                    s = brd.readLine();
//                }finally{
//                   if(s==null){
//                       ClientThreadMap.allClientThread.remove(id);
//                       return;
//                   }
//                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
                e.printStackTrace();
        }
        }
    }

    public void processMsg(){

    }
    public void sendFriendInfo(){
        StringBuffer s=new StringBuffer();
        int fiendNum=UserDao.allUsersInfo.keySet().size()-1;
        s.append(fiendNum+"\r\n");
        UserDao.allUsersInfo.forEach((key,value)->{
            if(key!=id){
                s.append(value.getId()+"\r\n"+value.getNickName()+"\r\n");
            }
        });
        try {
            ous.write(s.toString().getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    boolean checkIdError(int id){
        return !UserDao.allUsersInfo.containsKey(id);
    }
    boolean checkIdRepeat(int id){
        return UserDao.allUsersInfo.containsKey(id);
    }
    boolean checkIdOnlineError(int id){
        return ClientThreadMap.allClientThread.containsKey(id);
    }

    boolean checkPwdError(int id,int pwd){
        return UserDao.allUsersInfo.get(id).getPwd()!=pwd;
    }

}
