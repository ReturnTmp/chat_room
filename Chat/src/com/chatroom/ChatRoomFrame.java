package com.chatroom;

import com.main.ClientThread;

import javax.swing.*;
import java.awt.*;

public class ChatRoomFrame extends JFrame {
    public static int width=800;
    public static int height=900;
    public ChatRoomUpPanel chatRoomUpPanel=null;
    public JScrollPane chatRoomUpPane=null;
    /**
     * 完成聊天室界面初始化
     * 开启ClientThread线程
     * @param id
     */
    public ChatRoomFrame(int id){

        setTitle("ChatRoom");
        setSize(width,height);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        chatRoomUpPanel = new ChatRoomUpPanel();
        chatRoomUpPane = new JScrollPane(chatRoomUpPanel);
        initChatRoomUp(chatRoomUpPanel,chatRoomUpPane);
        add(chatRoomUpPane,BorderLayout.NORTH);

        ChatRoomTabbedPane chatRoomTabbedPane = new ChatRoomTabbedPane();
        add(chatRoomTabbedPane,BorderLayout.CENTER);

        ChatRoomDownPanel chatRoomDownPanel = new ChatRoomDownPanel(id);
        chatRoomDownPanel.sendButton.addActionListener(new SendingListener(
                chatRoomUpPanel, chatRoomTabbedPane, chatRoomDownPanel,
                chatRoomTabbedPane.emojiPanel.emojiListener));
        add(chatRoomDownPanel,BorderLayout.SOUTH);

        ClientThread clientThread = new ClientThread(this);
        clientThread.start();
        setVisible(true);

    }

    /**
     * 填充空白Lable
     * 设置父容器大小
     * @param chatRoomUpPanel
     * @param chatRoomUpPane
     */
    public void initChatRoomUp(ChatRoomUpPanel chatRoomUpPanel,JScrollPane chatRoomUpPane){
        Dimension size = chatRoomUpPanel.getPreferredSize();
        JLabel fillLable = new JLabel();
        fillLable.setPreferredSize(size);
        chatRoomUpPanel.add(fillLable,true);
        chatRoomUpPane.setPreferredSize(size);
    }
}
