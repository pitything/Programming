import java.util.*;

public class TestCollections {
    public static void main(String[] args){
        /** Collection */
        /**** List */
        /****** ArrayList */
        ArrayList<String> list = new ArrayList<>(8);
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.add("e");
        Iterator<String> iterator = list.iterator();
        while(iterator.hasNext()){
            String next = iterator.next();
            System.out.println(next);
            if("a".equals(next)){
                iterator.remove();
            }
        }
        System.out.println(list);

        for (String str :  list) {
            if ("d".equals(str)){
                list.remove("d");
            }
        }
        System.out.println(list);


        /* 判断输出 */
        String[] str = new String[5];
        for (String myStr : str) {
            myStr = "atguigu";
            System.out.println(myStr);
        }
        for (int i = 0; i < str.length; i++) {
            System.out.println(str[i]);
        }

        /****** LinkedList */
        List<String> list1 = new LinkedList<>();
        list.add("z");
        list.add("x");
        System.out.println(list1);


        /**** Set */
        /****** HashSet */
        Set set = new HashSet();
        set.add("aaa");
        set.add("bbb");
        set.add("ccc");
        set.add(111);
        Iterator<String> iterator1 = set.iterator();

        /****** TreeSet */
        TreeSet<TestHash> set1 = new TreeSet<>();
        set1.add(new TestHash(1,2, false));
        set1.add(new TestHash(5,2, false));
        set1.add(new TestHash(5,2, false));
        set1.add(new TestHash(3,2, false));
        System.out.println(set1);


        /** Map */
        Map<String, Object> map = new HashMap<>();
        map.put("a", "1");
        map.put("b", "2");
        Set<String> set2 = map.keySet();
        System.out.println(set2);
        Collection<Object> values = map.values();
        System.out.println(values);
        Set<Map.Entry<String, Object>> entries = map.entrySet();
        System.out.println(entries);

        // map 直接foreach，会输出map的所有键，不会输出值
        map.forEach((item, index) -> {
            System.out.println(item);
            System.out.println(map.get(item));
        });




    }
}

class TestHash implements Comparable{

    private int x;
    private int y;
    private boolean sortType;

    TestHash(int x, int y, boolean sortType){
        this.x = x;
        this.y = y;
        this.sortType = sortType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TestHash testHash = (TestHash) o;

        if (x != testHash.x) return false;
        return y == testHash.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override
    public int compareTo(Object o) {
        if(this == null) return -1;
        if(o == null || !(o instanceof TestHash)) return -1;
        return this.sortType ? (this.x > ((TestHash)o).x ? 1 : ((this.x == ((TestHash)o).x) ? 0 : -1))
                : (this.x < ((TestHash)o).x ? 1 : ((this.x == ((TestHash)o).x) ? 0 : -1));
    }

    @Override
    public String toString() {
        return "TestHash{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
