import java.util.*;

public class _17_贪心算法 {
    public static void main(String[] args) {
        // 存放广播电台
        HashMap<String, HashSet<String>> broadcasts = new HashMap<String, HashSet<String>>();
        // 存放所有的地区
        HashSet<String> allAreas = new HashSet<>();

        // 准备数据
        prepareData(allAreas, broadcasts);

        // 贪心算法
        List<String> res = greedyAlgorithm(allAreas, broadcasts);
        System.out.println("得到的选择结果是" + res);//[K 1 ,K 2 ,K 3 ,K 5 ]
    }

    public static void prepareData(HashSet<String> allAreas, HashMap<String, HashSet<String>> broadcasts){
        //将各个电台放入到broadcasts
        HashSet<String> hashSet1 = new HashSet<>();
        hashSet1.add("北京");
        hashSet1.add("上海");
        hashSet1.add("天津");

        HashSet<String> hashSet2 = new HashSet<>();
        hashSet2.add("广州");
        hashSet2.add("北京");
        hashSet2.add("深圳");

        HashSet<String> hashSet3 = new HashSet<>();
        hashSet3.add("成都");
        hashSet3.add("上海");
        hashSet3.add("杭州");

        HashSet<String> hashSet4 = new HashSet<>();
        hashSet4.add("上海");
        hashSet4.add("天津");

        HashSet<String> hashSet5 = new HashSet<>();
        hashSet5.add("杭州");
        hashSet5.add("大连");
        //加入到map
        broadcasts.put("K1", hashSet1);
        broadcasts.put("K2", hashSet2);
        broadcasts.put("K3", hashSet3);
        broadcasts.put("K4", hashSet4);
        broadcasts.put("K5", hashSet5);

        allAreas.add("北京");
        allAreas.add("上海");
        allAreas.add("广州");
        allAreas.add("深圳");
        allAreas.add("杭州");
        allAreas.add("成都");
        allAreas.add("天津");
        allAreas.add("大连");
    }

    /**
     * 贪心算法
     * @param allAreas
     * @param broadcasts
     */
    public static List<String> greedyAlgorithm(HashSet<String> allAreas, HashMap<String, HashSet<String>> broadcasts){
        // 存放选择的电台集合
        ArrayList<String> res = new ArrayList<>();
        // 拥有未覆盖的地区数量最多的电台
        String maxBroadcast;

        while (allAreas.size() != 0) {// 如果allAreas 不为 0 , 则表示还没有覆盖到所有的地区
            // maxBroadcast为设置第一个电台
            maxBroadcast = broadcasts.keySet().iterator().next();
            for (String broadcast : broadcasts.keySet()) {
                // 当前这个broadcast能够覆盖的地区，注意要new HashSet<>，而不是直接get，否则会改变原来的broadcasts
                HashSet<String> areas = new HashSet<>(broadcasts.get(broadcast));
                // maxBroadcast能够覆盖的地区
                HashSet<String> maxAreas = new HashSet<>(broadcasts.get(maxBroadcast));
                // 求出 areas 和 allAreas 集合的交集, 表示areas中存在未覆盖的数量
                areas.retainAll(allAreas);
                // 表示maxAreas中存在未覆盖的数量
                maxAreas.retainAll(allAreas);
                // 当该电台的未覆盖数量比maxBroadcast的未覆盖还多，重置maxBroadcast，体现出贪心算法特点,每次都最优
                if (areas.size() > 0 && areas.size() > maxAreas.size()) {
                    maxBroadcast = broadcast;
                }
            }
            res.add(maxBroadcast);
            allAreas.removeAll(broadcasts.get(maxBroadcast));
        }
        return res;
    }
}