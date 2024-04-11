package ui;

import USER.user;

import javax.swing.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class RegisterJFrame extends JFrame implements MouseListener {

    // 注册界面
    // 存储用户
    static ArrayList<user> allUsers = new ArrayList<>();

    // 添加按钮
    JButton reset = new JButton();
    JButton register = new JButton();

    // 添加文本框
    JTextField username = new JTextField();
    JPasswordField password = new JPasswordField();
     // JPasswordField confirmPassword = new JPasswordField();
    JTextField password2 = new JTextField();
    public RegisterJFrame(){

        // 初始化界面
        initUI();

        // 加载内容
        initView();

        this.setVisible(true);
    }

    public void initUI(){
        this.setSize(488,430);
        // 游戏标题
        this.setTitle("拼图游戏 V1.0注册");
        // 置顶
        this.setAlwaysOnTop(true);
        // 界面居中
        this.setLocationRelativeTo(null);
        // 代码结束方式
        this.setDefaultCloseOperation(3);
        // 关闭内部默认布局
        this.setLayout(null);
    }

    private void initView() {
        // 添加用户ID‘
        JLabel usernameLabel = new JLabel(new ImageIcon("image/login/用户名.png"));
        usernameLabel.setBounds(116,136,47,17);
        this.getContentPane().add(usernameLabel);

        // 添加用户名输入框
        username.setBounds(195,134,200,30);
        this.getContentPane().add(username);

        // 添加密码
        JLabel passwordLabel = new JLabel(new ImageIcon("image/login/密码.png"));
        passwordLabel.setBounds(130,196,32,16);
        this.getContentPane().add(passwordLabel);

        // 添加密码输入框
        password.setBounds(195,195,200,30);
        this.getContentPane().add(password);

        // 添加确认密码
        JLabel confirmPassword = new JLabel(new ImageIcon("image/register/再次输入密码.png"));
        confirmPassword.setBounds(85,257,96,17);
        this.getContentPane().add(confirmPassword);

        // 确认密码输入框
        password2.setBounds(195,256,200,30);
        this.getContentPane().add(password2);

        // 添加重置按钮
        reset.setBounds(123,310,128,47);
        reset.setIcon(new ImageIcon("image/register/重置按钮.png"));
        // 去除按钮边框
        reset.setBorderPainted(false);
        // 去除按钮背景色
        reset.setContentAreaFilled(false);
        // 添加鼠标事件
        reset.addMouseListener(this);
        this.getContentPane().add(reset);

        // 添加注册按钮
        register.setBounds(256,310,128,47);
        register.setIcon(new ImageIcon("image/login/注册按钮.png"));
        // 去除按钮边框
        register.setBorderPainted(false);
        // 去除按钮背景色
        register.setContentAreaFilled(false);
        // 添加鼠标事件
        register.addMouseListener(this);
        this.getContentPane().add(register);

        // 添加背景图片
        JLabel background = new JLabel(new ImageIcon("image/register/background.png"));
        background.setBounds(0,0,470,390);
        this.getContentPane().add(background);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object obj = e.getSource();
        user user = new user();
        if(obj == reset){
            // 重置输入内容
            username.setText("");
            password.setText("");
            password2.setText("");
        }else if(obj == register){
            // 注册成功
            // 判断两次密码输入是否一致
            if(password.getText().equals(password2.getText())){
                // 判断用户名是否已存在
                for(user u : allUsers){
                    if(u.getUsername().equals(username.getText())){
                        showJdialog("用户名已存在，请重新输入！");
                        return;
                    }
                    // 注册成功
                    user.setUsername(username.getText());
                    user.setPassword(password.getText());
                    allUsers.add(user);
                    showJdialog("注册成功！");
                    this.setVisible(false);
                    new LoginJFrame();
                }
            }
            else{
                showJdialog("两次密码输入不一致，请重新输入！");
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Object obj = e.getSource();
        if(obj == reset ){
            reset.setIcon(new ImageIcon("image/register/重置按下.png"));
        }else if(obj == register){
            register.setIcon(new ImageIcon("image/login/注册按下.png"));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Object obj = e.getSource();
         if(obj == reset){
             reset.setIcon(new ImageIcon("image/register/重置按钮.png"));
         }else if(obj == register) {
             register.setIcon(new ImageIcon("image/login/注册按钮.png"));
         }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void showJdialog(String content){
        // 创建弹框对象
        JDialog dialog = new JDialog();
        // 大小
        dialog.setSize(200,150);
        // 置顶  居中
        dialog.setAlwaysOnTop(true);
        dialog.setLocationRelativeTo(null);
        // 若弹框不关闭，则无法操作
        dialog.setModal(true);

        JLabel warning = new JLabel(content);
        warning.setBounds(0,0,200,150);
        dialog.getContentPane().add(warning);

        // dialog显示
        dialog.setVisible(true);
    }
}
