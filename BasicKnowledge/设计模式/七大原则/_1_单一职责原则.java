package BasicKnowledge.设计模式.七大原则;

public class _1_单一职责原则 {
    public static void main(String[] args) {
        Vehicle vehicle = new Vehicle();
        vehicle.run("摩托车");
        vehicle.run("汽车");
        vehicle.run("飞机");

        RoadVehicle roadVehicle = new RoadVehicle();
        roadVehicle.run("摩托车");
        roadVehicle.run("汽车");
        AirVehicle airVehicle = new AirVehicle();
        airVehicle.run("飞机");

        Vehicle2 vehicle2 = new Vehicle2();
        vehicle2.run("汽车");
        vehicle2.runAir("飞机");
    }
}

// 方式 1
// 1. 在方式 1 的run方法中，违反了单一职责原则
// 2. 解决的方案非常的简单，根据交通工具运行方法不同，分解成不同类即可
class Vehicle {
    public void run(String vehicle) {
        System.out.println(vehicle + " 在公路上运行");
    }
}

// 方式 2
// 1. 遵守单一职责原则
// 2. 但是这样做的改动很大，即将类分解，同时修改客户端
// 3. 改进：直接修改Vehicle 类，改动的代码会比较少=>方案 3
class RoadVehicle {
    public void run(String vehicle) {
        System.out.println(vehicle + "公路运行");
    }
}

class AirVehicle {
    public void run(String vehicle) {
        System.out.println(vehicle + "天空运行");
    }
}

// 方式 3
// 1. 这种修改方法没有对原来的类做大的修改，只是增加方法
// 2. 这里虽然没有在类这个级别上遵守单一职责原则，但是在方法级别上，仍然是遵守单一职责
class Vehicle2 {
    public void run(String vehicle) {
        System.out.println(vehicle + " 在公路上运行");
    }

    public void runAir(String vehicle) {
        System.out.println(vehicle + " 在天空上运行");
    }
}