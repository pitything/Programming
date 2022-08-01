package 算法;

import java.util.Arrays;

public class _21_弗洛伊德算法 {
    public static void main(String[] args) {
        // 测试看看图是否创建成功
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        final int N = 65535;
        // 创建邻接矩阵
        int[][] matrix = {{0, 5, 7, N, N, N, 2},
                          {5, 0, N, 9, N, N, 3},
                          {7, N, 0, N, 8, N, N},
                          {N, 9, N, 0, N, 4, N},
                          {N, N, 8, N, 0, 5, 4},
                          {N, N, N, 4, 5, 0, 6},
                          {2, 3, N, N, 4, 6, 0}};

        // 创建 Graph2  对象
        Graph2 graph = new Graph2(matrix, vertex);
        // 调用弗洛伊德算法
        graph.floyd();
        graph.show();
    }
}

class Graph2 {
    private char[] vertex;// 存放顶点的数组
    private int[][] dis;// 保存，从各个顶点出发到其它顶点的距离，最后的结果，也是保留在该数组
    private int[][] pre;// 保存到达目标顶点的前驱顶点

    /**
     * @param matrix 邻接矩阵
     * @param vertex 顶点数组
     */
    public Graph2(int[][] matrix, char[] vertex) {
        int length = vertex.length;
        this.vertex = vertex;
        this.dis = matrix;
        this.pre = new int[length][length];
        // 对pre数组初始化, 注意存放的是前驱顶点的下标
        for (int i = 0; i < length; i++) {
            Arrays.fill(pre[i], i);
        }
    }

    // 显示pre数组和dis数组
    public void show() {
        // 为了显示便于阅读，我们优化一下输出
        for (int k = 0; k < dis.length; k++) {
            // 先将pre数组输出的一行
            for (int i = 0; i < dis.length; i++) {
                System.out.print(vertex[pre[k][i]] + " ");
            }
            System.out.println();
            StringBuilder sb;
            // 输出路径
            for (int i = 0; i < dis.length; i++) {
                sb = new StringBuilder(String.valueOf(vertex[k]));
                int cur = k;
                while(pre[cur][i] != cur){
                    cur = pre[cur][i];
                    sb.append("->").append(vertex[cur]);
                }
                // System.out.print(vertex[k] + "->" + vertex[i] + " " + dis[k][i] + " | ");
                System.out.println(sb.append("->" + vertex[i] + " " + dis[k][i]));
            }
            System.out.println();
        }
    }

    // 弗洛伊德算法, 比较容易理解，而且容易实现
    public void floyd() {
        int minLen;// 变量保存距离
        // 对中间顶点遍历， k 就是中间顶点的下标 [A,B,C,D,E,F,G]
        for (int k = 0; k < dis.length; k++) {// 
            // 从i顶点开始出发 [A,B,C,D,E,F,G]
            for (int i = 0; i < dis.length; i++) {
                // 到达j顶点 // [A,B,C,D,E,F,G]
                for (int j = 0; j < dis.length; j++) {
                    minLen = dis[i][k] + dis[k][j];// => 求出从i 顶点出发，经过 k中间顶点，到达 j 顶点距离
                    if (minLen < dis[i][j]) {// 如果len小于 dis[i][j]
                        dis[i][j] = minLen;// 更新 i 到 j 距离
                        pre[i][j] = k;// 更新 i 到 j 前驱顶点
                    }
                }
            }
        }
    }
}