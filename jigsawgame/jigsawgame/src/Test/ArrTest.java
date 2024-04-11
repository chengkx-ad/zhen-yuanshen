package Test;

import java.util.Random;

public class ArrTest {
    public static void main(String[] args) {
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

        // 创建一个二维数组
        int[][] data = new int[4][4];
        // 分开
        for (int i = 0; i < tempArr.length; i++) {
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
}
