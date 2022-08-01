package 算法;

public class _15_动态规划算法 {
    public static void main(String[] args) {
        int[] w = {1, 4, 3};                //物品的重量
        int[] v = {1500, 3000, 2000};       //物品的价值 这里val[i]就是前面讲的v[i]
        int capacity = 4;                   //背包的容量
        packageProblem(w, v, capacity);
    }

    public static void packageProblem(int[] w, int [] v, int capacity){
        int count = v.length;               //物品的个数
        int[][] res = new int[count + 1][capacity + 1];//res[i][j] 表示在前i个物品中能够装入容量为j的背包中的最大价值
        int[][] path = new int[count + 1][capacity + 1];// 最优解数组

        //初始化第一行和第一列为 0
        for (int i = 0; i < res.length; i++) {
            res[i][0] = 0;//将第一列设置为 0
        }
        for (int i = 0; i < res[0].length; i++) {
            res[0][i] = 0;//将第一行设置 0
        }

        //根据前面得到公式来动态规划处理
        for (int i = 1; i < count + 1; i++) {//不处理第一行，物品 i是从 1 开始的
            for (int j = 1; j < capacity + 1; j++) {//不处理第一列，容量 j 是从 1 开始的
                if (w[i - 1] > j) {
                    res[i][j] = res[i - 1][j];
                } else {
                    //res[i][j]=Math.max(res[i- 1 ][j], v[i-1] + res[i-1][j-w[i-1]]);
                    //为了记录商品存放到背包的情况，我们不能直接的使用上面的公式，需要使用if-else来体现公式
                    if (res[i - 1][j] < v[i - 1] + res[i - 1][j - w[i - 1]]) {
                        res[i][j] = v[i - 1] + res[i - 1][j - w[i - 1]];
                        //把当前的情况记录到path
                        path[i][j] = 1;
                    } else {
                        res[i][j] = res[i - 1][j];
                    }
                }
            }
        }

        for (int i = 0; i < res.length; i++) {
            for (int j = 0; j < res[i].length; j++) {
                System.out.print(res[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.println("============================");
        //输出最后我们是放入的哪些商品
        //遍历path, 这样输出会把所有的放入情况都得到, 其实我们只需要最后的放入
        for (int i = 0; i < path.length; i++) {
            for (int j = 0; j < path[i].length; j++) {
                System.out.print(path[i][j] + "\t");
            }
            System.out.println();
        }

        System.out.println("============================");
        //最优解
        int i = path.length - 1;//行的最大下标
        int j = path[0].length - 1; //列的最大下标
        while (i > 0 && j > 0) {//从path的最后开始找
            if (path[i][j] == 1) {
                System.out.printf("第%d个商品放入到背包\n", i);
                j -= w[i - 1];//w[i- 1 ]
            }
            i--;
        }
    }
}