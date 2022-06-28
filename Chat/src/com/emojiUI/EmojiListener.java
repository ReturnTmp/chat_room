package com.emojiUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class EmojiListener extends MouseAdapter implements ActionListener{
    public ImageIcon icon=null;
    public EmojiButton source=null;
    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        source = (EmojiButton) actionEvent.getSource();
        icon= source.icon;
        System.out.println("选中");
    }
    public void mouseMoved(MouseEvent var1) {
        System.out.println("移动");
        Rectangle bounds = source.getBounds();
        int x=var1.getX();
        int y=var1.getY();
        if(bounds.contains(new Point(x,y))){
            source.mouseFlag=true;
        }else{
            source.mouseFlag=false;
        }
    }
}
