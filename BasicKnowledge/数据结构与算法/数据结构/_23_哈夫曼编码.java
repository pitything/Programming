import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class _23_哈夫曼编码 {
    static Map<Byte, String> byteMap = new HashMap<>();

    public static void main(String[] args) throws Exception {
        String s = "i like like like java do you like a java";
        System.out.println("压缩前：" + s);
        System.out.println("长度：" + s.length());
        byte[] hfmCode = getHfmCode(s.getBytes(StandardCharsets.UTF_8));
        System.out.println("压缩后：" + Arrays.toString(hfmCode));
        System.out.println("长度：" + hfmCode.length);
        System.out.println("解码后：" + new String(decode(byteMap, hfmCode)));

        File file1 = new File("/Users/leon_chiang/Study/Github/Programming/BasicKnowledge/数据结构与算法/数据结构/src.bmp");
        File file2 = new File("/Users/leon_chiang/Study/Github/Programming/BasicKnowledge/数据结构与算法/数据结构/temp.zip");
        File file3 = new File("/Users/leon_chiang/Study/Github/Programming/BasicKnowledge/数据结构与算法/数据结构/des.bmp");
        try(InputStream is = new FileInputStream(file1);
            OutputStream os = new FileOutputStream(file3);
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file2));
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file2))){

            /** 压缩 */
            byte[] bytes = new byte[is.available()];
            is.read(bytes, 0, bytes.length);
            System.out.println("压缩前，长度：" + bytes.length);
            byte[] hfmBytes = getHfmCode(bytes);
            System.out.println("压缩后，长度：" + hfmBytes.length);
            oos.writeObject(hfmBytes);
            oos.writeObject(byteMap);

            /** 解压缩 */
            byte[] hfmBytes2 = (byte[])ois.readObject();
            System.out.println("解压缩前，长度：" + hfmBytes2.length);
            Map<Byte, String> byteMap2 = (Map<Byte, String>)ois.readObject();
            byte[] res = decode(byteMap2, hfmBytes2);
            System.out.println("解压缩后，长度：" + res.length);
            os.write(res);
        }
    }

    /**
     *@paramsrcFile 你传入的希望压缩的文件的全路径
     *@paramdstFile 我们压缩后将压缩文件放到哪个目录
     */
    public static void zipFile(){
        OutputStream os=null;
        ObjectOutputStream oos=null;
        FileInputStream is=null;
        try{
            is=new FileInputStream("/Users/leon_chiang/Study/Github/Programming/BasicKnowledge/数据结构与算法/数据结构/resume.png");
            byte[]b=new byte[is.available()];
            is.read(b);
            System.out.println(b.length);
            byte[]huffmanBytes=getHfmCode(b);
            System.out.println(huffmanBytes.length);
//            os=new FileOutputStream(dstFile);
//            oos=new ObjectOutputStream(os);
//            oos.writeObject(huffmanBytes);
        }catch(Exception e){
            System.out.println(e.getMessage());
        }finally{
            try{
                is.close();
                oos.close();
                os.close();
            }catch(Exception e){
                System.out.println(e.getMessage());
            }
        }
    }

    /**
     * 将字符串转为哈夫曼编码
     *
     * @param bytes
     * @return
     */
    public static byte[] getHfmCode(byte[] bytes) {
        // 构建哈夫曼树
        HeroNode2 root = createHuffmanTree(bytes);
        // 获取映射表
        StringBuffer codeRes = new StringBuffer();
        preorderTraversal(root, "", codeRes);
        // 根据映射表将s转为哈夫曼编码
        byte[] byteArray = conver2ByteArray(bytes);
        return byteArray;
    }

    /**
     * 可以通过List 创建对应的哈夫曼树
     *
     * @param bytes
     * @return
     */
    private static HeroNode2 createHuffmanTree(byte[] bytes) {
        List<HeroNode2> nodes = new ArrayList<>();
        Map<Byte, Integer> map = new HashMap<>();
        for (byte c : bytes) {
            if (map.get(c) == null) {
                map.put(c, 1);
            } else {
                int count = map.get(c);
                map.put(c, ++count);
            }
        }
        for (Map.Entry<Byte, Integer> entity : map.entrySet()) {
            nodes.add(new HeroNode2(entity.getValue(), "" + entity.getKey()));
        }
        while (nodes.size() > 1) {
            Collections.sort(nodes, Comparator.comparingInt(o -> o.no));
            HeroNode2 leftNode = nodes.get(0);
            HeroNode2 rightNode = nodes.get(1);
            HeroNode2 parent = new HeroNode2(leftNode.no + rightNode.no);
            parent.left = leftNode;
            parent.right = rightNode;
            nodes.remove(leftNode);
            nodes.remove(rightNode);
            nodes.add(parent);
        }
        return nodes.get(0);
    }

    /**
     * 将字符在哈夫曼树上的位置转为编码，左子节点为0，右子节点为1
     *
     * @param root
     * @param code
     * @param stringBuffer
     */
    private static void preorderTraversal(HeroNode2 root, String code, StringBuffer stringBuffer) {
        if (root == null) return;
        StringBuffer sb = new StringBuffer(stringBuffer);
        sb.append(code);
        // 如果是叶子节点
        if (root.name != null) {
            byteMap.put((byte) Integer.parseInt(root.name), sb.toString());
        } else {
            // 如果不是叶子节点，就递归查找
            preorderTraversal(root.left, "0", sb);
            preorderTraversal(root.right, "1", sb);
        }
    }

    /**
     * 将字符串根据映射表转为 byte[]
     *
     * @param bytes
     * @return
     */
    private static byte[] conver2ByteArray(byte[] bytes) {
        StringBuilder res = new StringBuilder();
        for (byte entity : bytes) {
            res.append(byteMap.get(entity));
        }
//        System.out.println("哈夫曼编码：" + res);
        // 将哈夫曼编码的String Builder转为 byte[]
        int length = (res.length() % 8 == 0) ? res.length() / 8 : res.length() / 8 + 1;
        byte[] resByte = new byte[length];
        int index = 0;
        for (int i = 0; i < res.length(); i += 8) {
            // 按照《二进制》将数字转为byte，如：10101000（补码）=> 求原码(符号位不变，取反+1) => 11010111 + 1 => 11011000 = -(64+16+8) = -88
//            System.out.println((byte)Integer.parseInt("10101000", 2));
//            System.out.println(Integer.toBinaryString(-88));
            resByte[index++] = (byte) Integer.parseInt(res.substring(i, Math.min(i + 8, res.length())), 2);
        }
        return resByte;
    }

    /**
     * 完成对压缩数据的解码
     *
     * @return 就是原来的字符串对应的数组
     * @param huffmanCodes 哈夫曼编码表map
     * @param huffmanBytes 哈夫曼编码得到的字节数组
     */
    private static byte[] decode(Map<Byte, String> huffmanCodes, byte[] huffmanBytes) {
        StringBuilder stringBuilder = new StringBuilder();
        //将byte数组转成二进制的字符串
        for (int i = 0; i < huffmanBytes.length; i++) {
            byte b = huffmanBytes[i];
            //判断是不是最后一个字节
            boolean notLast = (i != huffmanBytes.length - 1);
            stringBuilder.append(byteToBitString(notLast, b));
        }
        // 把哈夫曼编码表进行调换，因为反向查询 a-> 100100 - >a
        Map<String, Byte> map = new HashMap<String, Byte>();
        for (Map.Entry<Byte, String> entry : huffmanCodes.entrySet()) {
            map.put(entry.getValue(), entry.getKey());
        }

        //创建要给集合，存放byte
        List<Byte> list = new ArrayList<>();
        //i 可以理解成就是索引,扫描 stringBuilder
        for (int i = 0; i < stringBuilder.length(); ) {
            int count = 1;// 小的计数器
            boolean flag = true;
            Byte b = null;

            while (flag) {
                String key = stringBuilder.substring(i, i + count);//i 不动，让count移动，指定匹配到一个字符
                b = map.get(key);
                if (b == null) {//说明没有匹配到
                    count++;
                } else {//匹配到
                    flag = false;
                }
            }
            list.add(b);
            i += count;//i 直接移动到 count
        }
        //当for循环结束后，我们list中就存放了所有的字符 "i like like like java do you like a java"
        //把list 中的数据放入到byte[] 并返回
        byte b[]=new byte[list.size()];
        for (int i = 0; i < b.length; i++) {
            b[i] = list.get(i);
        }
        return b;
    }

    /**
     * 将一个byte 转成一个二进制的字符串, 如果看不懂，可以参考我讲的Java基础 二进制的原码，反码，补码
     *
     * @return 是该b 对应的二进制的字符串，（注意是按补码返回）
     * @param b 传入的 byte
     * @param notLast 非最后一个字节，如果是true，表示不是最后一个字节，需要补高位，如果是false，表示是最后一个字节，无需补高位
     */
    private static String byteToBitString(boolean notLast, byte b) {
        // 如果是正数就要补高位，否则substring会越界，比如 2 转为2进制是 10，subString(2-8)会越界
        // 按位或 256 100000000 | 00000001 => 100000001
        String str = Integer.toBinaryString(notLast ? b | 256 : b);//返回的是temp对应的二进制的补码
        // 最后一个正数不用补0
        return notLast ? str.substring(str.length() - 8) : str;
    }
}