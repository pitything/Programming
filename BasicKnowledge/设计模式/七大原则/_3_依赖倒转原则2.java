package BasicKnowledge.设计模式.七大原则;

public class _3_依赖倒转原则2 {
    public static void main(String[] args) {
        ITV changHong = new ChangHong();

        // 通过接口传递实现依赖
        OpenAndClose1 openAndClose1 = new OpenAndClose1();
        openAndClose1.open(changHong);

        // 通过构造器进行依赖传递
        OpenAndClose2 openAndClose2 = new OpenAndClose2(changHong);
        openAndClose2.open();

        // 通过setter方法进行依赖传递
        OpenAndClose3 openAndClose3 = new OpenAndClose3();
        openAndClose3.setTv(changHong);
        openAndClose3.open();
    }
}

//ITV接口
interface ITV {
    void play();
}

class ChangHong implements ITV {
    @Override
    public void play() {
        System.out.println("长虹电视机，打开");
    }
}

// 方式 1 ： 通过接口传递实现依赖
// 开关的接口
interface IOpenAndClose1 {
    void open(ITV tv);//抽象方法,接收接口
}

class OpenAndClose1 implements IOpenAndClose1 {
    public void open(ITV tv) {
        tv.play();
    }
}

// 方式 2 : 通过构造方法依赖传递
interface IOpenAndClose2 {
    void open();//抽象方法
}

class OpenAndClose2 implements IOpenAndClose2 {
    public ITV tv;//成员
    public OpenAndClose2(ITV tv) {//构造器
        this.tv = tv;
    }
    public void open() {
        this.tv.play();
    }
}

// 方式 3 : 通过setter方法传递
interface IOpenAndClose3 {
    void open();// 抽象方法
    void setTv(ITV tv);
}

class OpenAndClose3 implements IOpenAndClose3 {
    private ITV tv;
    public void setTv(ITV tv) {
        this.tv = tv;
    }
    public void open() {
        this.tv.play();
    }
}