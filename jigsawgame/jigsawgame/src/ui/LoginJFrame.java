package ui;

import USER.user;
import Util.method1;
import jdk.dynalink.beans.StaticClass;

import javax.swing.*;
import javax.swing.text.AbstractDocument;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import static ui.RegisterJFrame.allUsers;  // 保存用户数据的静态集合

public class LoginJFrame extends JFrame implements MouseListener {
    // 登录界面，与登录有关的代码全部写在这部分

    // 创建一个集合存储正确的用户名和密码
    //static ArrayList<user> allUsers = new ArrayList<>();
    static {
        allUsers.add(new user("chengkaixuan","769997"));
        allUsers.add(new user("chengkaixuan2","123456"));
    }

    // 添加按钮
    JButton login = new JButton();
    JButton register = new JButton();

    // 添加文本框
    JTextField username = new JTextField();
    JPasswordField password = new JPasswordField();
    JTextField code = new JTextField();

    // 正确验证码
    JTextField rightcode = new JTextField();


    public LoginJFrame(){
        // 初始化界面
        initUI();

        // 初始化内容
        initView();

        this.setVisible(true);
    }

    // 初始化界面
    public void initUI(){
        this.setSize(488,430);
        // 游戏标题
        this.setTitle("拼图游戏 V1.0登录");
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
        usernameLabel.setBounds(116,135,47,17);
        this.getContentPane().add(usernameLabel);

        // 添加用户名输入框
        username.setBounds(195,134,200,30);
        this.getContentPane().add(username);

        // 添加密码
        JLabel passwordLabel = new JLabel(new ImageIcon("image/login/密码.png"));
        passwordLabel.setBounds(130,195,32,16);
        this.getContentPane().add(passwordLabel);

        // 添加密码输入框
        password.setBounds(195,195,200,30);
        this.getContentPane().add(password);

        // 验证码提示
        JLabel codeLabel = new JLabel(new ImageIcon("image/login/验证码.png"));
        codeLabel.setBounds(133,256,50,30);
        this.getContentPane().add(codeLabel);

        // 验证码输入框
        code.setBounds(195,256,100,30);
        this.getContentPane().add(code);

        String codeStr = method1.getCode();
        // 设置验证码内容
        rightcode.setText(codeStr);
        // 绑定鼠标事件
        rightcode.addMouseListener(this);
        // 设置位置
        rightcode.setBounds(300,256,50,30);
        // 添加到界面
        this.getContentPane().add(rightcode);

        // 添加登录按钮
        login.setBounds(123,310,128,47);
        login.setIcon(new ImageIcon("image/login/登录按钮.png"));
        // 去除按钮边框
        login.setBorderPainted(false);
        // 去除按钮背景色
        login.setContentAreaFilled(false);
        // 添加鼠标事件
        login.addMouseListener(this);
        this.getContentPane().add(login);

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
        JLabel background = new JLabel(new ImageIcon("image/login/background.png"));
        background.setBounds(0,0,470,390);
        this.getContentPane().add(background);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Object obj = e.getSource();
        if(obj == login){
            // 获取用户输入的信息（账号 密码 验证码）
            String usernameInput = username.getText();
            String passwordInput = password.getText();

            String codeInput = code.getText();

            // 创建user对象
            user user = new user(usernameInput,passwordInput);

            // 验证信息是否正确
            if(codeInput.length() == 0){
                showJdialog("验证码不能为空！");
            }else if(usernameInput.length() == 0 || passwordInput.length() == 0){
                showJdialog("用户名或者密码为空！！！");
            }else if(!codeInput.equalsIgnoreCase(rightcode.getText())){
                showJdialog("验证码错误");
            }else if(contains(user)){
                this.setVisible(false);
                new GameJFrame();
            }else {
                showJdialog("用户名或者密码输入错误!");
            }
        }else if(obj == register){
            // 跳转到注册界面
            this.setVisible(false);
            new RegisterJFrame();
        }else if(obj == rightcode){
            String codeStr = method1.getCode();
            rightcode.setText(codeStr);
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        Object obj = e.getSource();
        if(obj == login){
            login.setIcon(new ImageIcon("image/login/登录按下.png"));
        }else if(obj == register){
            register.setIcon(new ImageIcon("image/login/注册按下.png"));
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        Object obj = e.getSource();
        if(obj == login){
            login.setIcon(new ImageIcon("image/login/登录按钮.png"));
        }else if(obj == register){
            register.setIcon(new ImageIcon("image/login/注册按钮.png"));
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    // 弹窗信息
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

    // 判断用户是否存在（不存在要去注册）
    public boolean contains(user userInfo){
        for (int i = 0; i < allUsers.size(); i++) {
            user rightUser = allUsers.get(i);
            if(userInfo.getUsername().equals(rightUser.getUsername()) && userInfo.getPassword().equals(rightUser.getPassword())){
                return true;
            }
        }
        return false;
    }
}
