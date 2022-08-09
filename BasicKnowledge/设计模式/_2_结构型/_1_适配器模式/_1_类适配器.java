package BasicKnowledge.设计模式._2_结构型._1_适配器模式;

public class _1_类适配器 {
    public static void main(String[] args) {
        Phone phone = new Phone();
        phone.charging(new VoltageAdapter());
    }
}

class Phone {
    //充电
    public void charging(IVoltage5V iVoltage5V) {
        if (iVoltage5V.output5V() == 5) {
            System.out.println("电压为 5V, 可以充电~~");
        } else if (iVoltage5V.output5V() > 5) {
            System.out.println("电压大于 5V, 不能充电~~");
        }
    }
}

//适配接口
interface IVoltage5V {
    int output5V();
}

//被适配的类
class Voltage220V {
    //输出 220V的电压
    public int output220V() {
        int src = 220;
        System.out.println("电压=" + src + "伏");
        return src;
    }
}

//适配器类
class VoltageAdapter extends Voltage220V implements IVoltage5V {
    @Override
    public int output5V() {
        //获取到 220V电压
        int srcV = output220V();
        int dstV = srcV / 44;//转成 5 v
        return dstV;
    }
}