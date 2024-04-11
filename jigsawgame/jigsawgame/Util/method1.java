package Util;

import java.util.ArrayList;
import java.util.Random;


// 生成验证码
public class method1 {

    public static String getCode(){
        // 创建集合
        ArrayList<Character> list = new ArrayList<>();
        // 添加字母
        for (int i = 0; i < 26; i++) {
            list.add((char)('a' + i));
            list.add((char)('A' + i));
        }
        // 生成4个随机字母  (验证码的字母部分)
        String result = "";
        Random r = new Random();
        for (int i = 0; i < 4; i++) {
            // 获取随机索引
            int index = r.nextInt(list.size());
            // 获取随机字母
            char c = list.get(index);
            // 结果
            result = result + c;
        }

        // 在上面生成的四个字母后面随机拼接一个数字
        int num = r.nextInt(10);
        result = result + num;
        // 字符串转换为字符数组
        char[] chars = result.toCharArray();
        // 生成一个随机索引和另外的位置进行交换
        int index = r.nextInt(result.length());
        // 交换
        char temp = chars[4];
        chars[4] = chars[index];
        chars[index] = temp;
        // 再转换为字符串
        String code = new String(chars);

        return code;
    }
}
