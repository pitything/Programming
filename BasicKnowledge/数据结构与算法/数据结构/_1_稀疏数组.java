package 数据结构;

import org.junit.Test;

public class _1_稀疏数组 {
    // 创建一个原始的二维数组 11 * 11
    // 0 没有棋子；1 黑子；2 白子
    static int chessArr[][] = new int[11][11];
    static {
        chessArr[1][2] = 1;
        chessArr[2][3] = 2;
        chessArr[4][5] = 2;
    }
    
    static int[][] sparseArray = new int[4][3];
    static {
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = 3;
        sparseArray[1][0] = 1;
        sparseArray[1][1] = 2;
        sparseArray[1][2] = 1;
        sparseArray[2][0] = 2;
        sparseArray[2][1] = 3;
        sparseArray[2][2] = 2;
        sparseArray[3][0] = 4;
        sparseArray[3][1] = 5;
        sparseArray[3][2] = 2;
    }

    /** 二维数组 -> 稀疏数组 */
    @Test
    public void twoDimArray2SparseArray(){
        // 输出原始的二维数组
        System.out.println("原始的二维数组~~");
        for (int[] row : chessArr) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }

        /** 将二维数组 转 稀疏数组 */
        // 1. 先遍历二维数组 得到非 0 数据的个数
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr[i][j] != 0) {
                    sum++;
                }
            }
        }
        // 2. 创建对应的稀疏数组
        int sparseArray[][] = new int[sum + 1][3];
        // 给稀疏数组赋值
        sparseArray[0][0] = 11;
        sparseArray[0][1] = 11;
        sparseArray[0][2] = sum;
        // 遍历二维数组，将非 0 的值存放到 sparseArr中
        int count = 0;//count 用于记录是第几个非 0 数据
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr[i][j] != 0) {
                    count++;
                    sparseArray[count][0] = i;
                    sparseArray[count][1] = j;
                    sparseArray[count][2] = chessArr[i][j];
                }
            }
        }

        // 输出稀疏数组的形式
        System.out.println("得到稀疏数组为~~~~");
        for (int i = 0; i < sparseArray.length; i++) {
            System.out.printf("%d\t%d\t%d\t\n", sparseArray[i][0], sparseArray[i][1], sparseArray[i][2]);
        }
    }

    /** 稀疏数组 -> 二维数组 */
    @Test
    public void sparseArray2DimArray(){
        /** 将稀疏数组 --》 恢复成 原始的二维数组 */
        // 1. 先读取稀疏数组的第一行，根据第一行的数据，创建原始的二维数组
        int chessArr2[][] = new int[sparseArray[0][0]][sparseArray[0][1]];
        // 2. 在读取稀疏数组后几行的数据(从第二行开始)，并赋给 原始的二维数组 即可
        for (int i = 1; i < sparseArray.length; i++) {
            chessArr2[sparseArray[i][0]][sparseArray[i][1]] = sparseArray[i][2];
        }

        // 输出恢复后的二维数组
        System.out.println("恢复后的二维数组~");
        for (int[] row : chessArr2) {
            for (int data : row) {
                System.out.printf("%d\t", data);
            }
            System.out.println();
        }
    }
}
