package com.panel;

import com.imageclass.AllImage;
import com.main.MainListener;

import javax.swing.*;
import java.awt.*;

public class ChangePanel extends JPanel{
    JLabel jl1,jl2,jl3;
    JTextField jta1,jta2;
    JPasswordField jpf;
    JButton jbu;
    public ChangePanel() {
        setSize(400,400);
        setBackground(Color.gray);
        setName("change");
        setLayout(new FlowLayout());
        int inputLenght=35;
        jl1=new JLabel("账号：");
        jta1=new JTextField(inputLenght);
        jl2=new JLabel("密码：");
        jpf=new JPasswordField(inputLenght);
        jl3=new JLabel("昵称：");
        jta2=new JTextField(inputLenght);
        add(jl1);
        add(jta1);
        add(jl2);
        add(jpf);
        add(jl3);
        add(jta2);
        jbu=new JButton("修改");
        jbu.addActionListener(new MainListener(getName(),jta1,jta2,jpf));
        add(jbu);
        initFont();
        setVisible(true);
    }
    void initFont(){
        Font lableFont=new Font("黑体",10,20);
        Font textFont=new Font("黑体",10,20);
        jl1.setFont(lableFont);
        jl2.setFont(lableFont);
        jl3.setFont(lableFont);
        jta1.setFont(textFont);
        jta2.setFont(textFont);
        jpf.setFont(textFont);
        jbu.setFont(lableFont);
    }
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(AllImage.main_background,0,0,getWidth(),getHeight(),null);
    }
}
