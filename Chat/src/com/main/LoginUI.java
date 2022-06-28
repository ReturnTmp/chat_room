package com.main;

import sun.security.krb5.internal.NetClient;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginUI {
    private JFrame jf_login,jf_chat;
    private JTextField jtf_userName,jtf_pwd;
    private JLabel la_name,la_pwd;
    private JTextArea jta_input=new JTextArea(23,50);
    private NetClient conn;
    public void showLoginUI() {
        jf_login = new JFrame("聊天客户端，请登录");
        jf_login.setLayout(new FlowLayout());
        jf_login.setSize(400,400);
        jf_login.setLocationRelativeTo(null);
        jf_login.setDefaultCloseOperation(3);

        la_name=new JLabel("用户名:");
        la_pwd=new JLabel("密  码:");
        jtf_userName=new JTextField(12);
        jtf_pwd=new JTextField(12);
        jf_login.add(la_name);
        jf_login.add(jtf_userName);
        jf_login.add(la_pwd);
        jf_login.add(jtf_pwd);
        JButton login=new JButton("login");
        login.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
//                loginAction("localhost", 9004);
            }
        });
        jf_login.add(login);
        jf_login.setVisible(true);
    }
}
