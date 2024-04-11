package Test;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class MyFrame extends JFrame implements ActionListener{

    JButton jtb1 = new JButton("点击");

    JButton jtb2 = new JButton("再次点击");
    public MyFrame(){
        // 设置游戏主界面的宽和高
        this.setSize(603,680);
        // 游戏标题
        this.setTitle("拼图游戏, v1.0");
        // 置顶
        this.setAlwaysOnTop(true);
        // 界面居中
        this.setLocationRelativeTo(null);
        // 代码结束方式
        this.setDefaultCloseOperation(3);
        // 取消默认的居中放置
        this.setLayout(null);


        jtb1.setBounds(0,0,100,50);
        jtb1.addActionListener(this);



        jtb2.setBounds(100 , 0,200,70);
        jtb2.addActionListener(this);

        this.getContentPane().add(jtb1);
        this.getContentPane().add(jtb2);

        this.setVisible(true);


    }

    // 对当前按钮进行判断
    @Override
    public void actionPerformed(ActionEvent e) {

        // 获取当前操作的那个按钮对象
        Object source = e.getSource();

        if(source == jtb1){
            jtb1.setSize(200,200);
        } else if (source == jtb2) {
            Random r = new Random();
            jtb2.setLocation(r.nextInt(500), r.nextInt(500));
        }
    }
}
