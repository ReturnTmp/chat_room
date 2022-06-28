package com.typepanel;

import com.chatroom.ChatRoomUpPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class FilePanel extends JPanel {
    JLabel jLabel=new JLabel("所选文件路径：");
    public JTextField jTextField=new JTextField(25);
    JButton jButton=new JButton("浏览");
    public FilePanel() {
        super();
        setSize(ChatRoomUpPanel.width,ChatRoomUpPanel.anotherHeight*4/5);
        setName("file");
        setLayout(new FlowLayout());
        add(jLabel);
        add(jTextField);
        add(jButton);
        jButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent arg0)
            {
                JFileChooser fc=new JFileChooser("D:\\");
                int val=fc.showOpenDialog(null);    //文件打开对话框
                if(val==fc.APPROVE_OPTION)
                {
                    //正常选择文件
                    jTextField.setText(fc.getSelectedFile().toString());
                }
                else
                {
                    //未正常选择文件，如选择取消按钮
                    jTextField.setText("未选择文件");
                }
            }
        });
    }
}
