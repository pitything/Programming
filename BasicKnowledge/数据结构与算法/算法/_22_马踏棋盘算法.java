package 算法;

import java.awt.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class _22_马踏棋盘算法 {
    private static int X;// 棋盘的列数
    private static int Y;// 棋盘的行数
    // 创建一个数组，标记棋盘的各个位置是否被访问过
    private static boolean visited[];
    // 使用一个属性，标记是否棋盘的所有位置都被访问
    private static boolean finished;

    public static void main(String[] args) {
        X = 3;
        Y = 3;
        int row = 1;// 马儿初始位置的行，从 1 开始编号
        int column = 1;// 马儿初始位置的列，从 1 开始编号
        // 创建棋盘
        int[][] chessboard = new int[X][Y];
        visited = new boolean[X * Y];// 初始值都是false

        long a = System.currentTimeMillis();
        traversalChessboard(chessboard, row - 1, column - 1, 1);
        System.out.println(System.currentTimeMillis() - a);
        // 输出棋盘的最后情况
        _18_普里姆算法.showMatrix(chessboard);
    }

    /**
     * 完成骑士周游问题的算法
     *
     * @param chessboard 棋盘
     * @param row 马儿当前的位置的行 从 0 开始
     * @param column 马儿当前的位置的列 从 0 开始
     * @param step 是第几步 ,初始位置就是第 1 步
     */
    public static void traversalChessboard(int[][] chessboard, int row, int column, int step) {
        chessboard[row][column] = step;
        // row= 4 X= 8 column= 4 = 4 * 8 + 4 = 36
        visited[row * X + column] = true;// 标记该位置已经访问
        // 获取当前位置可以走的下一个位置的集合
        ArrayList<Point> ps = next(new Point(column, row));
        // 使用贪心算法优化：对ps进行排序,排序的规则就是对ps的所有的Point 对象的下一步的位置的数目，进行非递减排序
        ps.sort(Comparator.comparingInt(o -> next(o).size())); // 优化结果：23584ms -> 87ms
        // 遍历 ps
        while (!ps.isEmpty()) {
            Point p = ps.remove(0);// 取出下一个可以走的位置
            // 如果还没有访问过
            if (!visited[p.y * X + p.x]) {
                traversalChessboard(chessboard, p.y, p.x, step + 1);
            }
        }
        // 判断马儿是否完成了任务，使用 step 和应该走的步数比较 ，
        // 如果没有达到数量，则表示没有完成任务，将整个棋盘置 0
        // 说明:step<X*Y 成立的情况有两种
        // 1. 棋盘到目前位置,仍然没有走完
        // 2. 棋盘处于一个回溯过程
        if (step < X * Y && !finished) {
            chessboard[row][column] = 0;
            visited[row * X + column] = false;
        } else {
            finished = true;
        }
    }

    /**
     * 功能：根据当前位置(Point 对象)，计算马儿还能走哪些位置(Point )，并放入到一个集合中(ArrayList), 最多
     * 有 8 个位置
     *
     * @return
     * @param curPoint
     */
    public static ArrayList<Point> next(Point curPoint) {
        // 创建一个ArrayList
        ArrayList<Point> ps = new ArrayList<Point>();
        // 创建一个Point
        Point p1 = new Point();
        // 表示马儿可以走 5 这个位置
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        // 判断马儿可以走 6 这个位置
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        // 判断马儿可以走 7 这个位置
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y - 2) >= 0) {
            ps.add(new Point(p1));
        }
        // 判断马儿可以走 0 这个位置
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y - 1) >= 0) {
            ps.add(new Point(p1));
        }
        // 判断马儿可以走 1 这个位置
        if ((p1.x = curPoint.x + 2) < X && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        // 判断马儿可以走 2 这个位置
        if ((p1.x = curPoint.x + 1) < X && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        // 判断马儿可以走 3 这个位置
        if ((p1.x = curPoint.x - 1) >= 0 && (p1.y = curPoint.y + 2) < Y) {
            ps.add(new Point(p1));
        }
        // 判断马儿可以走 4 这个位置
        if ((p1.x = curPoint.x - 2) >= 0 && (p1.y = curPoint.y + 1) < Y) {
            ps.add(new Point(p1));
        }
        return ps;
    }
}