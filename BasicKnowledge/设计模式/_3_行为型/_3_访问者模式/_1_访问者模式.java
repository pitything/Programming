package BasicKnowledge.设计模式._3_行为型._3_访问者模式;

import java.util.LinkedList;
import java.util.List;

class _1_访问者模式 {
    public static void main(String[] args) {
        //创建 ObjectStructure
        ObjectStructure objectStructure = new ObjectStructure();
        objectStructure.attach(new Man());
        objectStructure.attach(new Woman());

        Success success = new Success();
        objectStructure.display(success);

        Fail fail = new Fail();
        objectStructure.display(fail);

        Wait wait = new Wait();
        objectStructure.display(wait);
    }
}

abstract class Person {
    //提供一个方法，让访问者可以访问
    public abstract void accept(Action action);
}

//说明
// 1. 这里我们使用到了双分派, 即首先在客户端程序中，将具体状态作为参数传递Woman中(第一次分派)
// 2. 然后Woman 类调用作为参数的 "具体方法" 中方法 getWomanResult, 同时将自己(this)作为参数传入，完成第二次的分派
class Woman extends Person {
    @Override
    public void accept(Action action) {
        action.getWomanResult(this);
    }
}

class Man extends Person {
    @Override
    public void accept(Action action) {
        action.getManResult(this);
    }
}

abstract class Action {
    //得到男性 的测评
    public abstract void getManResult(Man man);

    //得到女的 测评
    public abstract void getWomanResult(Woman woman);
}

class Fail extends Action {
    @Override
    public void getManResult(Man man) {
        System.out.println("男人给的评价该歌手失败 !");
    }

    @Override
    public void getWomanResult(Woman woman) {
        System.out.println("女人给的评价该歌手失败 !");
    }
}

class Success extends Action {
    @Override
    public void getManResult(Man man) {
        System.out.println("男人给的评价该歌手很成功 !");
    }

    @Override
    public void getWomanResult(Woman woman) {
        System.out.println("女人给的评价该歌手很成功 !");
    }
}

class Wait extends Action {
    @Override
    public void getManResult(Man man) {
        System.out.println("男人给的评价是该歌手待定 ");
    }

    @Override
    public void getWomanResult(Woman woman) {
        System.out.println("女人给的评价是该歌手待定 ");
    }
}

//数据结构，管理很多人(Man,Woman)
class ObjectStructure {
    //维护了一个集合
    private List<Person> persons = new LinkedList<>();

    //增加到list
    public void attach(Person p) {
        persons.add(p);
    }

    //移除
    public void detach(Person p) {
        persons.remove(p);
    }

    //显示测评情况
    public void display(Action action) {
        for (Person p : persons) {
            p.accept(action);
        }
    }
}