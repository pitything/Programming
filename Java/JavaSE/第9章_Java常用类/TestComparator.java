import java.util.*;

public class TestComparator {
    public static void main(String[] args) {
        /** 1.实现Comparable接口 */
        TestComparable t1 = new TestComparable("412367");
        TestComparable t2 = new TestComparable("6753");
        TestComparable t3 = new TestComparable("456d4");
        TestComparable[] array = new TestComparable[3];
        array[0] = t1;
        array[1] = t2;
        array[2] = t3;
        System.out.println(t1.compareTo(t2)); // 1
        System.out.println(Arrays.toString(array));// [TestComparable{value='412367'}, TestComparable{value='6753'}, TestComparable{value='456d4'}]
        Arrays.sort(array);
        System.out.println(Arrays.toString(array)); // [TestComparable{value='6753'}, TestComparable{value='456d4'}, TestComparable{value='412367'}]

        List<TestComparable> list = new ArrayList<TestComparable>(){{
            add(t1);
            add(t2);
            add(t3);
        }};
        System.out.println(list);// [TestComparable{value='412367'}, TestComparable{value='6753'}, TestComparable{value='456d4'}]
        // Collections.sort() 针对列表进行排序，底层还是Arrays.sort()
        Collections.sort(list);
        System.out.println(list);// [TestComparable{value='6753'}, TestComparable{value='456d4'}, TestComparable{value='412367'}]

        /** 2.Comparator 比较器 */
        Integer[] ddd = new Integer[]{1,2,3,3,1};
        Arrays.sort(ddd, new Comparator<Integer>(){
            @Override
            public int compare(Integer o1, Integer o2) {
                return o1 > o1 ? 1 : (o1 == o2 ? 0 : -1);
            }
        });
        // lambda表达式
        Arrays.sort(ddd, (o1, o2) -> o1 > o1 ? 1 : (o1 == o2 ? 0 : -1));
    }
}

class TestComparable implements Comparable{
    private String value;
    TestComparable(){}
    TestComparable(String value){ this.value = value;}

    @Override
    public int compareTo(Object o) {
        return this.value.length() > ((TestComparable)o).value.length() ? 1 : -1;
    }

    @Override
    public String toString() {
        return "TestComparable{" +
                "value='" + value + '\'' +
                '}';
    }
}