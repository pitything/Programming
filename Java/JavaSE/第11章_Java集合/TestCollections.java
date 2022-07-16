import org.junit.Test;

import java.util.*;

public class TestCollections {
    @Test
    public void testCollections(){
        /** Collections工具类 */
        /** 常用方法 **/
        List<Integer> collection1 = new ArrayList<>();
        // 1.添加
        collection1.add(3);
        collection1.add(2);
        collection1.add(5);
        collection1.add(4);
        collection1.add(1);
        System.out.println(collection1); // [3, 2, 5, 4, 1]
        Collections.reverse(collection1); // 反转 List 中元素的顺序：[1, 4, 5, 2, 3]
        Collections.shuffle(collection1); // 对 List 集合元素进行随机排序：[4, 1, 3, 5, 2]
        Collections.sort(collection1); // 自然顺序，按升序排序：[1, 2, 3, 4, 5]
        Collections.sort(collection1, new Comparator<Integer>() { // 自定义Comparator排序：[5, 4, 3, 2, 1]
            @Override
            public int compare(Integer o1, Integer o2) {
                return (o1 >= o2) ? -1 : 1;
            }
        }); //
        Collections.swap(collection1, 1, 2); // 交换下标元素： [5, 3, 4, 2, 1]
        System.out.println(Collections.max(collection1)); // 自然顺序，返回给定集合中的最大元素: 5
        // 根据 Comparator 指定的顺序，返回给定集合中的最大元素: 1
        System.out.println(Collections.max(collection1, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return (o1 >= o2) ? -1 : 1;
            }
        }));
        System.out.println(Collections.min(collection1)); // 自然顺序，返回给定集合中的最小元素: 1
        // 根据 Comparator 指定的顺序，返回给定集合中的最小元素: 5
        System.out.println(Collections.min(collection1, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return (o1 >= o2) ? -1 : 1;
            }
        }));
        System.out.println(Collections.frequency(collection1, 3));// 返回指定集合中指定元素的出现次数：1
        List<Integer> collection2 = new ArrayList<Integer>(){{add(3);}};
        Collections.copy(collection1, collection2); // 将collection2中的内容复制到collection1中：[3, 3, 4, 2, 1]
        Collections.replaceAll(collection1, 2, 1);// 使用新值替换 List 对象的所有旧值：[5, 3, 4, 1, 1]

        /** 同步方法 **/
        Collections.synchronizedList(collection2);
    }
}
