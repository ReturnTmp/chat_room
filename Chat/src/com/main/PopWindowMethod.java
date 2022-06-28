package com.main;

import javax.swing.*;

public class PopWindowMethod {
    static void sucessWindow(){
        JOptionPane.showMessageDialog(null,
                "成功","sucess", JOptionPane.NO_OPTION);
    }
    static void idErrorWindow(){
        JOptionPane.showMessageDialog(null,
                "id错误，请重新输入","错误",JOptionPane.ERROR_MESSAGE);
    }
    static void idRepeatWindow(){
        JOptionPane.showMessageDialog(null,
                "id已存在，请重新输入","错误",JOptionPane.ERROR_MESSAGE);
    }
    static void idOnlineWindow(){
        JOptionPane.showMessageDialog(null,
                "id已上线","错误",JOptionPane.ERROR_MESSAGE);
    }

    static void pwdErrorWindow(){
        JOptionPane.showMessageDialog(null,
                "password错误，请重新输入","错误",JOptionPane.ERROR_MESSAGE);
    }
}
