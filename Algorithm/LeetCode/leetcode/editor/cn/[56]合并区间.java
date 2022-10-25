package Algorithm.LeetCode.leetcode.editor.cn;//以数组 intervals 表示若干个区间的集合，其中单个区间为 intervals[i] = [starti, endi] 。请你合并所有重叠的区间，并返
//回 一个不重叠的区间数组，该数组需恰好覆盖输入中的所有区间 。 
//
// 
//
// 示例 1： 
//
// 
//输入：intervals = [[1,3],[2,6],[8,10],[15,18]]
//输出：[[1,6],[8,10],[15,18]]
//解释：区间 [1,3] 和 [2,6] 重叠, 将它们合并为 [1,6].
// 
//
// 示例 2： 
//
// 
//输入：intervals = [[1,4],[4,5]]
//输出：[[1,5]]
//解释：区间 [1,4] 和 [4,5] 可被视为重叠区间。 
//
// 
//
// 提示： 
//
// 
// 1 <= intervals.length <= 10⁴ 
// intervals[i].length == 2 
// 0 <= starti <= endi <= 10⁴ 
// 
//
// Related Topics 数组 排序 👍 1685 👎 0


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution56 {

    public int[][] merge(int[][] intervals) {
        // 根据子数组第一个值排序
        Arrays.sort(intervals, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2){
                return o1[0] - o2[0];
            }
        });
        List<int[]> res = new ArrayList<int[]>(){{add(intervals[0]);}};
        for(int i = 1; i < intervals.length; i++){
            int left = intervals[i][0];
            int right = intervals[i][1];
            // 上一个存入结果的区间
            int[] lastArr = res.get(res.size() - 1);
            // 如果上一个结果的右界限小于当前区间的左界限，说明没有交集，直接加入即可
            if(lastArr[1] < left){
                res.add(intervals[i]);
            }else if(lastArr[1] < right){
                // 如果有交集，且上一个结果的右界限小于当前的右界限，则将当前的右界限覆盖上一个结果
                // 如果上一个结果的右界限大于当前的右界限，则不必操作
                lastArr[1] = right;
                res.remove(res.size() - 1);
                res.add(lastArr);
            }
        }
        return res.toArray(new int[res.size()][]);
    }
}
//leetcode submit region end(Prohibit modification and deletion)
