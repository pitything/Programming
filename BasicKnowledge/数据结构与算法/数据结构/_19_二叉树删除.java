import java.util.ArrayList;
import java.util.List;

public class _19_二叉树删除 {
    public static List<Integer> res = new ArrayList<>();
    public static HeroNode2 resNode;
    public static boolean delete = false;
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
        System.out.println(_17_二叉树遍历.preorderTraversal(root));
        _17_二叉树遍历.res = new ArrayList<>();
        System.out.println("二叉树删除");// 1 , 2 , 3 , 5 , 4
        System.out.println(preorderSearch(root, 3));
        System.out.println(_17_二叉树遍历.preorderTraversal(root));
    }

    /**
     * 二叉树删除-递归
     */
    public static boolean preorderSearch(HeroNode2 root, int no) {
        if(root == null) return false;
        if(root.left != null && root.left.no == no) {
            root.left = null;
            delete = true;
            return delete;
        }
        if(root.right != null && root.right.no == no){
            root.right = null;
            delete = true;
            return delete;
        }
        if(!delete) delete = preorderSearch(root.left, no);
        if(!delete) delete = preorderSearch(root.right, no);
        return delete;
    }
}