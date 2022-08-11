package BasicKnowledge.设计模式._3_行为型._9_状态模式;

import java.util.Random;

public class _1_状态模式 {
    public static void main(String[] args) {
        // 创建活动对象，奖品有 1 个奖品
        RaffleActivity activity = new RaffleActivity(1);

        // 我们连续抽 30 次奖
        for (int i = 0; i < 30; i++) {
            System.out.println("--------第" + (i + 1) + "次抽奖----------");
            // 参加抽奖，第一步点击扣除积分
            activity.debuctMoney();
            // 第二步抽奖
            activity.raffle();
        }
    }
}

/**
 * 抽奖活动
 *
 * @author Administrator
 */
class RaffleActivity {
    //state 表示活动当前的状态，是变化
    public State state = null;

    // 奖品数量
    public int count = 0;

    // 四个属性，表示四种状态
    public State noRafflleState = new NoRaffleState(this);  // 不能抽奖状态
    public State canRaffleState = new CanRaffleState(this); // 可以抽奖的状态
    public State dispenseState = new DispenseState(this);// 发放奖品的状态
    public State dispensOutState = new DispenseOutState(this);// 奖品发放完毕状态

    //构造器
    // 1. 初始化当前的状态为 noRafflleState(即不能抽奖的状态)
    // 2. 初始化奖品的数量
    public RaffleActivity(int count) {
        this.state = noRafflleState;
        this.count = count;
    }

    //扣分, 调用当前状态的 deductMoney
    public void debuctMoney() {
        state.deductMoney();
    }

    //抽奖
    public void raffle() {
        // 如果当前的状态是抽奖成功
        if (state.raffle()) {
            //领取奖品
            state.dispensePrize();
        }
    }
}

/**
 * 状态抽象类
 *
 * @author Administrator
 */
abstract class State {
    // 扣除积分 - 50
    public abstract void deductMoney();

    // 是否抽中奖品
    public abstract boolean raffle();

    // 发放奖品
    public abstract void dispensePrize();
}

/**
 * 可以抽奖的状态
 *
 * @author Administrator
 */
class CanRaffleState extends State {
    RaffleActivity activity;

    public CanRaffleState(RaffleActivity activity) {
        this.activity = activity;
    }

    //已经扣除了积分，不能再扣
    @Override
    public void deductMoney() {
        System.out.println("已经扣取过了积分");
    }

    //可以抽奖, 抽完奖后，根据实际情况，改成新的状态
    @Override
    public boolean raffle() {
        System.out.println("正在抽奖，请稍等！");
        Random r = new Random();
        int num = r.nextInt(10);
        // 10 %中奖机会
        if (num == 0) {
            // 改变活动状态为发放奖品 context
            activity.state = (activity.dispenseState);
            return true;
        } else {
            System.out.println("很遗憾没有抽中奖品！");
            // 改变状态为不能抽奖
            activity.state = (activity.noRafflleState);
            return false;
        }
    }

    // 不能发放奖品
    @Override
    public void dispensePrize() {
        System.out.println("没中奖，不能发放奖品");
    }
}

/**
 * 奖品发放完毕状态，当我们activity 改变成 DispenseOutState， 抽奖活动结束
 *
 * @author Administrator
 */
class DispenseOutState extends State {
    // 初始化时传入活动引用
    RaffleActivity activity;

    public DispenseOutState(RaffleActivity activity) {
        this.activity = activity;
    }

    @Override
    public void deductMoney() {
        System.out.println("奖品发送完了，请下次再参加");
    }

    @Override
    public boolean raffle() {
        System.out.println("奖品发送完了，请下次再参加");
        return false;
    }

    @Override
    public void dispensePrize() {
        System.out.println("奖品发送完了，请下次再参加");
    }
}

/**
 * 发放奖品的状态
 *
 * @author Administrator
 */
class DispenseState extends State {
    // 初始化时传入活动引用，发放奖品后改变其状态
    RaffleActivity activity;

    public DispenseState(RaffleActivity activity) {
        this.activity = activity;
    }

    @Override
    public void deductMoney() {
        System.out.println("不能扣除积分");
    }

    @Override
    public boolean raffle() {
        System.out.println("不能抽奖");
        return false;
    }

    //发放奖品
    @Override
    public void dispensePrize() {
        if (activity.count > 0) {
            System.out.println("恭喜中奖了");
            // 改变状态为不能抽奖
            activity.state = (activity.noRafflleState);
        } else {
            System.out.println("很遗憾，奖品发送完了");
            // 改变状态为奖品发送完毕, 后面我们就不可以抽奖
            activity.state = (activity.dispensOutState);
            System.out.println("抽奖活动结束");
            System.exit(0);
        }
    }
}

/**
 * 不能抽奖状态
 *
 * @author Administrator
 */
class NoRaffleState extends State {
    // 初始化时传入活动引用，扣除积分后改变其状态
    RaffleActivity activity;

    public NoRaffleState(RaffleActivity activity) {
        this.activity = activity;
    }

    // 当前状态可以扣积分 , 扣除后，将状态设置成可以抽奖状态
    @Override
    public void deductMoney() {
        System.out.println("扣除 50 积分成功，您可以抽奖了");
        activity.state = (activity.canRaffleState);
    }

    // 当前状态不能抽奖
    @Override
    public boolean raffle() {
        System.out.println("扣了积分才能抽奖喔！");
        return false;
    }

    // 当前状态不能发奖品
    @Override
    public void dispensePrize() {
        System.out.println("不能发放奖品");
    }
}