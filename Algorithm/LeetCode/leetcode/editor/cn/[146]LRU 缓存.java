package Algorithm.LeetCode.leetcode.editor.cn;//
// 请你设计并实现一个满足 
// LRU (最近最少使用) 缓存 约束的数据结构。
// 
//
// 
// 实现 
// LRUCache 类：
// 
//
// 
// 
// 
// LRUCache(int capacity) 以 正整数 作为容量 capacity 初始化 LRU 缓存 
// int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。 
// void put(int key, int value) 如果关键字 key 已经存在，则变更其数据值 value ；如果不存在，则向缓存中插入该组 
//key-value 。如果插入操作导致关键字数量超过 capacity ，则应该 逐出 最久未使用的关键字。 
// 
// 
// 
//
// 函数 get 和 put 必须以 O(1) 的平均时间复杂度运行。 
//
// 
//
// 示例： 
//
// 
//输入
//["LRUCache", "put", "put", "get", "put", "get", "put", "get", "get", "get"]
//[[2], [1, 1], [2, 2], [1], [3, 3], [2], [4, 4], [1], [3], [4]]
//输出
//[null, null, null, 1, null, -1, null, -1, 3, 4]
//
//解释
//LRUCache lRUCache = new LRUCache(2);
//lRUCache.put(1, 1); // 缓存是 {1=1}
//lRUCache.put(2, 2); // 缓存是 {1=1, 2=2}
//lRUCache.get(1);    // 返回 1
//lRUCache.put(3, 3); // 该操作会使得关键字 2 作废，缓存是 {1=1, 3=3}
//lRUCache.get(2);    // 返回 -1 (未找到)
//lRUCache.put(4, 4); // 该操作会使得关键字 1 作废，缓存是 {4=4, 3=3}
//lRUCache.get(1);    // 返回 -1 (未找到)
//lRUCache.get(3);    // 返回 3
//lRUCache.get(4);    // 返回 4
// 
//
// 
//
// 提示： 
//
// 
// 1 <= capacity <= 3000 
// 0 <= key <= 10000 
// 0 <= value <= 10⁵ 
// 最多调用 2 * 10⁵ 次 get 和 put 
// 
//
// Related Topics 设计 哈希表 链表 双向链表 👍 2464 👎 0


import java.util.HashMap;
import java.util.Map;

//leetcode submit region begin(Prohibit modification and deletion)

class LRUCache146{
    public static void main(String[] args) {
        LRUCache146 lruCache = new LRUCache146(2);
        lruCache.put(2, 2);
        lruCache.put(2, 3);
        lruCache.put(2, 4);
        System.out.println(lruCache.get(2));

    }

     class Node{
         int key;
         int value;
         Node pre;
         Node next;
         Node(){}
         Node(int key, int value){
             this.key = key;
             this.value = value;
         }
     }

     Map<Integer, Node> map = new HashMap<>();
     int size;
     int capacity;
     Node head;
     Node tail;

    public LRUCache146(int capacity){
        this.size = 0;
        this.capacity = capacity;
        head = new Node();
        tail = new Node();
        head.next = tail;
        tail.pre = head;
    }

    public int get(int key){
        Node node = map.get(key);
        if(node == null) return -1;
        removeNode(node);
        moveNode2Head(node);
        return node.value;
    }

    public void put(int key, int value){
        Node node = map.get(key);
        if(node != null){
            node.value = value;
            // map.put(key, node);
            removeNode(node);
            moveNode2Head(node);
        }else{
            node = new Node(key, value);
            map.put(key, node);
            moveNode2Head(node);
            size++;
            if(size > capacity){
                map.remove(tail.pre.key);
                removeNode(tail.pre);
                size--;
            }
        }
    }

    public void removeNode(Node node){
        node.pre.next = node.next;
        node.next.pre = node.pre;
    }

    public void moveNode2Head(Node node){
        head.next.pre = node;
        node.next = head.next;
        head.next = node;
        node.pre = head;
    }
}


/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
//leetcode submit region end(Prohibit modification and deletion)
