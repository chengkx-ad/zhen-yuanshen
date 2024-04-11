package Test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ActionTest extends JFrame {
    public static void main(String[] args) {
        JFrame jframe = new JFrame();
        // 设置游戏主界面的宽和高
        jframe.setSize(603,680);
        // 游戏标题
        jframe.setTitle("测试");
        // 置顶
        jframe.setAlwaysOnTop(true);
        // 界面居中
        jframe.setLocationRelativeTo(null);
        // 代码结束方式
        jframe.setDefaultCloseOperation(3);

        // 创建按钮对象
        JButton jtb = new JButton("点击");
        // 设置位置和宽高
        jtb.setSize(500,500);
        // 给按钮添加监听动作，
        // 组件对象，表示要给哪个组件添加事件
        // addActionListener：表示我要给哪个事件添加事件监听（鼠标左键点击和键盘空格）
        // 目前的Action_MyFrame_Test只有该按钮用，所以对于这个类，可以使用匿名内部类来写
        // jtb.addActionListener(new Action_MyFrame_Test());
        // 匿名内部类
        jtb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("nonono----");
            }
        });

        // 把按钮添加到界面中
        jframe.getContentPane().add(jtb);

        // 取消默认的居中放置
        jframe.setLayout(null);

        jframe.setVisible(true);
    }
}
