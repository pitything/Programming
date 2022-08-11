package BasicKnowledge.设计模式._3_行为型._7_备忘录模式;

import java.util.ArrayList;
import java.util.HashMap;

class _2_游戏角色恢复状态实例 {
    public static void main(String[] args) {
        //创建游戏角色
        GameRole gameRole = new GameRole();
        gameRole.setVit(100);
        gameRole.setDef(100);
        System.out.println("和boss大战前的状态");
        gameRole.display();

        //把当前状态保存caretaker
        Caretaker1 caretaker = new Caretaker1();
        caretaker.setMemento1(gameRole.createMemento1());
        System.out.println("和boss大战~~~");
        gameRole.setDef(30);
        gameRole.setVit(30);
        gameRole.display();
        System.out.println("大战后，使用备忘录对象恢复到站前");
        gameRole.recoverGameRoleFromMemento1(caretaker.getMemento1());
        System.out.println("恢复后的状态");
        gameRole.display();
    }
}

//守护者对象, 保存游戏角色的状态
class Caretaker1 {
    //如果只保存一次状态
    private Memento1 memento;
    // 对 GameRole  保存多次状态
    private ArrayList<Memento1> mementos;
    // 对多个游戏角色保存多个状态
    private HashMap<String, ArrayList<Memento1>> rolesMemento1s;

    public Memento1 getMemento1() {
        return memento;
    }

    public void setMemento1(Memento1 memento) {
        this.memento = memento;
    }
}

class GameRole {
    private int vit;
    private int def;

    //创建 Memento1 ,即根据当前的状态得到 Memento1 
    public Memento1 createMemento1() {
        return new Memento1(vit, def);
    }

    //从备忘录对象，恢复 GameRole 的状态
    public void recoverGameRoleFromMemento1(Memento1 memento) {
        this.vit = memento.getVit();
        this.def = memento.getDef();
    }

    //显示当前游戏角色的状态
    public void display() {
        System.out.println("游戏角色当前的攻击力：" + this.vit + " 防御力:" + this.def);
    }

    public int getVit() {
        return vit;
    }

    public void setVit(int vit) {
        this.vit = vit;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }
}

class Memento1 {
    //攻击力
    private int vit;
    //防御力
    private int def;

    public Memento1(int vit, int def) {
        super();
        this.vit = vit;
        this.def = def;
    }

    public int getVit() {
        return vit;
    }

    public void setVit(int vit) {
        this.vit = vit;
    }

    public int getDef() {
        return def;
    }

    public void setDef(int def) {
        this.def = def;
    }
}