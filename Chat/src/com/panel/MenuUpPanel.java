package com.panel;

import com.imageclass.AllImage;
import com.main.MenuFrame;
import com.user.UserLable;

import javax.swing.*;
import java.awt.*;

public class MenuUpPanel extends JPanel {
    public MenuUpPanel(int id){
        super();
        setSize(MenuFrame.width,MenuFrame.height/8);
        UserLable userLable = new UserLable(id);
        add(userLable);
//        setBackground(Color.gray);
        setLayout(new FlowLayout(FlowLayout.LEFT));
    }

    @Override
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(AllImage.up_background,0,0,getWidth(),getHeight(),null);
    }
}
