package com.chatroom;

import javax.swing.*;
import java.awt.*;

public class ChatRoomUpPanel extends JPanel {
    public static int width=ChatRoomFrame.width;
    public static int height=ChatRoomFrame.height*2/3;
    public static int anotherHeight=ChatRoomFrame.height/3;
    public ChatRoomUpPanel(){
        super();
        setPreferredSize(new Dimension(width,height));
        setLayout(new FlowLayout(FlowLayout.LEFT,0,0));
    }

    @Override
    public Component add(Component component) {
        Dimension comSize = component.getPreferredSize();
        int comWidth= (int) comSize.getWidth();
        int comHeight= (int) comSize.getHeight();
        Dimension parentSize = this.getPreferredSize();
        int parentWidth = (int) parentSize.getWidth();
        int parentHeight = (int) parentSize.getHeight();

        JLabel jLabel = new JLabel();
        jLabel.setPreferredSize(new Dimension(parentWidth-comWidth,comHeight));
        this.setPreferredSize(new Dimension(parentWidth,parentHeight+comHeight));

        super.add(component);
        super.add(jLabel);

        return null;
    }

    /**
     * 添加startLable方法
     * @param component
     * @param flag
     * @return
     */
    public Component add(Component component,boolean flag) {
        return super.add(component);
    }
}
