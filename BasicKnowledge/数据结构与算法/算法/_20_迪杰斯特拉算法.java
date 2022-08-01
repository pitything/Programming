package 算法;

import java.util.Arrays;

public class _20_迪杰斯特拉算法 {
    // 表示不可以连接
    static final int N = 65535;
    public static void main(String[] args) {
        // 起始节点：G，下标 6
        int beginIndex = 6;
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        // 邻接矩阵
        int[][] matrix = {{N, 5, 7, N, N, N, 2},
                          {5, N, N, 9, N, N, 3},
                          {7, N, N, N, 8, N, N},
                          {N, 9, N, N, N, 4, N},
                          {N, N, 8, N, N, 5, 4},
                          {N, N, N, 4, 5, N, 6},
                          {2, 3, N, N, 4, 6, N}};
        // 创建 Graph 对象
        Graph graph = new Graph(vertex, matrix);
        // 测试, 看看图的邻接矩阵是否ok
        graph.showGraph();
        // 测试迪杰斯特拉算法
        graph.dijsktra(beginIndex);
        graph.showDijkstra();

        // dijkstra算法精简
        dijkstra2(vertex, matrix, beginIndex);
    }

    /**
     * dijkstra算法精简
     * @param vertex 节点数组
     * @param matrix 邻接矩阵
     * @param index 起点下标
     */
    public static void dijkstra2(char[] vertex, int[][] matrix, int index){
        // 节点数量
        int nodeCount = vertex.length;
        // 是否访问：0：否；1：是
        int[] isVisit = new int[nodeCount];
        // 距离index最短距离
        int[] des = new int[nodeCount];
        // 前驱节点下标：用来输出到每个节点的路径
        int[] pre = new int[nodeCount];

        // 初始化三个基本数组
        Arrays.fill(des, N);
        des[index] = 0;
        isVisit[index] = 1;

        // 更新G节点不经过第三个节点到其他节点的距离
        update(matrix, isVisit, des, pre, 6);
        for(int i = 0; i < nodeCount; i++){ // 单纯为了循环 nodeCount 次，未用到 i
            // 获取距离index节点最短且未被访问的节点
            int nextIndex = getNextUnVisNode(isVisit, des);
            update(matrix, isVisit, des, pre, nextIndex);
        }

        // 输出到每个节点路径
        for(int i = 0; i < nodeCount; i++){
            StringBuilder path = new StringBuilder();
            int preNode = i;
            path.append(vertex[preNode] + " -> ");
            while (pre[preNode] != index && i != index) {
                preNode = pre[preNode];
                path.append(vertex[preNode] + " -> ");
            }
            path.append(vertex[index]);
            System.out.printf("%s \t\t\t %s\n", path.toString(), " 距离：" + des[i]);
        }
    }

    /**
     * 更新 经过index节点，从起点G到其他节点的距离
     * @param matrix
     * @param isVisit
     * @param des
     * @param pre
     * @param index
     */
    public static void update(int[][] matrix, int[] isVisit, int[] des, int[] pre, int index){
        int minLen;
        for(int i = 0; i < isVisit.length; i++){
            // minLen = G到index距离 + index到i的距离
            minLen = des[index] + matrix[index][i];
            // 如果G直接访问i的距离 > minLen；则更新
            if(isVisit[i] == 0 && des[i] > minLen){
                des[i] = minLen;
                pre[i] = index;
            }
        }
    }

    /**
     * 获取距离index节点最短且未被访问的节点
     * @return
     */
    public static int getNextUnVisNode(int[] isVisit, int[] des){
        int minLen = N;
        int nextIndex = 0;
        for(int i = 0; i < des.length; i++){
            if(isVisit[i] == 0 && des[i] < minLen){
                minLen = des[i];
                nextIndex = i;
            }
        }
        isVisit[nextIndex] = 1;
        return nextIndex;
    }
}

class Graph {
    // 顶点数组
    private char[] vertex;
    // 邻接矩阵
    private int[][] matrix;
    // 已经访问的顶点的集合
    private VisitedVertex vv;

    public Graph(char[] vertex, int[][] matrix) {
        this.vertex = vertex;
        this.matrix = matrix;
    }

    public void showDijkstra() {
        vv.show();
    }

    public void showGraph() {
        _18_普里姆算法.showMatrix(matrix);
    }

    /**
     * 迪杰斯特拉算法实现
     *
     * @param index 表示出发顶点对应的下标
     */
    public void dijsktra(int index) {
        vv = new VisitedVertex(vertex.length, index);
        update(index);// 更新index顶点到周围顶点的距离和前驱顶点
        for (int j = 1; j < vertex.length; j++) {
            index = vv.updateArr();// 选择距离最近的节点作为新的访问顶点
            update(index);// 更新index顶点到周围顶点的距离和前驱顶点
        }
    }

    // 更新index下标顶点到周围顶点的距离和周围顶点的前驱顶点,
    private void update(int index) {
        int len;
        // 根据遍历我们的邻接矩阵的 matrix[index]行
        for (int j = 0; j < matrix[index].length; j++) {
            // len 含义是 : 出发点到index顶点的距离 + 从index顶点到j顶点的距离的和
            len = vv.getDis(index) + matrix[index][j];
            // 如果j顶点没有被访问过，并且 len 小于出发顶点到j顶点的距离，就需要更新
            if (!vv.isVisited(j) && len < vv.getDis(j)) {
                vv.updatePre(j, index);// 更新j顶点的前驱为index顶点
                vv.updateDis(j, len);// 更新出发顶点到j顶点的距离
            }
        }
    }
}

class VisitedVertex {
    // 记录各个顶点是否访问过 1 表示访问过, 0 未访问,会动态更新
    public int[] already_arr;

    // 每个下标对应的值为前一个顶点下标, 会动态更新
    public int[] pre_visited;

    // 记录出发顶点到其他所有顶点的距离,比如G为出发顶点，就会记录G到其它顶点的距离，会动态更新，求的最短距离就会存放到dis
    public int[] dis;

    /**
     * @param length:表示顶点的个数
     * @param index:         出发顶点对应的下标, 比如G顶点，下标就是 6
     */
    public VisitedVertex(int length, int index) {
        this.already_arr = new int[length];
        this.pre_visited = new int[length];
        this.dis = new int[length];
        // 初始化 dis数组
        Arrays.fill(dis, 65535);
        this.dis[index] = 0;// 设置出发顶点的访问距离为 0
        this.already_arr[index] = 1;// 设置出发顶点被访问过
    }

    /**
     * 功能: 判断index顶点是否被访问过
     *
     * @param index
     * @return 如果访问过，就返回true, 否则访问false
     */
    public boolean isVisited(int index) {
        return already_arr[index] == 1;
    }

    /**
     * 功能: 更新出发顶点到index顶点的距离
     *
     * @param index
     * @param len
     */
    public void updateDis(int index, int len) {
        dis[index] = len;
    }

    /**
     * 功能: 更新pre这个顶点的前驱顶点为index顶点
     *
     * @param pre
     * @param index
     */
    public void updatePre(int pre, int index) {
        pre_visited[pre] = index;
    }

    /**
     * 功能:返回出发顶点到index顶点的距离
     *
     * @param index
     */
    public int getDis(int index) {
        return dis[index];
    }

    /**
     * 返回距离G最近的节点作为新的访问顶点
     *
     * @return
     */
    public int updateArr() {
        int min = 65535, index = 0;
        for (int i = 0; i < already_arr.length; i++) {
            if (already_arr[i] == 0 && dis[i] < min) {
                min = dis[i];
                index = i;
            }
        }
        // 更新 index 顶点被访问过
        already_arr[index] = 1;
        return index;
    }

    /**
     * 显示最后的结果，将三个数组的情况输出
     */
    public void show() {
        System.out.println("============already_arr==============");
        // 输出already_arr
        for (int i : already_arr) {
            System.out.printf("%10d", i);
        }
        System.out.println();
        System.out.println("===========pre_visited===============");
        // 输出pre_visited
        for (int i : pre_visited) {
            System.out.printf("%10d", i);
        }
        System.out.println();
        System.out.println("============dis==============");
        // 输出dis
        for (int i : dis) {
            System.out.printf("%10d", i);
        }

        System.out.println();
        System.out.println("==========================");
        char[] vertex = {'A', 'B', 'C', 'D', 'E', 'F', 'G'};
        int count = 0;
        for (int i : dis) {
            if (i != 65535) {
                System.out.print(vertex[count] + "(" + i + ") ");
            } else {
                System.out.println("N");
            }
            count++;
        }
        System.out.println();
    }
}