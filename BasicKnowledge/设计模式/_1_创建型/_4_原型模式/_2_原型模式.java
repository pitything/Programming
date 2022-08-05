package BasicKnowledge.设计模式._1_创建型._4_原型模式;

import java.io.*;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class _2_原型模式 {
    public static void main(String[] args) throws CloneNotSupportedException {
        Sheep1 sheep = new Sheep1("tom", 1, "白色");
        sheep.friend = new Sheep1("jack", 2, "黑色");

        Sheep1 sheep1 = (Sheep1) sheep.clone();//克隆
        Sheep1 sheep2 = (Sheep1) sheep.clone();//克隆
        Sheep1 sheep3 = (Sheep1) sheep.clone();//克隆
        Sheep1 sheep4 = (Sheep1) sheep.clone();//克隆
        Sheep1 sheep5 = (Sheep1) sheep.clone();//克隆

        System.out.println(sheep1.hashCode() + " sheep1 = " + sheep1 + " sheep1.name = " + sheep1.name.hashCode() + " sheep1.friend = " + sheep1.friend.hashCode());
        System.out.println(sheep2.hashCode() + " sheep2 = " + sheep2 + " sheep2.name = " + sheep2.name.hashCode() + " sheep2.friend = " + sheep2.friend.hashCode());
        System.out.println(sheep3.hashCode() + " sheep3 = " + sheep3 + " sheep3.name = " + sheep3.name.hashCode() + " sheep3.friend = " + sheep3.friend.hashCode());
        System.out.println(sheep4.hashCode() + " sheep4 = " + sheep4 + " sheep4.name = " + sheep4.name.hashCode() + " sheep4.friend = " + sheep4.friend.hashCode());
        System.out.println(sheep5.hashCode() + " sheep5 = " + sheep5 + " sheep5.name = " + sheep5.name.hashCode() + " sheep5.friend = " + sheep5.friend.hashCode());

        DeepProtoType d1 = new DeepProtoType("d1");
        d1.deepCloneableTarget = new DeepCloneableTarget("dt1", "dt1");
        // 1.使用clone实现深拷贝
        DeepProtoType d2 = (DeepProtoType) d1.clone();
        System.out.println(d1.hashCode() + " " + d1.deepCloneableTarget.hashCode());
        System.out.println(d2.hashCode() + " " + d2.deepCloneableTarget.hashCode());

        // 2.通过序列化实现深拷贝
        DeepProtoType d3 = d1.deepClone();
        System.out.println(d1.hashCode() + " " + d1.deepCloneableTarget.hashCode());
        System.out.println(d3.hashCode() + " " + d3.deepCloneableTarget.hashCode());
    }
}

class Sheep1 implements Cloneable {
    public String name;
    public int age;
    public String color;
    public Sheep1 friend;//是对象, 克隆是会如何处理, 默认是浅拷贝

    public Sheep1(String name, int age, String color) {
        super();
        this.name = name;
        this.age = age;
        this.color = color;
    }

    @Override
    public String toString() {
        return "Sheep1 [name=" + name + ",age=" + age + ",color=" + color + "]";
    }

    //克隆该实例，使用默认的clone方法来完成，对象默认是浅拷贝
    @Override
    protected Object clone() {
        Sheep1 sheep = null;
        try {
            sheep = (Sheep1) super.clone();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return sheep;
    }
}

class DeepCloneableTarget implements Serializable, Cloneable {
    private static final long serialVersionUID = 1L;
    private String cloneName;
    private String cloneClass;

    //构造器
    public DeepCloneableTarget(String cloneName, String cloneClass) {
        this.cloneName = cloneName;
        this.cloneClass = cloneClass;
    }

    //因为该类的属性，都是String , 因此我们这里使用默认的clone完成即可
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}

class DeepProtoType implements Serializable, Cloneable {
    public String name;//String  属性
    public DeepCloneableTarget deepCloneableTarget;// 引用类型

    public DeepProtoType(String name){this.name = name;}

    //深拷贝 - 方式 1 使用clone方法
    @Override
    protected Object clone() throws CloneNotSupportedException {
        Object deep = null;
        //这里完成对基本数据类型(属性)和String 的克隆
        deep = super.clone();
        //对引用类型的属性，进行单独处理
        DeepProtoType deepProtoType = (DeepProtoType) deep;
        deepProtoType.deepCloneableTarget = (DeepCloneableTarget) deepCloneableTarget.clone();
        return deepProtoType;
    }

    //深拷贝 - 方式 2 通过对象的序列化实现 (推荐)
    public DeepProtoType deepClone() {
        //创建流对象
        ByteArrayOutputStream bos = null;
        ObjectOutputStream oos = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;

        try {
            //序列化
            bos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(bos);
            oos.writeObject(this);//当前这个对象以对象流的方式输出

            //反序列化
            bis = new ByteArrayInputStream(bos.toByteArray());
            ois = new ObjectInputStream(bis);
            DeepProtoType copyObj = (DeepProtoType) ois.readObject();

            return copyObj;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            //关闭流
            try {
                bos.close();
                oos.close();
                bis.close();
                ois.close();
            } catch (Exception e2) {
                System.out.println(e2.getMessage());
            }
        }
    }
}