package com.chatroom;

import com.typepanel.EmojiPanel;
import com.typepanel.FilePanel;
import com.typepanel.TextPanel;

import javax.swing.*;

public class ChatRoomTabbedPane extends JTabbedPane {
    TextPanel textPanel = new TextPanel();
    FilePanel filePanel = new FilePanel();
    EmojiPanel emojiPanel = new EmojiPanel();
    public ChatRoomTabbedPane(){
        setSize(ChatRoomUpPanel.width,ChatRoomUpPanel.anotherHeight*4/5);
        add(textPanel.getName(),textPanel);
        add(filePanel.getName(),filePanel);
        add(emojiPanel.getName(),emojiPanel);

    }
}
