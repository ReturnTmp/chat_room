package com.main;

import com.panel.MenuDownPanel;
import com.panel.MenuUpPanel;

import javax.swing.*;
import java.awt.*;

public class MenuFrame extends JFrame {
    public static int width=400;
    public static int height=800;

    MenuFrame(int id){
        setSize(width,height);
        setTitle("ChatMenu");

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        setLayout(new BorderLayout());
        MenuUpPanel menuUpPanel = new MenuUpPanel(id);
        add(menuUpPanel,BorderLayout.NORTH);
        MenuDownPanel menuDownPanel = new MenuDownPanel();
//        add(new JScrollPane(menuDownPanel));

        add(menuDownPanel,BorderLayout.CENTER);
        setVisible(true);
    }
}
