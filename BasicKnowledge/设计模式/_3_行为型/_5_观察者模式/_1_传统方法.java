package BasicKnowledge.设计模式._3_行为型._5_观察者模式;

public class _1_传统方法 {
    public static void main(String[] args) {
        //创建接入方 currentConditions
        CurrentConditions currentConditions = new CurrentConditions();
        //创建 WeatherData  并将 接入方 currentConditions传递到  WeatherData 中
        WeatherData weatherData = new WeatherData(currentConditions);
        //更新天气情况
        weatherData.setData(30, 150, 40);
        //天气情况变化
        System.out.println("============天气情况变化=============");
        weatherData.setData(40, 160, 20);
    }
}

/**
 * 显示当前天气情况(可以理解成是气象站自己的网站)
 *
 * @author Administrator
 */
class CurrentConditions {
    // 温度，气压，湿度
    private float temperature;
    private float pressure;
    private float humidity;

    //更新 天气情况，是由 WeatherData  来调用，我使用推送模式
    public void update(float temperature, float pressure, float humidity) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        display();
    }

    //显示
    public void display() {
        System.out.println("***TodaymTemperature:" + temperature + "***");
        System.out.println("***TodaymPressure:" + pressure + "***");
        System.out.println("***TodaymHumidity:" + humidity + "***");
    }
}

/**
 * 类是核心
 * 1. 包含最新的天气情况信息
 * 2. 含有  CurrentConditions 对象
 * 3. 当数据有更新时，就主动的调用  CurrentConditions 对象update方法(含 display), 这样他们(接入方)就看到最新的信息
 *
 * @author Administrator
 */
class WeatherData {
    private float temperature;
    private float pressure;
    private float humidity;
    private CurrentConditions currentConditions;

    //加入新的第三方
    public WeatherData(CurrentConditions currentConditions) {
        this.currentConditions = currentConditions;
    }

    public float getTemperature() {
        return temperature;
    }

    public float getPressure() {
        return pressure;
    }

    public float getHumidity() {
        return humidity;
    }

    public void dataChange() {
        //调用 接入方的 update
        currentConditions.update(getTemperature(), getPressure(), getHumidity());
    }

    //当数据有更新时，就调用 setData
    public void setData(float temperature, float pressure, float humidity) {
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        //调用dataChange， 将最新的信息 推送给 接入方 currentConditions
        dataChange();
    }
}