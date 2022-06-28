package com.typepanel;

import com.chatroom.ChatRoomUpPanel;

import javax.swing.*;
import java.awt.*;

public class TextPanel extends JScrollPane {
    public JTextArea jTextArea=null;
    public TextPanel(){
        super();
        setSize(ChatRoomUpPanel.width,ChatRoomUpPanel.anotherHeight*4/5);
        setName("text");
        JPanel jPanel = new JPanel();
        jPanel.setLayout(new FlowLayout());
        jTextArea = new JTextArea(8, 65);
        jPanel.add(jTextArea);
        setViewportView(jPanel);
    }
}
