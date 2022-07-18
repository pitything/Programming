package 数据结构;

import java.util.Arrays;

public class _15_递归_八皇后问题 {
    //定义一个max表示共有多少个皇后
    static int max = 8;
    //定义数组array,保存皇后放置位置的结果,比如 arr={ 0 , 4 , 7 , 5 , 2 , 6 , 1 , 3 }
    static int[] array = new int[max];
    // 解法数
    static int count = 0;
    // 判断的冲突次数
    static int judgeCount = 0;

    public static void main(String[] args) {
        //测试一把， 8皇后是否正确
        check(0);
        System.out.printf("一共有%d解法", count);
        System.out.println();
        System.out.printf("一共判断冲突的次数%d次", judgeCount);// 1. 5 w
        System.out.println();
    }

    //编写一个方法，放置第n个皇后
    //特别注意： check 是 每一次递归时，进入到check中都有 for( int i= 0 ;i<max;i++)，因此会有回溯
    private static void check(int n) {
        if (n == max) { //n= 8 , 其实 8 个皇后就既然放好
            count++;
            System.out.println(Arrays.toString(array));
            return;
        }
        //依次放入皇后，并判断是否冲突
        for (int i = 0; i < max; i++) {
            //先把当前这个皇后 n, 放到该行的第 1 列
            array[n] = i;
            //判断当放置第n个皇后到i列时，是否冲突
            if (judge(n)) {// 不冲突
                //接着放 n + 1 个皇后,即开始递归
                check(n + 1);//
            }
            //如果冲突，就继续执行 array[n]=i; 即将第n个皇后，放置在本行得 后移的一个位置
        }
    }

    /**
     * 查看当我们放置第n个皇后, 就去检测该皇后是否和前面已经摆放的皇后冲突：行、列、斜线
     * @param n 表示第n个皇后
     * @return
     */
    private static boolean judge(int n) {
        judgeCount++;
        for (int i = 0; i < n; i++) {
            // 1.判断是否在同一行, 没有必要，n 每次都在递增
            // 2.array[i]==array[n] 表示判断 第n个皇后是否和前面的n- 1 个皇后在同一列
            // 3.Math.abs(n-i)==Math.abs(array[n]-array[i]) 表示判断第n个皇后是否和第i皇后是否在同一斜线
            //n= 1 放置第 2 列 1 n= 1 array[ 1 ]= 1
            //Math.abs( 1 - 0 )== 1 Math.abs(array[n]-array[i])=Math.abs( 1 - 0 )= 1
            if (array[i] == array[n] || Math.abs(n - i) == Math.abs(array[n] - array[i])) {
                return false;
            }
        }
        return true;
    }
}