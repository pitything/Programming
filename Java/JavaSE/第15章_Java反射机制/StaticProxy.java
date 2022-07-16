public class StaticProxy {
    public static void main(String[] args) {
        // 老板演讲
        ProxyBoss boss = new ProxyBoss(new Boss());
        boss.say();

        // 举行婚礼
        ProxyMan1 proxyMan1 = new ProxyMan1(new Man1());
        proxyMan1.startWedding();

    }
}

interface CommonInterface{
    void say();
}

class Boss implements CommonInterface{
    @Override
    public void say() {
        System.out.println("同志们辛苦了！我开始指导工作了！");
    }
}

class ProxyBoss implements CommonInterface{
    private CommonInterface commonInterface;

    public ProxyBoss(CommonInterface commonInterface){
        this.commonInterface = commonInterface;
    }

    @Override
    public void say() {
        System.out.println("端茶倒水。");
        commonInterface.say();
        System.out.println("感谢说话。");
    }
}

interface Wedding{
    void startWedding();
}

class Man1 implements Wedding{
    @Override
    public void startWedding() {
        System.out.println("单膝跪地求婚。");
    }
}

class ProxyMan1 implements Wedding{
    private Wedding wedding;

    public ProxyMan1(Wedding wedding){
        this.wedding = wedding;
    }

    @Override
    public void startWedding() {
        System.out.println("致开场词。");
        wedding.startWedding();
        System.out.println("致祝福语。");
    }
}