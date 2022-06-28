package com.tree;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

public class MyTreeNode extends DefaultMutableTreeNode {

    private static final long serialVersionUID = 1007068847268622569L;
    private Icon icon;
    private String name;
    private int id;
    private boolean select;

    private JPanel groupPanel;
    private JPanel buddyPanel;

    private JLabel iconLabel;
    private JLabel nameLabel;
    private JLabel signLabel;

    public MyTreeNode() {
    }

    /**
     * 初始化分组节点
     *
     * @param name 名称
     */
    public MyTreeNode(Icon icon, String name) {
        this.icon = icon;
        this.name = name;
        // 初始化UI
        initCateGUI();
    }

    /**
     * 初始化好友节点
     */
    public MyTreeNode(Icon icon, String nick, int id) {
        this.icon = icon;
        this.name = nick;
        this.id = id;
        // 初始化UI
        initNodeGUI();
    }

    /**
     * 自定义分组UI
     */
    private void initCateGUI() {
        groupPanel = new JPanel();
        groupPanel.setLayout(null);
        groupPanel.setBackground(null);
        groupPanel.setPreferredSize(new Dimension(300, 25));
//		groupPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        iconLabel = new JLabel(icon);
        iconLabel.setBounds(6, 5, 15, 16);
        groupPanel.add(iconLabel);

        nameLabel = new JLabel(name);
        nameLabel.setBounds(23, 0, 132, 28);
        groupPanel.add(nameLabel);
    }

    /**
     * 自定义好友UI
     */
    private void initNodeGUI() {
        buddyPanel = new JPanel();
        buddyPanel.setLayout(null);
        buddyPanel.setBackground(null);
        buddyPanel.setPreferredSize(new Dimension(300, 50));
//		buddyPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

        iconLabel = new JLabel(icon);
        iconLabel.setBounds(8, 4, 42, 42);
        buddyPanel.add(iconLabel);

        nameLabel = new JLabel("用户名："+name);
        nameLabel.setBounds(59, 5, 132, 19);
        buddyPanel.add(nameLabel);

        signLabel = new JLabel("ID："+id);
        signLabel.setBounds(59, 28, 132, 17);
        buddyPanel.add(signLabel);
    }

    /**
     * 将自定义UI返回给渲染器	<br/>
     * 供渲染器调用，返回的必须是一个Component
     *
     * @return
     */
    public Component getGroupView() {
        return groupPanel;
    }

    /**
     * 将自定义UI返回给渲染器	<br/>
     * 供渲染器调用，返回的必须是一个Component
     *
     * @return
     */
    public Component getBuddyView() {
        return buddyPanel;
    }

    public JLabel getIconLabel() {
        return iconLabel;
    }

    public JLabel getNameLabel() {
        return nameLabel;
    }

    public JLabel getSignLabel() {
        return signLabel;
    }

    public Icon getIcon() {
        return icon;
    }

    public void setIcon(Icon icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSelect() {
        return select;
    }

    public void setSelect(boolean select) {
        this.select = select;
    }

}
