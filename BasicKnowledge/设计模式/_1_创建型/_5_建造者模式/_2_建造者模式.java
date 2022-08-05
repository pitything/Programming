package BasicKnowledge.设计模式._1_创建型._5_建造者模式;

public class _2_建造者模式 {
    public static void main(String[] args) {
        //准备创建房子的指挥者
        HouseDirector houseDirector = new HouseDirector();

        //盖普通房子
        HouseBuilder commonHouse = new CommonHouseBuilder();
        //完成盖房子，返回产品(普通房子)
        House house = houseDirector.constructHouse(commonHouse);

        System.out.println("--------------------------");
        //盖高楼
        HouseBuilder highBuilding = new HighHouseBuilder();
        //完成盖房子，返回产品(高楼)
        House house2 = houseDirector.constructHouse(highBuilding);
    }
}

//产品->Product
class House {
    public String base;
    public String wall;
    public String roof;
}

// 抽象的建造者
abstract class HouseBuilder {
    protected House house = new House();

    //将建造的流程写好, 抽象的方法
    public abstract void buildBasic();
    public abstract void buildWalls();
    public abstract void roofed();

    //建造房子好， 将产品(房子) 返回
    public House buildHouse() {
        return house;
    }
}

class CommonHouseBuilder extends HouseBuilder {
    @Override
    public void buildBasic() {
        house.base = "普通房子打地基 5 米 ";
        System.out.println("普通房子打地基 5 米 ");
    }
    @Override
    public void buildWalls() {
        house.wall = "普通房子砌墙 10 cm";
        System.out.println("普通房子砌墙 10 cm");
    }
    @Override
    public void roofed() {
        house.roof = "普通房子屋顶";
        System.out.println("普通房子屋顶 ");
    }
}

class HighHouseBuilder extends HouseBuilder {
    @Override
    public void buildBasic() {
        house.roof = "高楼的打地基";
        System.out.println("高楼的打地基 100 米 ");
    }
    @Override
    public void buildWalls() {
        house.roof = "高楼的砌墙";
        System.out.println("高楼的砌墙 20 cm");
    }
    @Override
    public void roofed() {
        house.roof = "高楼的透明屋顶";
        System.out.println("高楼的透明屋顶 ");
    }
}

//指挥者，这里去指定制作流程，返回产品
class HouseDirector {
    //如何处理建造房子的流程，交给指挥者
    public House constructHouse(HouseBuilder houseBuilder) {
        houseBuilder.buildBasic();
        houseBuilder.buildWalls();
        houseBuilder.roofed();
        return houseBuilder.buildHouse();
    }
}