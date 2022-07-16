import org.junit.Test;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class TestMap {
    @Test
    public void testMap(){
        /** Map */
        Map<String, Object> map1 = new HashMap<>();
        // 1.将指定key-value添加到(或修改)当前map对象中
        map1.put("a", "1");
        map1.put("b", "2");
        Map<String, Object> map2 = new HashMap<>();
        map2.put("c", "3");
        map2.put("d", "4");
        // 2.将 map2 中的所有key-value对存放到当前map中
        map1.putAll(map2);
        // 3.移除指定key的key-value对，并返回value：1
        System.out.println(map1.remove("a"));
        // 4.获取指定key对应的value：2
        System.out.println(map1.get("b"));
        // 5.是否包含指定的key：true
        System.out.println(map1.containsKey("b"));
        // 6.是否包含指定的value:true
        System.out.println(map1.containsValue("2"));
        // 7.返回map中key-value对的个数：3
        System.out.println(map1.size());
        // 8.判断当前map是否为空判断当前map是否为空：false
        System.out.println(map1.isEmpty());
        // 9.判断当前map和参数对象obj是否相等：false
        System.out.println(map1.equals("cc"));
        // 10.返回所有key构成的Set集合：[b, c, d]
        System.out.println(map1.keySet());
        // 11.返回所有value构成的Collection集合：[2, 3, 4]
        System.out.println(map1.values());
        // 12.返回所有key-value对构成的Set集合：[b=2, c=3, d=4]
        Set<Map.Entry<String, Object>> entries = map1.entrySet();
        System.out.println(entries);
        // 13.清空当前map中的所有数据：{}
        map1.clear();
        System.out.println(map1);

        // 14.map遍历
        for (Map.Entry<String, Object> stringObjectEntry : map1.entrySet()) {
            System.out.println(stringObjectEntry);
            System.out.println(stringObjectEntry.getKey());
            System.out.println(stringObjectEntry.getValue());
        }
        // map 直接foreach，会输出map的所有键，不会输出值
        map1.forEach((item, index) -> {
            System.out.println(item);
            System.out.println(map1.get(item));
        });
    }

    @Test
    public void testHashMap(){
        /*** HashMap ***/
        HashMap<String, Object> hashMap = new HashMap<>();
        hashMap.put("aa", 11);

        /*** LinkedHashMap ***/
        LinkedHashMap<String, Object> linkedHashMap = new LinkedHashMap<>();

        /*** TreeMap ***/
        TreeMap<TestHash, Object> treeMap = new TreeMap<>(new Comparator<TestHash>() {
            @Override
            public int compare(TestHash o1, TestHash o2) {
                return 0;
            }
        });

        /** HashTable */
        Hashtable<String, Object> hashtable = new Hashtable<>();

        /** ConcurrentHashMap */
        ConcurrentHashMap<String, Object> concurrentHashMap = new ConcurrentHashMap<>();



    }
}
