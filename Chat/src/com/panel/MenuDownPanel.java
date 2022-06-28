package com.panel;

import com.chatroom.ChatRoomFrame;
import com.imageclass.AllImage;
import com.main.MenuFrame;
import com.tree.MyTreeNode;
import com.tree.MyTreeUI;
import com.tree.TreeUtils;
import com.user.MyDao;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class MenuDownPanel extends JPanel{
    /**
     * 鼠标滑过
     */
    private Color HOVER_COLOR = new Color(200, 200, 200, 100);

    /**
     * 鼠标点击
     */
    private Color SELECT_COLOR = new Color(160, 160, 160, 100);

    private JTree jTree;

    private DefaultMutableTreeNode root;
    private DefaultTreeModel model;

    public MenuDownPanel(){
        super();
        initGUI();
    }
    private void initGUI() {
        setSize(MenuFrame.width,MenuFrame.height*7/8);

        setLayout(new FlowLayout(FlowLayout.LEFT));
        setBackground(Color.white);

        root = new DefaultMutableTreeNode();
        model = new DefaultTreeModel(root);

        MyTreeNode cate = new MyTreeNode(new ImageIcon(AllImage.down_arrow), "好友列表");
        MyDao.myFriendInfo.forEach((key,value)->{
            MyTreeNode node = new MyTreeNode(
                    new ImageIcon(AllImage.left_arrow),value.getNickName() , value.getId());
            cate.add(node);
        });
        root.add(cate);

        jTree = new JTree(model);
        jTree.setUI(new MyTreeUI());

        jTree.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseExited(MouseEvent e) {
                TreeUtils.restoreUI(root, model, true);
            }

            @Override
            public void mouseClicked(MouseEvent e) {
                TreePath path = jTree.getSelectionPath();
                if (path == null) {
                    return;
                }
                MyTreeNode node = (MyTreeNode) path.getLastPathComponent();
                if (node == null) {
                    return;
                }
                // 除了好友节点，其他节点都没有点击选中功能
                if (node.getLevel() != 2) {
                    return;
                }
                // 已选择了，就不管，避免重复绘制
                if (node.isSelect()) {
                    return;
                }
                TreeUtils.restoreUI(root, model, false);
                TreeUtils.setBackColor(model, node, SELECT_COLOR);
                new ChatRoomFrame(node.getId());
                node.setSelect(true);
            }
        });

        jTree.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseMoved(MouseEvent e) {
                TreePath path = jTree.getPathForLocation(e.getX(), e.getY());
                if (path == null) {
                    return;
                }
                MyTreeNode node = (MyTreeNode) path.getLastPathComponent();
                if (node == null) {
                    return;
                }
                // 已选择了，就不管，避免重复绘制
                if (node.isSelect()) {
                    return;
                }
                TreeUtils.restoreUI(root, model, true);
                TreeUtils.setBackColor(model, node, HOVER_COLOR);
            }
        });

        add(jTree);
    }

}
