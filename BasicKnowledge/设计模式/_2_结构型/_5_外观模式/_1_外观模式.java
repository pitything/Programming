package BasicKnowledge.设计模式._2_结构型._5_外观模式;

public class _1_外观模式 {
    public static void main(String[] args) {
        //这里直接调用。。 很麻烦
        HomeTheaterFacade homeTheaterFacade = new HomeTheaterFacade();
        homeTheaterFacade.ready();
        homeTheaterFacade.play();
        homeTheaterFacade.end();
    }
}

class HomeTheaterFacade {
    //定义各个子系统对象
    private TheaterLight theaterLight;
    private Popcorn popcorn;
    private Stereo stereo;
    private Projector projector;
    private Screen screen;
    private DVDPlayer dVDPlayer;

    public HomeTheaterFacade() {
        super();
        this.theaterLight = TheaterLight.getInstance();
        this.popcorn = Popcorn.getInstance();
        this.stereo = Stereo.getInstance();
        this.projector = Projector.getInstance();
        this.screen = Screen.getInstance();
        this.dVDPlayer = DVDPlayer.getInstanc();
    }

    //操作分成 4 步
    public void ready() {
        popcorn.on();
        popcorn.pop();
        screen.down();
        projector.on();
        stereo.on();
        dVDPlayer.on();
        theaterLight.dim();
    }

    public void play() {
        dVDPlayer.play();
    }

    public void pause() {
        dVDPlayer.pause();
    }

    public void end() {
        popcorn.off();
        theaterLight.bright();
        screen.up();
        projector.off();
        stereo.off();
        dVDPlayer.off();
    }
}

class Popcorn {
    private static Popcorn instance = new Popcorn();

    public static Popcorn getInstance() {
        return instance;
    }

    public void on() {
        System.out.println("popcornon");
    }

    public void off() {
        System.out.println("popcornff");
    }

    public void pop() {
        System.out.println("popcornispoping ");
    }
}

class Projector {
    private static Projector instance = new Projector();

    public static Projector getInstance() {
        return instance;
    }

    public void on() {
        System.out.println(" Projector   on");
    }

    public void off() {
        System.out.println(" Projector   ff");
    }

    public void focus() {
        System.out.println(" Projector   is Projector    ");
    }
}

class Screen {
    private static Screen instance = new Screen();

    public static Screen getInstance() {
        return instance;
    }

    public void up() {
        System.out.println(" Screen   up");
    }

    public void down() {
        System.out.println(" Screen   down");
    }
}


class Stereo {
    private static Stereo instance = new Stereo();

    public static Stereo getInstance() {
        return instance;
    }

    public void on() {
        System.out.println(" Stereo   on");
    }

    public void off() {
        System.out.println(" Screen   off");
    }

    public void up() {

        System.out.println(" Screen   up");
    }
}

class TheaterLight {
    private static TheaterLight instance = new TheaterLight();

    public static TheaterLight getInstance() {
        return instance;
    }
    public void on() {
        System.out.println(" TheaterLight   on");
    }

    public void off() {
        System.out.println(" TheaterLight   off");
    }

    public void dim() {
        System.out.println(" TheaterLight   dim");
    }

    public void bright() {
        System.out.println(" TheaterLight   bright");
    }
}
class DVDPlayer {
    //使用单例模式, 使用饿汉式
    private static DVDPlayer instance = new DVDPlayer();

    public static DVDPlayer getInstanc() {
        return instance;
    }

    public void on() {
        System.out.println("dvdon");
    }

    public void off() {
        System.out.println("dvdoff");
    }

    public void play() {
        System.out.println("dvdisplaying");
    }

    public void pause() {
        System.out.println("dvdpause");
    }
}