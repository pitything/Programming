package 算法;

public class _18_普里姆算法 {
    public static void main(String[] args) {
        char[] nodes = new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G'};// 节点
        int verxs = nodes.length;// 节点数量
        // 邻接矩阵的关系使用二维数组表示, 10000 这个大数，表示两个点不联通
        int[][] weight = new int[][]{
                {10000, 5, 7, 10000, 10000, 10000, 2},
                {5, 10000, 10000, 9, 10000, 10000, 3},
                {7, 10000, 10000, 10000, 8, 10000, 10000},
                {10000, 9, 10000, 10000, 10000, 4, 10000},
                {10000, 10000, 8, 10000, 10000, 5, 4},
                {10000, 10000, 10000, 4, 5, 10000, 6},
                {2, 3, 10000, 10000, 4, 6, 10000},};
        // 创建MGraph
        MGraph graph = new MGraph(verxs, nodes, weight);
        // 输出邻接矩阵
        new _18_普里姆算法().showMatrix(graph.weight);
        // 普里姆算法
        prim(graph, 0);// 从节点A开始遍历
    }

    // 显示图的邻接矩阵
    public static void showMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix.length; j++) {
                System.out.printf("%12d", matrix[i][j]);
            }
            System.out.println();//换行
        }
    }

    /**
     * prim算法，得到最小生成树
     * @param graph 图
     * @param node 表示从图的第几个顶点开始生成'A'-> 0 'B'-> 1 ...
     */
    public static void prim(MGraph graph, int node) {
        // visited[] 标记节点(顶点)是否被访问过：默认元素的值都是 0 , 表示没有访问过
        int visited[] = new int[graph.verxs];
        // 把当前这个节点标记为已访问
        visited[node] = 1;
        // node1  和 node2  记录两个顶点的下标
        int node1 = -1;
        int node2 = -1;
        int minWeight = 10000;// 将 minWeight 初始成一个大数，后面在遍历过程中，会被替换
        for (int k = 1; k < graph.verxs; k++) {// 因为有 graph.verxs顶点，有 graph.verxs - 1 边，遍历次数
            // 这个是确定每一次生成的子图 ，和哪个节点的距离最近
            for (int i = 0; i < graph.verxs; i++) {// i节点表示被访问过的节点
                for (int j = 0; j < graph.verxs; j++) {// j节点表示还没有访问过的节点
                    if (visited[i] == 1 && visited[j] == 0 && graph.weight[i][j] < minWeight) {
                        // 替换minWeight(寻找已经访问过的节点和未访问过的节点间的权值最小的边)
                        minWeight = graph.weight[i][j];
                        node1 = i;
                        node2 = j;
                    }
                }
            }
            // 找到一条边是最小
            System.out.println("边<" + graph.nodes[node1] + "," + graph.nodes[node2] + "> 权值:" + minWeight);
            // 将当前这个节点标记为已经访问
            visited[node2] = 1;
            // minWeight 重新设置为最大值 10000
            minWeight = 10000;
        }
    }
}

class MGraph {
    int verxs;// 图的节点个数
    char[] nodes;// 节点
    int[][] weight;// 边，就是我们的邻接矩阵

    public MGraph(int verxs, char[] nodes, int[][] weight) {
        this.verxs = verxs;
        this.nodes = nodes;
        this.weight = weight;
    }
}