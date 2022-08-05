package BasicKnowledge.设计模式.七大原则;


public class _3_依赖倒转原则1 {
    public static void main(String[] args) {
        Person person = new Person();
        person.receive(new Email());

        // 客户端无需改变
        Person2 person2 = new Person2();
        person2.receive(new Email2());
        person2.receive(new WeiXin());
    }
}

// 方式 1
class Email {
    public String getInfo() {
        return "电子邮件信息:hello,world";
    }
}

class Person {
    public void receive(Email email) {
        System.out.println(email.getInfo());
    }
}

// 方式 2
// 1. 如果我们获取的对象是 微信，短信等等，则新增类，同时Perons也要增加相应的接收方法
// 2. 解决思路：引入一个抽象的接口IReceiver, 表示接收者, 这样Person类与接口IReceiver发生依赖
// 因为Email,WeiXin 等等属于接收的范围，他们各自实现IReceiver 接口就ok, 这样我们就符号依赖倒转原则
interface IReceiver {
    public String getInfo();
}

class Email2 implements IReceiver {
    public String getInfo() {
        return "电子邮件信息:hello,world";
    }
}

// 增加微信
class WeiXin implements IReceiver {
    public String getInfo() {
        return "微信信息:hello,ok";
    }
}

class Person2 {
    // 这里我们是对接口的依赖
    public void receive(IReceiver receiver) {
        System.out.println(receiver.getInfo());
    }
}