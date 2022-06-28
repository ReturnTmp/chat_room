package com.company;

import javax.swing.*;

public class PopWindowMethod {
    static void changeSucessWindow(){
        JOptionPane.showMessageDialog(null,
                "修改成功","sucess",JOptionPane.NO_OPTION);
    }
    static void registerSucessWindow(){
        JOptionPane.showMessageDialog(null,
                "注册成功","sucess", JOptionPane.NO_OPTION);
    }
    static void idErrorWindow(){
        JOptionPane.showMessageDialog(null,
                "id错误，请重新输入","错误",JOptionPane.ERROR_MESSAGE);
    }
    static void pwdErrorWindow(){
        JOptionPane.showMessageDialog(null,
                "password错误，请重新输入","错误",JOptionPane.ERROR_MESSAGE);
    }
}
