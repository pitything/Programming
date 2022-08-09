package BasicKnowledge.设计模式._2_结构型._1_适配器模式;

public class _2_对象适配器 {
    public static void main(String[] args) {
        Phone phone = new Phone();
        phone.charging(new VoltageAdapter1(new Voltage220V()));
    }
}

//适配器类
class VoltageAdapter1 implements IVoltage5V {
    private Voltage220V voltage220V;// 关联关系-聚合
    //通过构造器，传入一个Voltage220V 实例
    public VoltageAdapter1(Voltage220V voltage220v) {
        this.voltage220V = voltage220v;
    }

    @Override
    public int output5V() {
        int dst = 0;
        if (null != voltage220V) {
            int src = voltage220V.output220V();//获取 220 V 电压
            System.out.println("使用对象适配器，进行适配~~");
            dst = src / 44;
            System.out.println("适配完成，输出的电压为=" + dst);
        }
        return dst;
    }
}