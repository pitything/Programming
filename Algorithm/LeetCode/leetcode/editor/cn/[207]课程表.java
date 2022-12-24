package Algorithm.LeetCode.leetcode.editor.cn;//你这个学期必须选修 numCourses 门课程，记为 0 到 numCourses - 1 。
//
// 在选修某些课程之前需要一些先修课程。 先修课程按数组 prerequisites 给出，其中 prerequisites[i] = [ai, bi] ，表
//示如果要学习课程 ai 则 必须 先学习课程 bi 。 
//
// 
// 例如，先修课程对 [0, 1] 表示：想要学习课程 0 ，你需要先完成课程 1 。 
// 
//
// 请你判断是否可能完成所有课程的学习？如果可以，返回 true ；否则，返回 false 。 
//
// 
//
// 示例 1： 
//
// 
//输入：numCourses = 2, prerequisites = [[1,0]]
//输出：true
//解释：总共有 2 门课程。学习课程 1 之前，你需要完成课程 0 。这是可能的。 
//
// 示例 2： 
//
// 
//输入：numCourses = 2, prerequisites = [[1,0],[0,1]]
//输出：false
//解释：总共有 2 门课程。学习课程 1 之前，你需要先完成​课程 0 ；并且学习课程 0 之前，你还应先完成课程 1 。这是不可能的。 
//
// 
//
// 提示： 
//
// 
// 1 <= numCourses <= 10⁵ 
// 0 <= prerequisites.length <= 5000 
// prerequisites[i].length == 2 
// 0 <= ai, bi < numCourses 
// prerequisites[i] 中的所有课程对 互不相同 
// 
//
// Related Topics 深度优先搜索 广度优先搜索 图 拓扑排序 👍 1458 👎 0


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//leetcode submit region begin(Prohibit modification and deletion)
class Solution207 {

    // 存放每个节点的下一层节点
    List<List<Integer>> edge;
    // 存放每个节点的状态
    int[] isVisit;
    // 是否能够完成
    boolean canFinish = true;

    // 每个节点的入度
    int[] inCount;

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        return canFinish2(numCourses, prerequisites);
        // return canFinish1(numCourses, prerequisites);
    }


    /**
     * 深度优先遍历
     * 思路：edge 存放每个节点的后继节点，isVisit[]存放每个节点的访问状态：0：未访问，1：访问中，2：已完成
     * 深度优先遍历每一个节点，并将isVisit属性设置为1访问中，如果该节点有下一层节点，且下一层节点是未访问，则递归调用下一层的子节点，
     * 最终将每一个没有出度的节点的isVisit属性设置为2已完成，在这个过程中，如果有某个子节点的状态是1访问中，说明有环，则无法完成
     * 复杂度：时间：O() 空间：O()
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish1(int numCourses, int[][] prerequisites) {
        edge = new ArrayList<>();
        isVisit = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            edge.add(new ArrayList<>());
        }
        // 将每个节点的下一层节点放入到edge中
        for (int i = 0; i < prerequisites.length; i++) {
            edge.get(prerequisites[i][0]).add(prerequisites[i][1]);
        }
        // 遍历每个节点，如果该节点没有被访问，则使用深度优先遍历
        for (int i = 0; i < numCourses; i++) {
            if (isVisit[i] == 0) {
                dfs(i);
            }
        }
        return canFinish;
    }

    public void dfs(int i) {
        // 将节点状态设置为访问中
        isVisit[i] = 1;
        // 遍历该节点的每个子节点
        for (int j : edge.get(i)) {
            if (isVisit[j] == 0) {
                dfs(j);
                if (!canFinish) return;
            } else if (isVisit[j] == 1) {
                // 如果存在状态是访问中的子节点，说明有环，则不能完成
                canFinish = false;
                return;
            }
        }
        // 将节点状态设置为已完成
        isVisit[i] = 2;
    }

    /**
     * 广度优先遍历
     * 思路：edge 存放每个节点的后继节点，inCount表示每个节点的入度
     * 将入度为0的节点放入到queue中，然后一个个出队列，说明该节点已经完成，将完成总数count+1，并将子节点的入度-1，
     * 如果子节点的入度为0，则将子节点放入队列，直至queue为空，最后判断完成总数和课程总数是否相等
     * 复杂度：时间：O() 空间：O()
     *
     * @param numCourses
     * @param prerequisites
     * @return
     */
    public boolean canFinish2(int numCourses, int[][] prerequisites) {
        edge = new ArrayList<>();
        inCount = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            edge.add(new ArrayList<>());
        }
        // 将每个节点的下一层节点放入到edge中，并将下一层节点的入度++
        for (int i = 0; i < prerequisites.length; i++) {
            edge.get(prerequisites[i][1]).add(prerequisites[i][0]);
            inCount[prerequisites[i][0]]++;
        }
        // 遍历每个节点，将入度为0的节点放入队列
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inCount[i] == 0) {
                queue.add(i);
            }
        }
        int count = 0;
        // 如果队列不为空
        while (!queue.isEmpty()) {
            // 完成的数量++
            count++;
            int k = queue.poll();
            // 遍历每个入度为0的节点的子节点，将子节点的入度--，如果子节点入度为0，则加入到队列当中去
            for (int i : edge.get(k)) {
                inCount[i]--;
                if (inCount[i] == 0) queue.add(i);
            }
        }
        // 完成的数量是否和总数相等
        return count == numCourses;
    }
}
//leetcode submit region end(Prohibit modification and deletion)
