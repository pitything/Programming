import org.junit.Test;

import java.util.*;

public class TestCollections {
    @Test
    public void testCollection(){
        /** Collection */
        System.out.println("-----------Collection-----------");
        Collection<Integer> collection1 = new ArrayList<>();
        // 1.添加
        collection1.add(1);
        collection1.add(2);
        collection1.add(3);
        collection1.add(4);
        collection1.add(5);
        collection1.addAll(new ArrayList<Integer>(){{add(6);}});
        System.out.println(collection1); // [1, 2, 3, 4, 5, 6]
        // 2.获取有效元素的个数:6
        System.out.println(collection1.size());
        // 3.是否包含某个元素,用元素的equals方法来判断，拿两个集合的元素挨个比较
        System.out.println(collection1.contains(3));                                      // true
        System.out.println(collection1.containsAll(new ArrayList<Integer>() {{add(3);}}));// true
        // 4.删除：通过元素的equals方法判断是否是要删除的那个元素。只会删除找到的第一个元素
        System.out.println(collection1.remove(3)); // true
        System.out.println(collection1.removeAll(new ArrayList<Integer>() {{add(2);}})); // true
        // 5.把交集的结果存在当前集合中，不影响其他集合
        System.out.println(collection1.retainAll(new ArrayList<Integer>() {{add(4);add(5);add(6);}}));//true
        // 6.集合是否相等
        System.out.println(collection1.equals(new ArrayList<Integer>() {{add(4);add(5);}}));// false
        // 7.转成对象数组
        System.out.println(Arrays.toString(collection1.toArray(new Integer[collection1.size()])));//[4, 5, 6]
        // 8.获取集合对象的哈希值
        System.out.println(collection1.hashCode());
        // 9.返回迭代器对象，用于集合遍历
        Iterator<Integer> iterator1 = collection1.iterator();// 33796
        // 10.清空集合
        collection1.clear();
        // 11.是否是空集合
        System.out.println(collection1.isEmpty());// true
    }

    @Test
    public void testIterator(){
        /** Iterator迭代器 */
        System.out.println("-----------Iterator迭代器-----------");
        Collection<String> collection2 = new ArrayList<String>(8){{
            add("a");
            add("b");
            add("c");
            add("d");
            add("e");
        }};
        // 1.获取迭代器
        Iterator<String> iterator2 = collection2.iterator();
        // 2.判断是否还有下一个元素
        // 在调用it.next()方法之前必须要调用it.hasNext()进行检测。
        // 若不调用，且下一条记录无效，直接调用it.next()会抛出NoSuchElementException异常。
        while (iterator2.hasNext()){
//            iterator2.remove(); // 要先调用next()将指针下移，否则抛出异常：java.lang.IllegalStateException
            // 3.将下一个元素赋给变量a：:①指针下移 ②将下移以后集合位置上的元素返回
            String str = iterator2.next();
            System.out.println(str);
            if(str == "a"){
                // 4.移除当前元素：会将expectedModCount = modCount
                iterator2.remove();
                // 用集合实例的remove，会导致修改数modCount+1，而期望修改数expectedModCount没变
                // 再次调用next()方法，从而抛出异常：java.util.ConcurrentModificationException
//                collection2.remove(str);
            }
            // 如果是倒数第二个元素删除，不会报错，因为hasNext()方法：:cursor != size;返回了false，不会再进入循环调用next()，不会抛出异常
//            if ("d".equals(str)){
//                collection2.remove(str);
//            }
            // 如果是最后一个元素删除，hasNext():cursor != size;返回true，因为size减少了一个，之后调用next()，同样抛出异常
//            if ("e".equals(str)){
//                collection2.remove(str);
//            }
        }
        System.out.println(collection2); // [b, c, d, e]

        /**foreach循环*/
        System.out.println("-----------foreach循环-----------");
        Collection<String> collection3 = new ArrayList<String>(){{
            add("a");
            add("b");
            add("c");
            add("d");
            add("e");
        }};
        for (String entity : collection3) {
            System.out.println(entity);
        }
        int[] intArray = new int[]{1, 2, 3, 4, 5};
        for (int entity : intArray) {
            System.out.println(entity);
        }
    }

    @Test
    public void testList(){
        /**** List */
        List<String> list1 = new ArrayList<>();
        // 1.在index位置插入ele元素
        list1.add(0, "222");
        // 2.从index位置开始将所有元素添加进来
        list1.addAll(1, new ArrayList<String>(){{add("333");add("444");add("555");}});
        System.out.println(list1); //[222, 333, 444, 555]
        // 3.获取指定index位置的元素: 555
        System.out.println(list1.get(3));
        // 4.返回obj在集合中首次出现的位置：2
        System.out.println(list1.indexOf("444"));
        // 5.返回obj在当前集合中末次出现的位置：2
        System.out.println(list1.lastIndexOf("444"));
        // 6.移除指定index位置的元素，并返回此元素：444
        // 注意：如果参数为int，调用该方法；否则调用remove(obj)
        System.out.println(list1.remove(2));
        // 7.设置指定index位置的元素为ele：[222, 333, 22222]
        list1.set(2, "22222");
        // 8.返回从fromIndex到toIndex位置的子集合：[0,2)：[222, 333]
        System.out.println(list1.subList(0, 2));
        System.out.println(list1);

        /****** ArrayList */
        System.out.println("-----------ArrayList-----------");
        ArrayList<String> list2 = new ArrayList<>(); // 默认为容量10
        ArrayList<String> list3 = new ArrayList<>(16);

        /****** LinkedList */
        System.out.println("-----------LinkedList-----------");
        //   [cc, ff, dd, ee, cc, ff]
        LinkedList<String> list4 = new LinkedList<String>(){{add("cc");add("ff");add("dd");add("ee");add("cc");add("ff");}};
        list4.addFirst("aa"); // [aa, cc, ff, dd, ee, cc, ff]
        list4.addLast("zz");  // [aa, cc, ff, dd, ee, cc, ff, zz]
        System.out.println(list4.getFirst()); // aa
        System.out.println(list4.getLast());  // zz
        System.out.println(list4.removeFirst());// aa, [cc, ff, dd, ee, cc, ff, zz]
        System.out.println(list4.removeLast()); // zz, [cc, ff, dd, ee, cc, ff]
        list4.removeFirstOccurrence("cc"); // 同remove("cc")：[ff, dd, ee, cc, ff]
        list4.removeLastOccurrence("ff"); // [ff, dd, ee, cc]


        /****** Vector */
        // [aa, bb, cc, dd]
        Vector<String> vector1 = new Vector<String>(){{add("aa");add("bb");add("cc");add("dd");}};
        vector1.addElement("ff"); // [aa, bb, cc, dd, ff]
        vector1.insertElementAt("kk", 0); // [kk, aa, bb, cc, dd, ff]
        vector1.setElementAt("jj", 1); // [kk, jj, bb, cc, dd, ff]
        vector1.removeElement("dd"); // [kk, jj, bb, cc, ff]
        vector1.removeAllElements();  // 空

    }

    @Test
    public void testSet(){
        /**** Set */
        /****** HashSet */
        System.out.println("------------HashSet-------------");
        HashSet<TestHash> set = new HashSet<>();
        set.add(new TestHash(11, true));
        set.add(new TestHash(21, true));
        set.add(new TestHash(51, true));
        set.add(new TestHash(31, true));
        set.add(new TestHash(31, false));
        System.out.println(set);  // [TestHash{x=31}, TestHash{x=31}, TestHash{x=11}, TestHash{x=21}, TestHash{x=51}]

        /****** LinkedHashSet */
        System.out.println("-------------LinkedHashSet------------");
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        linkedHashSet.add(new TestHash(11, true));
        linkedHashSet.add(new TestHash(21, true));
        linkedHashSet.add(new TestHash(51, true));
        linkedHashSet.add(new TestHash(31, true));
        linkedHashSet.add(new TestHash(31, false));
        System.out.println(linkedHashSet);// [TestHash{x=11}, TestHash{x=21}, TestHash{x=51}, TestHash{x=31}, TestHash{x=31}]

        /****** TreeSet */
        System.out.println("-------------TreeSet------------");
        TreeSet<TestHash> treeSet = new TreeSet<>();
        treeSet.add(new TestHash(5, true));
        treeSet.add(new TestHash(3, true));
        treeSet.add(new TestHash(2, true));
        treeSet.add(new TestHash(31, true));
        treeSet.add(new TestHash(31, false));
        // 自动排序：按照对象的compareTo方法，[TestHash{x=2}, TestHash{x=3}, TestHash{x=5}, TestHash{x=31}]
        // 注意：TreeSet根据重写的compareTo方法判断是否相等，如果TestHash只根据x判断，那么new TestHash(31, false)不能插入set中
        System.out.println(treeSet);
        System.out.println(treeSet.comparator());
        System.out.println(treeSet.first()); // 第一个：TestHash{x=2}
        System.out.println(treeSet.last()); // 最后一个：TestHash{x=31}
        System.out.println(treeSet.lower(new TestHash(6, true)));// 小于6的第一个：TestHash{x=5}
        System.out.println(treeSet.higher(new TestHash(4, true))); // 大于4的第一个：TestHash{x=5}
        // 分隔[x=2, x=5)：[TestHash{x=2}, TestHash{x=3}]
        System.out.println(treeSet.subSet(new TestHash(2, true), new TestHash(5, true)));
        System.out.println(treeSet.headSet(new TestHash(5, true)));// 小于5的所有：[TestHash{x=2}, TestHash{x=3}]
        System.out.println(treeSet.tailSet(new TestHash(5, true)));// 大于等于5的所有：[TestHash{x=5}, TestHash{x=31}]
    }
}
