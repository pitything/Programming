package BasicKnowledge.设计模式._1_创建型._5_建造者模式;

public class _1_传统的方式 {
    public static void main (String[]args){
        CommonHouse commonHouse = new CommonHouse();
        commonHouse.build();
        HighHouse highHouse = new HighHouse();
        highHouse.build();
    }
}

abstract class AbstractHouse {
    //打地基
    public abstract void buildBasic();

    //砌墙
    public abstract void buildWalls();

    //封顶
    public abstract void roofed();

    public void build() {
        buildBasic();
        buildWalls();
        roofed();
    }
}

class CommonHouse extends AbstractHouse {
    @Override
    public void buildBasic() {
        System.out.println("普通房子打地基 5 米 ");
    }
    @Override
    public void buildWalls() {
        System.out.println("普通房子砌墙 10 cm");
    }
    @Override
    public void roofed() {
        System.out.println("普通房子屋顶 ");
    }
}

class HighHouse extends AbstractHouse {
    @Override
    public void buildBasic() {
        System.out.println("高楼的打地基 100 米 ");
    }
    @Override
    public void buildWalls() {
        System.out.println("高楼的砌墙 20 cm");
    }
    @Override
    public void roofed() {
        System.out.println("高楼的透明屋顶 ");
    }
}