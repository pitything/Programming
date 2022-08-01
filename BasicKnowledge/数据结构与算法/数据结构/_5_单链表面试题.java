package 数据结构;

import java.util.Stack;

public class _5_单链表面试题 {
    public static void main(String[] args) {
        HeroNode1 hero1 = new HeroNode1(1, "宋江", "及时雨");
        HeroNode1 hero2 = new HeroNode1(2, "卢俊义", "玉麒麟");
        HeroNode1 hero3 = new HeroNode1(3, "吴用", "智多星");
        HeroNode1 hero4 = new HeroNode1(4, "林冲", "豹子头");

        SingleLinkedList linkedList = new SingleLinkedList();
        linkedList.addByNo(hero1);
        linkedList.addByNo(hero2);
        linkedList.addByNo(hero3);
        linkedList.addByNo(hero4);

        /** 返回的就是有效节点的个数(如果是带头结点的链表，需求不统计头节点) */
        System.out.println("返回的就是有效节点的个数(如果是带头结点的链表，需求不统计头节点)~~~");
        System.out.println(getLength(linkedList.head));

        /** 查找单链表中的倒数第k个结点 */
        System.out.println("查找单链表中的倒数第k个结点~~~");
        System.out.println(findLastIndexNode(linkedList.head, 1));

        /** 将单链表反转 */
        System.out.println("将单链表反转~~~");
        reversetList1(linkedList.head);
        reversetList2(linkedList.head);

        /** 单链表的逆序打印 */
        System.out.println("单链表的逆序打印1~~~");
        reversePrint1(linkedList.head);
        System.out.println("单链表的逆序打印2~~~");
        reversePrint2(linkedList.head);
    }

    /**
     * @return 返回的就是有效节点的个数(如果是带头结点的链表 ， 需求不统计头节点)
     * @paramhead 链表的头节点
     */
    public static int getLength(HeroNode1 head) {
        if (head.next == null) {//空链表
            return 0;
        }
        int length = 0;
        //定义一个辅助的变量, 这里我们没有统计头节点
        HeroNode1 cur = head.next;
        while (cur != null) {
            length++;
            cur = cur.next;//遍历
        }
        return length;
    }

    /**
     * 查找单链表中的倒数第k个结点 【新浪面试题】
     * 1. 编写一个方法，接收head节点，同时接收一个index
     * 2 .index 表示是倒数第index个节点
     * 3. 先把链表从头到尾遍历，得到链表的总的长度 getLength
     * 4. 得到size 后，我们从链表的第一个开始遍历 (size-index)个，就可以得到
     * 5. 如果找到了，则返回该节点，否则返回null
     */
    public static HeroNode1 findLastIndexNode(HeroNode1 head, int index) {
        //判断如果链表为空，返回null
        if (head.next == null) {
            return null;//没有找到
        }
        //第一个遍历得到链表的长度(节点个数)
        int size = getLength(head);
        //第二次遍历 size-index 位置，就是我们倒数的第K个节点
        //先做一个index的校验
        if (index <= 0 || index > size) {
            return null;
        }
        //定义给辅助变量，for 循环定位到倒数的index
        HeroNode1 cur = head.next;
        for (int i = 0; i < size - index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    /**
     * 将单链表反转1
     */
    public static void reversetList1(HeroNode1 node) {
        if (node.next == null || node.next.next == null) return;
        HeroNode1 newNode = new HeroNode1(0, "", "");
        HeroNode1 temp = node.next;
        Stack<HeroNode1> stack = new Stack<>();
        while (temp != null) {
            stack.push(temp);
            temp = temp.next;
        }
        HeroNode1 temp2 = newNode;
        while (!stack.empty()) {
            temp = stack.pop();
            temp2.next = temp;
            temp2 = temp2.next;
        }
        temp2.next = null;
        node.next = newNode.next;
    }

    /**
     * 将单链表反转2
     */
    public static void reversetList2(HeroNode1 head) {
        //如果当前链表为空，或者只有一个节点，无需反转，直接返回
        if (head.next == null || head.next.next == null) return;

        //定义一个辅助的指针(变量)，帮助我们遍历原来的链表
        HeroNode1 cur = head.next;
        HeroNode1 next;// 指向当前节点[cur]的下一个节点
        HeroNode1 reverseHead = new HeroNode1(0, "", "");
        //遍历原来的链表，每遍历一个节点，就将其取出，并放在新的链表reverseHead 的最前端(头插法)
        while (cur != null) {
            next = cur.next;//先暂时保存当前节点的下一个节点，因为后面需要使用
            cur.next = reverseHead.next;//将cur的下一个节点指向新的链表的最前端
            reverseHead.next = cur;//将cur 连接到新的链表上
            cur = next;//让cur后移
        }
        //将head.next 指向 reverseHead.next, 实现单链表的反转
        head.next = reverseHead.next;
    }

    /**
     * 单链表的逆序打印1：栈
      */
    public static void reversePrint1(HeroNode1 head) {
        if (head.next == null) {
            return;//空链表，不能打印
        }
        //创建要给一个栈，将各个节点压入栈
        Stack<HeroNode1> stack = new Stack<HeroNode1>();
        HeroNode1 cur = head.next;
        //将链表的所有节点压入栈
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;//cur后移，这样就可以压入下一个节点
        }
        //将栈中的节点进行打印,pop 出栈
        while (stack.size() > 0) {
            System.out.println(stack.pop());//stack的特点是先进后出
        }
    }

    /**
     * 单链表的逆序打印2：递归
      */
    public static void reversePrint2(HeroNode1 head) {
        if(head.next != null) {
            reversePrint2(head.next);
            System.out.println(head.next);
        }
    }
}