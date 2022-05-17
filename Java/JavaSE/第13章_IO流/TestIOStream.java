import com.sun.corba.se.impl.orbutil.ObjectWriter;
import sun.util.resources.ga.LocaleNames_ga;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

public class TestIOStream {
    public static void main (String[] args) throws IOException, ClassNotFoundException {
        /** 测试File类 */
//        testFileClass();

        File file = new File("/Users/leon_chiang/Desktop/test.txt");
        System.out.println("上次修改时间：" + Instant.ofEpochMilli(file.lastModified()).atZone(ZoneOffset.ofHours(8)).toLocalDateTime());

        /** 测试OutputStream \ Writer */
//        testOutputStream(file);
//        /** 测试InputStream \ Reader */
//        testInputStream(file);

        /** 测试 BufferInputStream \ BufferReader */
//        testBufferInputStream(file);

        /** 测试 ObjectOutputStream  */
        testObjectOutputStream(file);
        /** 测试 ObjectInputStream  */
        testObjectInputStream(file);

    }

    /**
     * 测试File类
     */
    public static void testFileClass() throws IOException {
        File file = new File("/Users/leon_chiang/Desktop/test.txt");
        file.createNewFile();
        file.mkdir();
        System.out.println(file.getPath());
        System.out.println(file.getName());
        System.out.println(file.getParent());
        System.out.println(file.length());
        System.out.println(file.list());
        System.out.println(file.listFiles());
        System.out.println(file.renameTo(new File("/Users/leon_chiang/Desktop/test111.txt")));
        System.out.println(file.delete());
    }

    /**
     * 测试OutputStream \ Writer
     * @param file
     */
    public static void testOutputStream(File file) throws IOException {
        String str = "是否真的爱我\n" +
                "\n" +
                " \n" +
                "\n" +
                "是否真的爱我\n" +
                "\n" +
                "别对我沉默\n" +
                "\n" +
                "这月色美丽的夜晚\n" +
                "\n" +
                "你在想什么\n" +
                "\n" +
                "是否真的爱我\n" +
                "\n" +
                "别对我冷漠\n" +
                "\n" +
                "你心里有什么样的话\n" +
                "\n" +
                "今晚跟我说\n" +
                "\n" +
                "还记得曾经编织的梦想\n" +
                "\n" +
                "也许你早遗忘\n" +
                "\n" +
                "绚烂的爱情在多年以后\n" +
                "\n" +
                "也许渐渐变得平淡\n" +
                "\n" +
                "你可知不是我不了解\n" +
                "\n" +
                "爱情微妙难捉摸\n" +
                "\n" +
                "不是我不怀疑弦外的爱情会迷惑\n" +
                "\n" +
                "总是肩并肩走过的岁月 刻画在心头\n" +
                "\n" +
                "切切的叮咛我 与你长相厮守\n" +
                "\n" +
                " \n" +
                "\n" +
                "是否真的爱我\n" +
                "\n" +
                "别对我沉默\n" +
                "\n" +
                "这月色美丽的夜晚\n" +
                "\n" +
                "你在想什么\n" +
                "\n" +
                "是否真的爱我\n" +
                "\n" +
                "别对我冷漠\n" +
                "\n" +
                "你心里有什么样的话\n" +
                "\n" +
                "今晚跟我说\n" +
                "\n" +
                "还记得曾经编织的梦想\n" +
                "\n" +
                "也许你早遗忘\n" +
                "\n" +
                "绚烂的爱情在多年以后\n" +
                "\n" +
                "也许渐渐变得平淡\n" +
                "\n" +
                "你可知不是我不了解\n" +
                "\n" +
                "爱情微妙难捉摸\n" +
                "\n" +
                "不是我不怀疑弦外的爱情会迷惑\n" +
                "\n" +
                "总是肩并肩走过的岁月 刻画在心头\n" +
                "\n" +
                "切切的叮咛我 与你长相厮守\n" +
                "\n" +
                " \n" +
                "\n" +
                "你可知不是我不了解\n" +
                "\n" +
                "爱情微妙难捉摸\n" +
                "\n" +
                "不是我不怀疑弦外的爱情会迷惑\n" +
                "\n" +
                "总是肩并肩走过的岁月 刻画在心头\n" +
                "\n" +
                "切切的叮咛我 与你长相厮守\n" +
                "\n" +
                " \n" +
                "\n" +
                "不是我不了解\n" +
                "\n" +
                "爱情微妙难捉摸\n" +
                "\n" +
                "不是我不怀疑弦外的爱情会迷惑\n" +
                "\n" +
                "总是肩并肩走过的岁月 刻画在心头\n" +
                "\n" +
                "切切的叮咛我 与你长相厮守";
        long a = System.currentTimeMillis();
        try(OutputStream outputStream = new FileOutputStream(file, true)){
            outputStream.write(str.getBytes(StandardCharsets.UTF_8));
        }
        long b = System.currentTimeMillis();
        try(Writer writer = new FileWriter(file, true)){
            writer.write(str);
        }
        long c = System.currentTimeMillis();
        System.out.println("FileOutputStream耗时：" + (b - a));
        System.out.println("FileWriter耗时：" + (c - b));
    }

    /** 测试InputStream \ Reader */
    public static void testInputStream(File file) throws IOException{
        StringBuffer str = new StringBuffer();
        StringBuffer str1 = new StringBuffer();
        byte[] bytes = new byte[1024];
        char[] chars = new char[1024];
        long a = System.currentTimeMillis();
        try(InputStream inputStream1 = new FileInputStream(file)){
            /** 测试InputStream */
            while (inputStream1.read(bytes) != -1) {
                str.append(new String(bytes, "utf-8"));
                bytes = new byte[1024];
            }
            System.out.println(str);
        }
        long b = System.currentTimeMillis();
        try(Reader reader = new FileReader(file)){
            /** 测试Reader */
            while(reader.read(chars) != -1){
                str1.append(new String(chars));
                chars = new char[1024];
            }
            System.out.println(str1);
        }
        long c = System.currentTimeMillis();
        System.out.println("FileInputStream耗时：" + (b - a));
        System.out.println("FileReader耗时：" + (c - b));
    }

    /** 测试 BufferInputStream \ BufferReader */
    public static void testBufferInputStream(File file) throws IOException{
        StringBuffer str = new StringBuffer();
        StringBuffer str1 = new StringBuffer();
        byte[] bytes = new byte[1024];
        long a = System.currentTimeMillis();
        try(BufferedInputStream inputStream1 = new BufferedInputStream(new FileInputStream(file))){
            /** 测试InputStream */
            while (inputStream1.read(bytes) != -1) {
                str.append(new String(bytes, "utf-8"));
                bytes = new byte[1024];
            }
            System.out.println(str);
        }
        long b = System.currentTimeMillis();
        try(BufferedReader reader = new BufferedReader(new FileReader(file))){
            /** 测试Reader */
            String res1;
            while((res1 = reader.readLine()) != null){
                str1.append(res1);
                str1.append("\n");
            }
            System.out.println(str1);
        }
        long c = System.currentTimeMillis();
        System.out.println("FileInputStream耗时：" + (b - a));
        System.out.println("FileReader耗时：" + (c - b));
    }

    /**
     * 测试 BufferedOutputStream \ BufferedWriter
     * @param file
     */
    public static void testBufferedOutputStream(File file) throws IOException {
        String str = "是否真的爱我\n" +
                "\n" +
                " \n" +
                "\n" +
                "是否真的爱我\n" +
                "\n" +
                "别对我沉默\n" +
                "\n" +
                "这月色美丽的夜晚\n" +
                "\n" +
                "你在想什么\n" +
                "\n" +
                "是否真的爱我\n" +
                "\n" +
                "别对我冷漠\n" +
                "\n" +
                "你心里有什么样的话\n" +
                "\n" +
                "今晚跟我说\n" +
                "\n" +
                "还记得曾经编织的梦想\n" +
                "\n" +
                "也许你早遗忘\n" +
                "\n" +
                "绚烂的爱情在多年以后\n" +
                "\n" +
                "也许渐渐变得平淡\n" +
                "\n" +
                "你可知不是我不了解\n" +
                "\n" +
                "爱情微妙难捉摸\n" +
                "\n" +
                "不是我不怀疑弦外的爱情会迷惑\n" +
                "\n" +
                "总是肩并肩走过的岁月 刻画在心头\n" +
                "\n" +
                "切切的叮咛我 与你长相厮守\n" +
                "\n" +
                " \n" +
                "\n" +
                "是否真的爱我\n" +
                "\n" +
                "别对我沉默\n" +
                "\n" +
                "这月色美丽的夜晚\n" +
                "\n" +
                "你在想什么\n" +
                "\n" +
                "是否真的爱我\n" +
                "\n" +
                "别对我冷漠\n" +
                "\n" +
                "你心里有什么样的话\n" +
                "\n" +
                "今晚跟我说\n" +
                "\n" +
                "还记得曾经编织的梦想\n" +
                "\n" +
                "也许你早遗忘\n" +
                "\n" +
                "绚烂的爱情在多年以后\n" +
                "\n" +
                "也许渐渐变得平淡\n" +
                "\n" +
                "你可知不是我不了解\n" +
                "\n" +
                "爱情微妙难捉摸\n" +
                "\n" +
                "不是我不怀疑弦外的爱情会迷惑\n" +
                "\n" +
                "总是肩并肩走过的岁月 刻画在心头\n" +
                "\n" +
                "切切的叮咛我 与你长相厮守\n" +
                "\n" +
                " \n" +
                "\n" +
                "你可知不是我不了解\n" +
                "\n" +
                "爱情微妙难捉摸\n" +
                "\n" +
                "不是我不怀疑弦外的爱情会迷惑\n" +
                "\n" +
                "总是肩并肩走过的岁月 刻画在心头\n" +
                "\n" +
                "切切的叮咛我 与你长相厮守\n" +
                "\n" +
                " \n" +
                "\n" +
                "不是我不了解\n" +
                "\n" +
                "爱情微妙难捉摸\n" +
                "\n" +
                "不是我不怀疑弦外的爱情会迷惑\n" +
                "\n" +
                "总是肩并肩走过的岁月 刻画在心头\n" +
                "\n" +
                "切切的叮咛我 与你长相厮守";
        long a = System.currentTimeMillis();
        try(BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(file, true))){
            outputStream.write(str.getBytes(StandardCharsets.UTF_8));
            outputStream.flush();
        }
        long b = System.currentTimeMillis();
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))){
            writer.write(str);
            writer.flush();
        }
        long c = System.currentTimeMillis();
        System.out.println("FileOutputStream耗时：" + (b - a));
        System.out.println("FileWriter耗时：" + (c - b));
    }

    /**
     * 测试 ObjectOutputStream
     * @param file
     */
    public static void testObjectOutputStream(File file) throws IOException {
        Man man = new Man(23, "leon", 175);
        long a = System.currentTimeMillis();
        try(ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))){
            outputStream.writeObject(man);
            outputStream.flush();
        }
        long b = System.currentTimeMillis();
        System.out.println("FileOutputStream耗时：" + (b - a));
    }

    /**
     * 测试 ObjectInputStream
     * @param file
     */
    public static void testObjectInputStream(File file) throws IOException, ClassNotFoundException {
        long a = System.currentTimeMillis();
        try(ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))){
            Object man = inputStream.readObject();
            System.out.println(man);
        }
        long b = System.currentTimeMillis();
        System.out.println("FileOutputStream耗时：" + (b - a));
    }
}


class Man implements Serializable{

    public Integer age;
    public String name;
    public Integer height;

    public Man(Integer age, String name, Integer height) {
        this.age = age;
        this.name = name;
        this.height = height;
    }

    @Override
    public String toString() {
        return "Man{" +
                "age=" + age +
                ", name='" + name + '\'' +
                ", height=" + height +
                '}';
    }
}
