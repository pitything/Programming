import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class _22_哈夫曼树 {
    public static void main(String[] args) {
        int arr[] = {13, 7, 8, 3, 29, 6, 1};
        System.out.println(_17_二叉树遍历.preorderTraversal(createHuffmanTree(arr)));
    }

    /**
     * 创建哈夫曼树的方法
     * @return 创建好后的哈夫曼树的root节点
     * @paramarr 需要创建成哈夫曼树的数组
     */
    public static HeroNode2 createHuffmanTree(int[] arr) {
        List<HeroNode2> nodes = new ArrayList<>();
        for (int value : arr) {
            nodes.add(new HeroNode2(value));
        }

        while (nodes.size() > 1) {
            //排序 从小到大
            Collections.sort(nodes, Comparator.comparingInt(o -> o.no));

            //取出根节点权值最小的两颗二叉树
            //( 1 ) 取出权值最小的节点（二叉树）
            HeroNode2 leftHeroNode2 = nodes.get(0);
            //( 2 ) 取出权值第二小的节点（二叉树）
            HeroNode2 rightHeroNode2 = nodes.get(1);
            //( 3 )构建一颗新的二叉树
            HeroNode2 parent = new HeroNode2(leftHeroNode2.no + rightHeroNode2.no);
            parent.left = leftHeroNode2;
            parent.right = rightHeroNode2;
            //( 4 )从ArrayList删除处理过的二叉树
            nodes.remove(leftHeroNode2);
            nodes.remove(rightHeroNode2);
            //( 5 )将parent加入到nodes
            nodes.add(parent);
        }
        //返回哈夫曼树的root节点
        return nodes.get(0);
    }
}