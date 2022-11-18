package Algorithm.LeetCode.leetcode.editor.cn;//请你将一些箱子装在 一辆卡车 上。给你一个二维数组 boxTypes ，其中 boxTypes[i] = [numberOfBoxesi,
//numberOfUnitsPerBoxi] ：
//
//
// numberOfBoxesi 是类型 i 的箱子的数量。
// numberOfUnitsPerBoxi 是类型 i 每个箱子可以装载的单元数量。
//
//
// 整数 truckSize 表示卡车上可以装载 箱子 的 最大数量 。只要箱子数量不超过 truckSize ，你就可以选择任意箱子装到卡车上。
//
// 返回卡车可以装载 单元 的 最大 总数。
//
//
//
// 示例 1：
//
//
//输入：boxTypes = [[1,3],[2,2],[3,1]], truckSize = 4
//输出：8
//解释：箱子的情况如下：
//- 1 个第一类的箱子，里面含 3 个单元。
//- 2 个第二类的箱子，每个里面含 2 个单元。
//- 3 个第三类的箱子，每个里面含 1 个单元。
//可以选择第一类和第二类的所有箱子，以及第三类的一个箱子。
//单元总数 = (1 * 3) + (2 * 2) + (1 * 1) = 8
//
// 示例 2：
//
//
//输入：boxTypes = [[5,10],[2,5],[4,7],[3,9]], truckSize = 10
//输出：91
//
//
//
//
// 提示：
//
//
// 1 <= boxTypes.length <= 1000
// 1 <= numberOfBoxesi, numberOfUnitsPerBoxi <= 1000
// 1 <= truckSize <= 10⁶
//
//
// Related Topics 贪心 数组 排序 👍 95 👎 0


import java.util.Arrays;
import java.util.Comparator;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution1710 {
    /**
     * 思路：先将二维数组按照第二列的值从大到小进行排序，然后根据卡车是否存在剩余空间，依次放入卡车中
     * @param args
     */
    public static void main(String[] args) {
        int[][] box = new int[][]{{35,14},{57,99},{70,48},{50,70},{59,24},{48,72},{27,48},{50,89},{91,9},{87,66},{74,58},{52,29},{10,19},{11,87},{56,71},{83,67},{73,31},{41,58},{26,39},{100,99},{96,51},{33,34},{43,23},{22,41},{89,28},{43,19},{87,56},{30,95},{54,93},{81,98},{84,26},{51,52},{21,16}};
        // int[][] box = new int[][]{{1,3},{2,2},{3,1}};
        System.out.println(new Solution1710().maximumUnits(box, 270));
    }

    public int maximumUnits(int[][] boxTypes, int truckSize) {
        Arrays.sort(boxTypes, new Comparator<int[]>(){
           @Override
           public int compare(int[] o1, int[] o2){
               return o2[1] - o1[1];
            }
        });
        int res = 0;
        for(int i = 0; i < boxTypes.length; i++){
            if(truckSize >= boxTypes[i][0]){
                res += boxTypes[i][0] * boxTypes[i][1];
                truckSize -= boxTypes[i][0];
            }else{
                res += truckSize * boxTypes[i][1];
                break;
            }
        }
        return res;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
