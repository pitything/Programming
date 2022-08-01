package 算法;

import java.util.Arrays;
import java.util.Collections;

public class _19_克鲁斯卡尔算法 {
    private char[] vertexs;// 顶点数组
    private int[][] matrix;// 邻接矩阵
    private int edgeNum;// 边的个数
    // 使用 INF 表示两个顶点不能连通
    private static final int INF = Integer.MAX_VALUE;

    public _19_克鲁斯卡尔算法(char[] vertexs, int[][] matrix) {
        this.vertexs = vertexs;
        this.matrix = matrix;

        // 初始化顶点数和边的个数
        int length = vertexs.length;
        // 统计边的条数
        for (int i = 0; i < length; i++) {
            for (int j = i + 1; j < length; j++) {
                if (this.matrix[i][j] != INF) {
                    edgeNum++;
                }
            }
        }
    }

    public static void main(String[] args) {
        char[] vertexs = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        // 克鲁斯卡尔算法的邻接矩阵
        int matrix[][] = {{0, 12, INF, INF, INF, 16, 14},
                          {12, 0, 10, INF, INF, 7, INF},
                          {INF, 10, 0, 3, 5, 6, INF},
                          {INF, INF, 3, 0, 4, INF, INF},
                          {INF, INF, 5, 4, 0, 2, 8},
                          {16, 7, 6, INF, 2, 0, 9},
                          {14, INF, INF, INF, 8, 9, 0}};

        // 创建_19_克鲁斯卡尔算法  对象实例
        _19_克鲁斯卡尔算法 kruskalCase = new _19_克鲁斯卡尔算法(vertexs, matrix);
        // 输出构建的
        _18_普里姆算法.showMatrix(matrix);
        kruskalCase.kruskal();
    }

    public void kruskal() {
        // 结果数组, 保存最后的最小生成树
        EData[] res = new EData[edgeNum];
        // 图中所有的边的集合
        EData[] allEdges = getEdges();
        System.out.println("图的边的集合 = " + Arrays.toString(allEdges) + " 共 " + allEdges.length);// 12

        int[] ends = new int[edgeNum];// 用于保存"已有最小生成树" 中的每个顶点在最小生成树中的终点

        // 按照边的权值大小进行排序(从小到大)
        Collections.sort(Arrays.asList(allEdges), (o1, o2) -> o1.weight - o2.weight);

        // 遍历edges 数组，将边添加到最小生成树中时，判断是准备加入的边否形成了回路，如果没有，就加入 res,否则不能加入
        for (int i = 0; i < edgeNum; i++) {
            // 第i条边的第 1 个顶点下标
            int p1 = getPosition(allEdges[i].start);// p1 = 4
            // 第i条边的第 2 个顶点下标
            int p2 = getPosition(allEdges[i].end);// p2 = 5
            // 获取 p1 顶点在已有最小生成树中的终点
            int e1 = getEnd(ends, p1);// m= 4
            // 获取 p2 顶点在已有最小生成树中的终点
            int e2 = getEnd(ends, p2);// n= 5
            // 没有构成回路
            if (e1 != e2) {
                ends[e1] = e2;// 设置 e1 在"已有最小生成树"中的终点为 e2 // <E,F>[ 0 , 0 , 0 , 0 , 5 , 0 , 0 , 0 , 0 , 0 , 0 , 0 ]
                res[i] = allEdges[i];// 有一条边加入到res数组
            }
        }
        // 统计并打印 "最小生成树", 输出 res：<E,F><C,D><D,E><B,F><E,G><A,B>
        System.out.println("最小生成树为：");
        for (int i = 0; i < res.length; i++) {
            if(res[i] != null) System.out.println(res[i]);
        }
    }

    /**
     * 通过matrix 邻接矩阵来获取图中所有边
     * EData [] 形式 [['A','B', 12 ],['B','F', 7 ],.]
     *
     * @return
     */
    private EData[] getEdges() {
        int index = 0;
        EData[] allEdges = new EData[edgeNum];
        for (int i = 0; i < vertexs.length; i++) {
            for (int j = i + 1; j < vertexs.length; j++) {
                if (matrix[i][j] != INF) {
                    allEdges[index++] = new EData(vertexs[i], vertexs[j], matrix[i][j]);
                }
            }
        }
        return allEdges;
    }

    /**
     * @return 返回顶点对应的下标，如果找不到，返回- 1
     * @paramch 顶点的值，比如'A','B'
     */
    private int getPosition(char ch) {
        for (int i = 0; i < vertexs.length; i++) {
            if (vertexs[i] == ch) {// 找到
                return i;
            }
        }
        // 找不到,返回- 1
        return -1;
    }
    
    /**
     * 获取下标为i的顶点的终点()
     *
     * @return 返回的就是 下标为i的这个顶点对应的终点的下标, 一会回头还有来理解
     * @param ends ： 数组就是记录了各个顶点对应的终点是哪个,ends 数组是在遍历过程中，逐步形成
     * @param i: 表示传入的顶点对应的下标
     */
    private int getEnd(int[] ends, int i) {// i= 4 [ 0 , 0 , 0 , 0 , 5 , 0 , 0 , 0 , 0 , 0 , 0 , 0 ]
        while (ends[i] != 0) {
            i = ends[i];
        }
        return i;
    }
}

// 创建一个类EData  ，它的对象实例就表示一条边
class EData {
    char start;// 边的一个点
    char end;// 边的另外一个点
    int weight;// 边的权值

    // 构造器
    public EData(char start, char end, int weight) {
        this.start = start;
        this.end = end;
        this.weight = weight;
    }

    // 重写toString , 便于输出边信息
    @Override
    public String toString() {
        return "<" + start + "," + end + "> = " + weight;
    }
}