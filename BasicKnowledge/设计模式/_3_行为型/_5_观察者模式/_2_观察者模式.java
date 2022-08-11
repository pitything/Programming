package BasicKnowledge.设计模式._3_行为型._5_观察者模式;

import java.util.ArrayList;

public class _2_观察者模式 {
    public static void main(String[] args) {
        //创建一个 WeatherData1
        WeatherData1 weatherData = new WeatherData1();

        //创建观察者
        CurrentConditions1 currentConditions = new CurrentConditions1();
        BaiduSite baiduSite = new BaiduSite();

        //注册到weatherData
        weatherData.registerObserver(currentConditions);
        weatherData.registerObserver(baiduSite);

        //测试
        System.out.println("通知各个注册的观察者, 看看信息");
        weatherData.setData(10f, 100f, 30.3f);
        weatherData.removeObserver(currentConditions);

        System.out.println();
        System.out.println("通知各个注册的观察者, 看看信息");
        weatherData.setData(10f, 100f, 30.3f);
    }
}

//观察者接口，有观察者来实现
interface Observer {
    public void update(float temperature, float pressure, float humidity);
}

class BaiduSite implements Observer {
    // 温度，气压，湿度
    private float temperature;
    private float pressure;
    private float humidity;

    // 更新 天气情况，是由 WeatherData1  来调用，我使用推送模式
    public void update(float temperature, float pressure, float humidity) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        display();
    }

    // 显示
    public void display() {
        System.out.println("===百度网站====");
        System.out.println("***百度网站 气温 :" + temperature + "***");
        System.out.println("***百度网站 气压:" + pressure + "***");
        System.out.println("***百度网站 湿度:" + humidity + "***");
    }
}

class CurrentConditions1 implements Observer {
    // 温度，气压，湿度
    private float temperature;
    private float pressure;
    private float humidity;

    // 更新 天气情况，是由 WeatherData1  来调用，我使用推送模式
    public void update(float temperature, float pressure, float humidity) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        display();
    }

    // 显示
    public void display() {
        System.out.println("***Today Temperature:" + temperature + "***");
        System.out.println("***Today Pressure:" + pressure + "***");
        System.out.println("***Today Humidity:" + humidity + "***");
    }
}

//接口, 让 WeatherData1  来实现
interface Subject {
    public void registerObserver(Observer o);
    public void removeObserver(Observer o);
    public void notifyObservers();
}

/**
 * 类是核心
 * 1. 包含最新的天气情况信息
 * 2. 含有 观察者集合，使用ArrayList管理
 * 3. 当数据有更新时，就主动的调用 ArrayList, 通知所有的(接入方)就看到最新的信息
 *
 * @authorAdministrator
 */
class WeatherData1 implements Subject {
    private float temperatrue;
    private float pressure;
    private float humidity;
    //观察者集合
    private ArrayList<Observer> observers;

    //加入新的第三方
    public WeatherData1() {
        observers = new ArrayList<Observer>();
    }

    public float getTemperature() {
        return temperatrue;
    }

    public float getPressure() {
        return pressure;
    }

    public float getHumidity() {
        return humidity;
    }

    public void dataChange() {
//调用 接入方的 update
        notifyObservers();
    }

    //当数据有更新时，就调用 setData
    public void setData(float temperature, float pressure, float humidity) {
        this.temperatrue = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        //调用dataChange， 将最新的信息 推送给 接入方 currentConditions
        dataChange();
    }

    //注册一个观察者
    @Override
    public void registerObserver(Observer o) {
        observers.add(o);
    }

    //移除一个观察者
    @Override
    public void removeObserver(Observer o) {
        if (observers.contains(o)) {
            observers.remove(o);
        }
    }

    //遍历所有的观察者，并通知
    @Override
    public void notifyObservers() {
        for (int i = 0; i < observers.size(); i++) {
            observers.get(i).update(this.temperatrue, this.pressure, this.humidity);
        }
    }
}