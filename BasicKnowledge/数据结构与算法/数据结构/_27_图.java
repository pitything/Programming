import java.util.*;

public class _27_图 {
    private ArrayList<String> vertexList;//存储顶点集合
    private int[][] edges;               //存储图对应的邻结矩阵
    private int numOfEdges;              //表示边的数目
    private boolean[] isVisited;         //定义给数组boolean[],记录某个节点是否被访问

    public _27_图() {}
    public _27_图(int n) {
        vertexList = new ArrayList<>(n);
        edges = new int[n][n];
        numOfEdges = 0;
    }

    public static void main(String[] args) {
        String vertexs[] = {" 1 ", " 2 ", " 3 ", " 4 ", " 5 ", " 6 ", " 7 ", " 8 "};

        // 创建图对象
        _27_图 graph = new _27_图(vertexs.length);
        // 添加顶点
        for (String vertex : vertexs) {
            graph.insertVertex(vertex);
        }
        // 添加边
        graph.insertEdge(0, 1, 1);
        graph.insertEdge(0, 2, 1);
        graph.insertEdge(1, 3, 1);
        graph.insertEdge(1, 4, 1);
        graph.insertEdge(3, 7, 1);
        graph.insertEdge(4, 7, 1);
        graph.insertEdge(2, 5, 1);
        graph.insertEdge(2, 6, 1);
        graph.insertEdge(5, 6, 1);

        // 显示邻结矩阵
        graph.showGraph();

        System.out.print("深度优先遍历：");
        graph.search("dfs");// 1 -> 2 -> 4 -> 8 -> 5 -> 3 -> 6 -> 7
        System.out.println();

        System.out.print("广度优先遍历：");
        graph.search("bfs");// 1 -> 2 -> 3 -> 4 -> 5 -> 6 -> 7 -> 8
        System.out.println();
    }

    //显示图对应的矩阵
    public void showGraph() {
        for (int[] link : edges) {
            System.err.println(Arrays.toString(link));
        }
    }

    //得到边的数目
    public int getNumOfEdges() {
        return numOfEdges;
    }

    //返回v1 和v2的权值
    public int getWeight(int v1, int v2) {
        return edges[v1][v2];
    }

    //插入节点
    public void insertVertex(String vertex) {
        vertexList.add(vertex);
    }

    /**
     * 添加边
     * @param v1 表示点的下标即使第几个顶点 "A"-"B""A"-> 0 "B"-> 1
     * @param v2 第二个顶点对应的下标
     * @param weight 表示
     */
    public void insertEdge(int v1, int v2, int weight) {
        edges[v1][v2] = weight;
        edges[v2][v1] = weight;
        numOfEdges++;
    }

    /**
     * 得到第一个邻接节点下标
     * @param index
     * @return 如果存在就返回对应的下标，否则返回- 1
     */
    public int getFirstNeighbor(int index) {
        for (int j = 0; j < vertexList.size(); j++) {
            if (edges[index][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    /**
     * 获取下一个邻接节点下标
     * @param v1
     * @param v2
     * @return
     */
    public int getNextNeighbor(int v1, int v2) {
        for (int j = v2 + 1; j < vertexList.size(); j++) {
            if (edges[v1][j] > 0) {
                return j;
            }
        }
        return -1;
    }

    public void search(String searchType) {
        int size = vertexList.size();
        isVisited = new boolean[size];
        for (int i = 0; i < size; i++) {
            // 如果没有被访问过
            if (!isVisited[i]) {
                if("dfs".equals(searchType)) {
                    dfs(isVisited, i);
                }else if("bfs".equals(searchType)){
                    bfs(isVisited, i);
                }
            }
        }
    }

    /**
     * 深度优先遍历
     * @param isVisited
     * @param i
     */
    private void dfs(boolean[] isVisited, int i) {
        //首先我们访问该节点,输出
        System.out.print(vertexList.get(i) + "->");
        //将节点设置为已经访问
        isVisited[i] = true;
        //查找节点i的第一个邻接节点w
        int w = getFirstNeighbor(i);
        while (w != -1) {//说明有
            if (!isVisited[w]) {
                dfs(isVisited, w);
            }
            //如果w节点已经被访问过，获取w节点后的下一个i的邻接节点
            w = getNextNeighbor(i, w);
        }
    }

    /**
     * 广度优先遍历
     * @param isVisited
     * @param i
     */
    private void bfs(boolean[] isVisited, int i) {
        int u;// 表示队列的头节点对应下标
        int w;// 邻接节点w
        // 队列，记录节点访问的顺序
        Queue queue = new LinkedList();
        //访问节点，输出节点信息
        System.out.print(vertexList.get(i) + "=>");
        //标记为已访问
        isVisited[i] = true;
        //将节点加入队列
        queue.add(i);
        while (!queue.isEmpty()) {
            //取出队列的头节点下标
            u = (Integer) queue.poll();
            //得到第一个邻接节点的下标 w
            w = getFirstNeighbor(u);
            while (w != -1) {//找到
                // 是否访问过
                if (!isVisited[w]) {
                    System.out.print(vertexList.get(w) + "=>");
                    //标记已经访问
                    isVisited[w] = true;
                    //入队
                    queue.add(w);
                }
                //以u为前驱点，找w后面的下一个邻节点
                w = getNextNeighbor(u, w);//体现出我们的广度优先
            }
        }
    }
}