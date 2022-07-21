import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class _17_二叉树遍历 {
    public static List<Integer> res = new ArrayList<>();
    public static void main(String[] args) {
        //创建需要的节点
        HeroNode2 root = new HeroNode2(1, "宋江");
        HeroNode2 node2 = new HeroNode2(2, "吴用");
        HeroNode2 node3 = new HeroNode2(3, "卢俊义");
        HeroNode2 node4 = new HeroNode2(4, "林冲");
        HeroNode2 node5 = new HeroNode2(5, "关胜");
        //说明，我们先手动创建该二叉树，后面我们学习递归的方式创建二叉树
        root.left = node2;
        root.right = node3;
        node3.right = node4;
        node3.left = node5;
        //     1
        //   2   3
        //      5 4
        System.out.println("前序遍历");// 1 , 2 , 3 , 5 , 4
//        System.out.println(preorderTraversal(root));
        System.out.println(preorderTraversal2(root));
        System.out.println("中序遍历");
        res = new ArrayList<>();
//        System.out.println(inorderTraversal(root));// 2 , 1 , 5 , 3 , 4
        System.out.println(inorderTraversal2(root));// 2 , 1 , 5 , 3 , 4
        System.out.println("后序遍历");
        res = new ArrayList<>();
//        System.out.println(postorderTraversal(root));// 2 , 5 , 4 , 3 , 1
        System.out.println(postorderTraversal2(root));// 2 , 5 , 4 , 3 , 1
    }

    /**
     * 前序遍历-递归
     */
    public static List<Integer> preorderTraversal(HeroNode2 root) {
        if(root == null) return res;
        res.add(root.no);
        preorderTraversal(root.left);
        preorderTraversal(root.right);
        return res;
    }

    /**
     * 中序遍历-递归
     */
    public static List<Integer> inorderTraversal(HeroNode2 root){
        if(root == null) return res;
        inorderTraversal(root.left);
        res.add(root.no);
        inorderTraversal(root.right);
        return res;
    }

    /**
     * 后序遍历-递归
     */
    public static List<Integer> postorderTraversal(HeroNode2 root){
        if(root == null) return res;
        postorderTraversal(root.left);
        postorderTraversal(root.right);
        res.add(root.no);
        return res;
    }

    /**
     * 前序遍历-循环
     */
    public static List<Integer> preorderTraversal2(HeroNode2 root) {
        Stack<HeroNode2> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        while(root != null || !stack.empty()){
            while(root != null){
                res.add(root.no);
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            root = root.right;
        }
        return res;
    }
    /**
     * 中序遍历-循环
     */
    public static List<Integer> inorderTraversal2(HeroNode2 root){
        Stack<HeroNode2> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        while(root != null || !stack.empty()){
            while (root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.pop();
            res.add(root.no);
            root = root.right;
        }
        return res;
    }
    /**
     * 后序遍历-循环
     */
    public static List<Integer> postorderTraversal2(HeroNode2 root){
        Stack<HeroNode2> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        HeroNode2 pre = null;//用来记录上一节点
        while(root != null || !stack.empty()){
            while(root != null){
                stack.push(root);
                root = root.left;
            }
            root = stack.peek();
            // 如果没有右节点，或者已经遍历了右节点，就将根节点放入res中
            if(root.right == null || root.right == pre){
                res.add(root.no);
                pre = stack.pop();;
                root = null;
            }else{
                // 如果有右节点，且未被访问过，则访问右节点
                root = root.right;
            }
        }
        return res;
    }
}

class HeroNode2 {
    public int no;
    public String name;
    public HeroNode2 left;//默认null
    public HeroNode2 right;//默认null

    public HeroNode2(int no) {
        this.no = no;
    }

    public HeroNode2(int no, String name) {
        this.no = no;
        this.name = name;
    }

    @Override
    public String toString() {
        return "HeroNode2 [no=" + no + ",name=" + name + "]";
    }
}