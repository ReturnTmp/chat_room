package com.main;

import com.panel.ChangePanel;
import com.panel.LoginPanel;
import com.panel.RegisterPanel;

import javax.swing.*;

public class MainNetUI {
//    private JFrame jf_login,jf_chat;
//    private JTextField jtf_userName,jtf_pwd;
//    private JLabel la_name,la_pwd;
//    private JTextArea jta_input=new JTextArea(23,50);
//    private NetClient conn;


//    private void loginAction(String ip,int port) {
//        String name=jtf_userName.getText();
//        String pwd=jtf_pwd.getText();
//        conn=new NetClient(ip, port, jta_input);
//        if(conn.conn2Server()) {
//            if(conn.loginserver(name,pwd)) {
//                showMainUI();
//                conn.start();
//                jf_login.setVisible(false);
//            }
//        } else {
//            JOptionPane.showMessageDialog(null,"账号有误！user1~10","错误",JOptionPane.ERROR_MESSAGE);
//        }
//    }
//    private void showMainUI() {
//        jf_chat = new JFrame("聊天客户端");
//        jf_chat.setLayout(new FlowLayout());
//        jf_chat.setSize(600,800);
//        jf_chat.setLocationRelativeTo(null);
//        jf_chat.setDefaultCloseOperation(3);
//        JLabel  la_send=new JLabel("发送:");
//        JLabel  la_recive=new JLabel("收到:");
//        final JTextArea jta_output=new JTextArea(13,50);
//        JButton bu_send=new JButton("send");
//        bu_send.addActionListener(new ActionListener() {
//            public void actionPerformed(ActionEvent e) {
//                String msg=jta_output.getText();
//                conn.sendMsg(msg);
//            }
//        });
//        jf_chat.add(la_recive);
//        jf_chat.add(jta_input);
//        jf_chat.add(la_send);
//        jf_chat.add(jta_output);
//        jf_chat.add(bu_send);
//        jf_chat.setVisible(true);
//    }
    public void initUI(){
        JFrame jf=new JFrame();
        jf.setSize(500,500);
        jf.setTitle("Chat");
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
//        jf.setLayout(new FlowLayout());

        JTabbedPane jtp=new JTabbedPane();

        LoginPanel loginPanel = new LoginPanel(jf);
        RegisterPanel registerPanel = new RegisterPanel();
        ChangePanel changePanel = new ChangePanel();

        jtp.add(loginPanel.getName(),loginPanel);
        jtp.add(registerPanel.getName(),registerPanel);
        jtp.add(changePanel.getName(),changePanel);
        jf.add(jtp);
        jf.setVisible(true);



//        MyUserTools.print();
    }
    public static void main(String[] args) {
        new MainNetUI().initUI();
//        new MainNetUI().showLoginUI();
//		new MainNetUI().showMainUI();
    }
}

