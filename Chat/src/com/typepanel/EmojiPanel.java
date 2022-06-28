package com.typepanel;

import com.chatroom.ChatRoomUpPanel;
import com.emojiUI.EmojiButton;
import com.emojiUI.EmojiListener;
import com.imageclass.AllImage;

import javax.swing.*;
import java.awt.*;

public class EmojiPanel extends JScrollPane {
    public EmojiButton[]emojiButton;
    public EmojiListener emojiListener;
    public EmojiPanel() {
        super();
        setSize(ChatRoomUpPanel.width,ChatRoomUpPanel.anotherHeight*4/5);
        JPanel jPanel = new JPanel();
        emojiButton=initEmojiButtonArr(AllImage.emoji1Image);
//        EmojiButton[]tmp=initEmojiButtonArr(AllImage.emoji2Image);
//        System.arraycopy(emojiButton,emojiButton.length,tmp,0,tmp.length);
        setName("emoji");
        jPanel.setPreferredSize(new Dimension(ChatRoomUpPanel.width-40,ChatRoomUpPanel.anotherHeight*4/5));
        jPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        emojiListener = new EmojiListener();
        for (int i = 0; i < emojiButton.length; i++) {
            emojiButton[i].addActionListener(emojiListener);
            jPanel.add(emojiButton[i]);
        }
        setViewportView(jPanel);
    }
    public EmojiButton[] initEmojiButtonArr(Image[]emojiImage){
        EmojiButton[] res=new EmojiButton[emojiImage.length];
        for (int i = 0; i < res.length; i++) {
            res[i]=new EmojiButton(new ImageIcon(emojiImage[i]));
        }
        return res;
    }
}
