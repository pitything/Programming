import java.util.ArrayList;
import java.util.List;

public class _18_二叉树查找 {
    public static List<Integer> res = new ArrayList<>();
    public static HeroNode2 resNode;
    public static boolean found = false;
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
        System.out.println("前序查找");// 1 , 2 , 3 , 5 , 4
        found = false;
        System.out.println(preorderSearch(root, 2));
        System.out.println("中序查找");
        found = false;
        System.out.println(inorderSearch(root, 2));// 2 , 1 , 5 , 3 , 4
        System.out.println("后序查找");
        found = false;
        System.out.println(postorderSearch(root, 2));// 2 , 5 , 4 , 3 , 1
    }

    /**
     * 前序查找-递归
     */
    public static HeroNode2 preorderSearch(HeroNode2 root, int no) {
        if(root == null) return null;
        System.out.println("查找至：" + root);
        if(root.no == no) {
            found = true;
            return root;
        }
        if(!found) resNode = preorderSearch(root.left, no);
        if(!found) resNode = preorderSearch(root.right, no);
        return resNode;
    }

    /**
     * 中序查找-递归
     */
    public static HeroNode2 inorderSearch(HeroNode2 root, int no){
        if(root == null) return null;
        if(!found) resNode = inorderSearch(root.left, no);
        System.out.println("查找至：" + root);
        if(root.no == no) {
            found = true;
            return root;
        }
        if(!found) resNode = inorderSearch(root.right, no);
        return resNode;
    }

    /**
     * 后序查找-递归
     */
    public static HeroNode2 postorderSearch(HeroNode2 root, int no){
        if(root == null) return null;
        if(!found) resNode = postorderSearch(root.left, no);
        if(!found) resNode= postorderSearch(root.right, no);
        System.out.println("查找至：" + root);
        if(root.no == no){
            found = true;
            return root;
        }
        return resNode;
    }
}