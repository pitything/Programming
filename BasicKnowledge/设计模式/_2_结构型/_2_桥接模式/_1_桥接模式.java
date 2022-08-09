package BasicKnowledge.设计模式._2_结构型._2_桥接模式;

public class _1_桥接模式 {
    public static void main(String[] args) {
        //获取折叠式手机 (样式 +品牌 )
        Phone phone1 = new FoldedPhone(new XiaoMi());
        phone1.open();
        phone1.call();
        phone1.close();

        System.out.println("================================");
        Phone phone2 = new FoldedPhone(new Vivo());
        phone2.open();
        phone2.call();
        phone2.close();

        System.out.println("================================");
        UpRightPhone phone3 = new UpRightPhone(new XiaoMi());
        phone3.open();
        phone3.call();
        phone3.close();

        System.out.println("================================");
        UpRightPhone phone4 = new UpRightPhone(new Vivo());
        phone4.open();
        phone4.call();
        phone4.close();
    }
}

abstract class Phone {

    private Brand brand;
    //构造器
    public Phone(Brand brand) {
        super();
        this.brand = brand;
    }
    protected void open() {
        this.brand.open();
    }

    protected void close() {
        brand.close();
    }

    protected void call() {
        brand.call();
    }

}
class UpRightPhone extends Phone {

    public UpRightPhone(Brand brand) {
        super(brand);
    }
    public void open() {
        super.open();
        System.out.println("直立样式手机 ");
    }

    public void close() {
        super.close();
        System.out.println("直立样式手机 ");
    }

    public void call() {
        super.call();
        System.out.println("直立样式手机 ");
    }
}
//折叠式手机类，继承 抽象类 Phone
class FoldedPhone extends Phone {
    //构造器
    public FoldedPhone(Brand brand) {
        super(brand);
    }

    public void open() {
        super.open();
        System.out.println("折叠样式手机 ");
    }

    public void close() {
        super.close();
        System.out.println("折叠样式手机 ");
    }

    public void call() {
        super.call();
        System.out.println("折叠样式手机 ");
    }
}

// 品牌
interface Brand {
    void open();
    void close();
    void call();
}
class Vivo implements Brand {

    @Override
    public void open() {
        System.out.println("Vivo手机开机 ");
    }

    @Override
    public void close() {
        System.out.println("Vivo手机关机 ");
    }

    @Override
    public void call() {
        System.out.println("Vivo手机打电话 ");
    }
}

class XiaoMi implements Brand {
    @Override
    public void open() {
        System.out.println("小米手机开机 ");
    }

    @Override
    public void close() {
        System.out.println("小米手机关机 ");
    }

    @Override
    public void call() {
        System.out.println("小米手机打电话 ");
    }
}