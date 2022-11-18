package Algorithm.LeetCode.leetcode.editor.cn;//设计一个支持 push ，pop ，top 操作，并能在常数时间内检索到最小元素的栈。
//
// 实现 MinStack 类: 
//
// 
// MinStack() 初始化堆栈对象。 
// void push(int val) 将元素val推入堆栈。 
// void pop() 删除堆栈顶部的元素。 
// int top() 获取堆栈顶部的元素。 
// int getMin() 获取堆栈中的最小元素。 
// 
//
// 
//
// 示例 1: 
//
// 
//输入：
//["MinStack","push","push","push","getMin","pop","top","getMin"]
//[[],[-2],[0],[-3],[],[],[],[]]
//
//输出：
//[null,null,null,null,-3,null,0,-2]
//
//解释：
//MinStack minStack = new MinStack();
//minStack.push(-2);
//minStack.push(0);
//minStack.push(-3);
//minStack.getMin();   --> 返回 -3.
//minStack.pop();
//minStack.top();      --> 返回 0.
//minStack.getMin();   --> 返回 -2.
// 
//
// 
//
// 提示： 
//
// 
// -2³¹ <= val <= 2³¹ - 1 
// pop、top 和 getMin 操作总是在 非空栈 上调用 
// push, pop, top, and getMin最多被调用 3 * 10⁴ 次 
// 
//
// Related Topics 栈 设计 👍 1451 👎 0


//leetcode submit region begin(Prohibit modification and deletion)

import java.util.*;


class MinStack {

    /**
     * 借助Stack类实现
     * 思路：创建两个栈，一个栈正常存放push进来的数据，另一个栈存放最小的数据，每一次pop操作，两个栈都分别将栈顶元素出栈
     * 复杂度：时间：O() 空间：O()
     * @param args
     */
        public static void main(String[] args) {
            MinStack minStack = new MinStack();
            minStack.push(2147483646);
            minStack.push(2147483646);
            minStack.push(2147483647);
            minStack.top();
            minStack.pop();
            minStack.getMin();
            minStack.pop();
            minStack.getMin();
            minStack.pop();
            minStack.push(2147483647);
            minStack.top();
            minStack.getMin();
            minStack.push(-2147483648);
            minStack.top();
            minStack.getMin();
            minStack.pop();
            minStack.getMin();
        }
        Stack<Integer> stack = new Stack<>();
        Stack<Integer> minStack = new Stack<>();

        public MinStack() {
            stack.push(Integer.MAX_VALUE);
            minStack.push(Integer.MAX_VALUE);
        }

        public void push(int val) {
            stack.push(val);
            minStack.push(Math.min(val, minStack.peek()));
        }

        public void pop() {
            stack.pop();
            minStack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return minStack.peek();
        }
    }

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 * <p>
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */

/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */
//leetcode submit region end(Prohibit modification and deletion)
