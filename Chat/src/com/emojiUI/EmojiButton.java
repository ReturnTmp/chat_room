package com.emojiUI;

import javax.swing.*;
import java.awt.*;

public class EmojiButton extends JButton {
    ImageIcon icon;
    Boolean mouseFlag=false;
    public EmojiButton(ImageIcon icon){
        this.icon=icon;
//        setIcon(icon);
        setPreferredSize(new Dimension(40,40));
        setContentAreaFilled(false);
//        setBorderPainted(false);
    }
    protected void paintComponent(Graphics graphics) {
        super.paintComponent(graphics);
        graphics.drawImage(this.icon.getImage(),0,0,getWidth(),getHeight(),null);
//        if(!mouseFlag){
//            graphics.drawImage(icon.getImage(),0,0,getWidth(),getHeight(),null);
//        } else {
//            graphics.drawImage(getGrayIcon(icon).getImage(),0,0,getWidth(),getHeight(),null);
//        }
    }
//    public ImageIcon getGrayIcon(ImageIcon icon){
//        BufferedImage bufferedImage=new BufferedImage(
//                icon.getIconHeight(),icon.getIconHeight(),BufferedImage.TYPE_INT_RGB);
//        for (int i = 0; i < bufferedImage.getHeight(); i++) {
//            for (int j = 0; j < bufferedImage.getWidth(); j++) {
//                bufferedImage.setRGB(j,i,new Color(50,50,50).getRGB());
//            }
//        }
//        bufferedImage.getGraphics().drawImage(
//                icon.getImage(),icon.getIconWidth(),icon.getIconHeight(),null);
//        return new ImageIcon(bufferedImage);
//    }
}
