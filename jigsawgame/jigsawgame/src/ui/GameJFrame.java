package ui;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Random;


public class GameJFrame extends JFrame implements KeyListener, ActionListener {

    // 游戏界面，与登录相关的代码全部写在这部分
    // 加载数据


    // 加载图片时，用到的二维数组
    int[][] data = new int[4][4];

    // x，y记录空白格子的位置
    int x = 0;
    int y = 0;

    // 数据路径
    String path = "image/animal/animal3/";

    // 正确答案
    int[][] win = {
        {1,2,3,4},
        {5,6,7,8},
        {9,10,11,12},
        {13,14,15,0}
    };

    // 计步
    int step = 0;

    // 创建选项下面的条目对象：重新游戏 重新登录 关闭游戏 公众号
    JMenuItem replayItem = new JMenuItem("重新游戏");
    JMenuItem reLoginItem = new JMenuItem("重新登录");
    JMenuItem closeItem = new JMenuItem("关闭游戏");

    JMenuItem girlItem = new JMenuItem("美女");
    JMenuItem animalItem = new JMenuItem("动物");
    JMenuItem sportItem = new JMenuItem("运动");
    JMenuItem accountItem = new JMenuItem("公众号");

    public GameJFrame(){

        // 初始化界面
        initJFrame();

        // 初始化菜单
        initJMenu();

        // 初始化数据
        initdata();

        // 初始化图片
        initImage();

        // 可视
        this.setVisible(true);
    }

    // 初始化数据（打乱顺序）
    private void initdata() {
        // 把一个一维数组中的数据：0-15打乱顺序
        // 按照4个一组的方式添加到一个二维数组中

        // 定义一个一维数组
        int[] tempArr = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
        // 打乱数组中的顺序
        Random r = new Random();
        for (int i = 0; i < tempArr.length; i++) {
            int index = r.nextInt(tempArr.length);
            int temp = tempArr[i];
            tempArr[i] = tempArr[index];
            tempArr[index] = temp;
        }

        // 遍历打乱顺序的一维数组
        for (int i = 0; i < tempArr.length; i++) {
            System.out.print(tempArr[i] + " ");
        }
        System.out.println();

        // 分开
        for (int i = 0; i < tempArr.length; i++) {
            if(tempArr[i] == 0){
                x = i / 4;
                y = i % 4;
            }
            data[i / 4][i % 4] = tempArr[i];
        }

        // 遍历二维数组
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data.length; j++) {
                System.out.print(data[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void initImage() {
        // 清楚已有图片
        this.getContentPane().removeAll();

        // 胜利图片
        if(vicTory()) {
            JLabel victory = new JLabel(new ImageIcon("image/win.png"));
            victory.setBounds(203,283,197,73);
            this.getContentPane().add(victory);
        }

        JLabel steplabel = new JLabel("步数：" + step);
        steplabel.setBounds(50,30,100,20);
        this.getContentPane().add(steplabel);

        for (int i = 0; i < 4; i++) {
            for (int j = 0;  j < 4; j++) {
                int num = data[i][j];
                // 创建一个图片对象
                // 创建JLabel(管理容器)
                JLabel label = new JLabel(new ImageIcon(path + num + ".jpg"));
                // 指定位置
                label.setBounds(105 * j + 83,105 * i + 134,105,105);
                // 为图片添加边界
                label.setBorder(new BevelBorder(1));
                // 把管理容器放到菜单中
                // 获取Frame中本身就有的Label（容器）
                this.getContentPane().add(label);
            }
        }
        // 添加背景图片
        JLabel background = new JLabel(new ImageIcon("image/background.png"));
        background.setBounds(40,40,508,560);

        this.getContentPane().add(background);

        // 更新图片
        this.getContentPane().repaint();
    }

    private void initJMenu() {
        // 创建整个菜单对象
        JMenuBar JMenuBar = new JMenuBar();
        // 创建菜单上的两个选项的对象：功能  关于我们
        JMenu functionJMenu = new JMenu("功能");
        JMenu changePJMenu = new JMenu("更换图片");
        JMenu aboutJMenu = new JMenu("关于我们");

        // 将每一个选项下的条目添加到选项中
        functionJMenu.add(changePJMenu);
        functionJMenu.add(replayItem);
        functionJMenu.add(reLoginItem);
        functionJMenu.add(closeItem);

        changePJMenu.add(girlItem);
        changePJMenu.add(animalItem);
        changePJMenu.add(sportItem);

        aboutJMenu.add(accountItem);
        // 条目绑定事件
        replayItem.addActionListener(this);
        reLoginItem.addActionListener(this);
        closeItem.addActionListener(this);
        accountItem.addActionListener(this);
        girlItem.addActionListener(this);
        animalItem.addActionListener(this);
        sportItem.addActionListener(this);

        // 将菜单添加到菜单栏中
        JMenuBar.add(functionJMenu);
        JMenuBar.add(aboutJMenu);

        this.setJMenuBar(JMenuBar);
    }

    private void initJFrame() {
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
        // 为整个界面添加键盘监听事件
        this.addKeyListener(this);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if(code == 65){
            // 删除界面所有图片，加载完整图片
            this.getContentPane().removeAll();

            JLabel all = new JLabel(new ImageIcon(path + "all.jpg"));
            all.setBounds(83,143,420,420);
            this.getContentPane().add(all);

            JLabel background = new JLabel(new ImageIcon("image/background.png"));
            background.setBounds(40,40,508,560);
            this.getContentPane().add(background);

            this.getContentPane().repaint();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // 如果游戏胜利，则直接结束，不应该继续移动
        if(vicTory()){
            return;
        }
        // 对上下左右进行判断
        int code = e.getKeyCode();
        if(code == 37){
            if(y == 3){
                return;
            }
            data[x][y] = data[x][y+1];
            data[x][y+1] = 0;
            y++;
            initImage();
            step++;
        }else if(code == 38){
            if(x == 3){
                return;
            }
            data[x][y] = data[x+1][y];
            data[x+1][y] = 0;
            x++;
            initImage();
            step++;
        }else if(code == 39){
            if(y == 0){
                return;
            }
            data[x][y] = data[x][y-1];
            data[x][y-1] = 0;
            y--;
            initImage();
            step++;
        }else if(code == 40){
            if(x == 0){
                return;
            }
            data[x][y] = data[x-1][y];
            data[x-1][y] = 0;
            x--;
            initImage();
            step++;
        }else if(code == 65){
            initImage();
        }else if(code == 87){
            data = new int[][]{
                    {1,2,3,4},
                    {5,6,7,8},
                    {9,10,11,12},
                    {13,14,15,0}
            };
            initImage();
        }
    }

    // 判断游戏是否胜利
    public boolean vicTory(){
        for (int i = 0; i < data.length; i++) {
            for (int j = 0; j < data[i].length; j++) {
                if(data[i][j] != win[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object obj = e.getSource();

        if(obj == replayItem){
            // 重新游戏
            // 计步器清零
            step = 0;
            // 再次打乱顺序
            initdata();
            // 重新加载图片
            initImage();
        }else if(obj == reLoginItem){
            // 关闭当前登录界面
            this.setVisible(false);
            // 打开登录界面
            new LoginJFrame();
        }else if(obj == closeItem){
            // 关闭游戏  关闭虚拟机即可
            System.exit(0);
        }else if(obj == accountItem){
            // 弹窗对象
            JDialog gzhjdialog = new JDialog();
            // jlabel
            JLabel gzhjlabel = new JLabel(new ImageIcon("image/about.png"));
            gzhjlabel.setBounds(0,0,258,258);
            // 将图片添加到弹框中
            gzhjdialog.getContentPane().add(gzhjlabel);
            // 设置弹框大小
            gzhjdialog.setSize(344,344);
            // 弹框居中
            gzhjdialog.setAlwaysOnTop(true);
            //置顶
            gzhjdialog.setLocationRelativeTo(null);
            // 弹框出现时，无法进行其他操作
            gzhjdialog.setModal(true);
            // 使弹框出现
            gzhjdialog.setVisible(true);
        }else if(obj == girlItem){
            Random r = new Random();
            int num = r.nextInt(13) + 1;
            path = "image/girl/girl" + num + "/";
            step = 0;
            initdata();
            initImage();
        }else if(obj == animalItem){
            Random r = new Random();
            int num = r.nextInt(8) + 1;
            path = "image/animal/animal" + num + "/";
            step = 0;
            initdata();
            initImage();
        }else if(obj == sportItem){
            Random r = new Random();
            int num = r.nextInt(10) + 1;
            path = "image/sport/sport" + num + "/";
            step = 0;
            initdata();
            initImage();
        }
    }
}
