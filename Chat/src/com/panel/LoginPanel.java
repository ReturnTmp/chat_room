package com.panel;

import com.imageclass.AllImage;
import com.main.MainListener;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel{
    JLabel jl1,jl2;
    JTextField jta;
    JPasswordField jpf;
    JButton jbu;
    public LoginPanel(JFrame jf) {
        setSize(400,400);
        setBackground(Color.gray);
        setName("login");
        setLayout(new FlowLayout());
        int inputLenght=35;
        jl1=new JLabel("账号：");
        jta=new JTextField(inputLenght);
        jl2=new JLabel("密码：");
        jpf=new JPasswordField(inputLenght);
        add(jl1);
        add(jta);
        add(jl2);
        add(jpf);
        jbu=new JButton("登录");
        jbu.addActionListener(new MainListener(getName(),jta,jpf,jf));
        add(jbu);
        initFont();
        setVisible(true);
    }
    void initFont(){
        Font lableFont=new Font("黑体",10,20);
        Font textFont=new Font("黑体",10,20);
        jl1.setFont(lableFont);
        jl2.setFont(lableFont);
        jta.setFont(textFont);
        jpf.setFont(textFont);
        jbu.setFont(lableFont);
    }
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(AllImage.main_background,0,0,getWidth(),getHeight(),null);
    }

}
