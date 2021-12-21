/******************************************************************
 * 文件名称: middle.No2_addTwoNumbers
 * 系统名称:
 * 模块名称:
 * 软件版权:
 * 功能说明:
 * 系统版本:
 * 开发人员: haoliang.jiang
 * 开发时间: 2021/12/15 2:10 上午
 * 修改记录:
 * 程序版本             修改日期                修改人员                        修改单号                               修改说明
 *******************************************************************/
package middle;

import java.util.*;

/**
 * Title: 22. 括号生成
 *
 * Description:
 * 数字 n 代表生成括号的对数，请你设计一个函数，用于能够生成所有可能的并且 有效的 括号组合。
 *
 * Example:
 * 输入：n = 3
 * 输出：["((()))","(()())","(())()","()(())","()()()"]
 *
 * 输入：n = 1
 * 输出：["()"]
 * 
 * 提示：
 * 1 <= n <= 8
 *
 * @Author haoliang.jiang
 * @Date 2021/12/21 16:57 下午
 */
public class No22_generateParenthesis {

    public static void main(String[] args){
        System.out.println(generateParenthesis1(2));
        System.out.println(generateParenthesis2(2));
    }

    /**
     * @description 括号生成
     * @param n 括号数量
     * @return List<String>
     * @Author haoliang.jiang
     * @Date 2021/12/21 16:57 下午
     * @Update
     */
    private static List<String> generateParenthesis1(int n) {
        List<String> result = new ArrayList<>();
        if(n == 0) return result;
        dfs("", n, n, result);
        return result;
    }

    /**
     * 深度优先遍历
     * @param str    当前递归得到的结果
     * @param left   左括号还有几个可以使用
     * @param right  右括号还有几个可以使用
     * @param result 结果集
     */
    private static void dfs(String str, int left, int right, List<String> result){
        if(left == 0 && right == 0) {
            result.add(str);
            return;
        }
        if(left > right) return;

        if(left > 0){
            dfs(str + "(", left - 1, right, result);
        }
        if(right > 0){
            dfs(str + ")", left, right - 1, result);
        }
    }


    /**
     * @description 广度优先遍历
     * @param n 括号数量
     * @return List<String>
     * @Author haoliang.jiang
     * @Date 2021/12/21 16:57 下午
     * @Update
     */
    private static List<String> generateParenthesis2(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node("", n, n));

        while (!queue.isEmpty()) {

            Node curNode = queue.poll();
            if (curNode.left == 0 && curNode.right == 0) {
                res.add(curNode.res);
            }
            if (curNode.left > 0) {
                queue.offer(new Node(curNode.res + "(", curNode.left - 1, curNode.right));
            }
            if (curNode.right > 0 && curNode.left < curNode.right) {
                queue.offer(new Node(curNode.res + ")", curNode.left, curNode.right - 1));
            }
        }
        return res;
    }

    static class Node {
        /**
         * 当前得到的字符串
         */
        private String res;
        /**
         * 剩余左括号数量
         */
        private int left;
        /**
         * 剩余右括号数量
         */
        private int right;

        public Node(String str, int left, int right) {
            this.res = str;
            this.left = left;
            this.right = right;
        }
    }
}
