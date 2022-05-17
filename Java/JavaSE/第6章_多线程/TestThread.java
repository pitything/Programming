import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 创建多线程的方式
 */
public class TestThread {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
//        Myclass1 myclass11 = new Myclass1("myclass1-");
//        Myclass1 myclass12 = new Myclass1("myclass2-");
//        myclass11.start();
//        myclass12.start();


//        Myclass2 myclass21 = new Myclass2();
//        Myclass2 myclass22 = new Myclass2();
//        Thread tyclass21 =  new Thread(myclass21);
//        Thread tyclass22 =  new Thread(myclass21);
//        tyclass21.start();
//        tyclass22.start();

        FutureTask futureTask2 = new FutureTask((Callable<Integer>) () -> {
            System.out.println(0);
            return 0;
        });
        new Thread(futureTask2).start();
        System.out.println(futureTask2.get());

        /** 计算1-100的和 */
//        int count = 100000;
//        long a = System.currentTimeMillis();
//        Myclass3 myclass3;
        ExecutorService executorService = Executors.newFixedThreadPool(30);
//        List<Callable<Integer>> taskList = new ArrayList<>();
//        for (int i = 1; i <= count; i++) {
//            myclass3  = new Myclass3(i);
//            taskList.add(myclass3);
////            new Thread(futureTask).start();
//        }
//        executorService.invokeAll(taskList);
//        long b = System.currentTimeMillis();
////        for (int i = 1; i <= count; i++) {
////            int total = 0;
////            for (int j = 0; j < i; j++){
////                total += j;
////            }
////            System.out.println(total);
////        }
////        long c = System.currentTimeMillis();
//        System.out.println("多线程耗时：" + (b - a));
////        System.out.println("单线程耗时：" + (c - b) );

    }


}


/**
 * 方式1：继承Thread类，重写run方法
 */
class Myclass1 extends Thread{
    int count = 100;
    public Myclass1(){
    }
    public Myclass1(String name){
        super(name);
    }

    @Override
    public void run(){
        while(count > 0) {
            System.out.println(Thread.currentThread().getName() + count--);
        }
    }
}

/**
 * 方式2：实现Runnable接口，重写run方法
 */
class Myclass2 implements Runnable{
    public static Integer count = 100;
    private static Object obj = new Object();
    @Override
    public void run(){
        while (true){
//            synchronized(count) { // 不能这样写：count不是唯一的，是变动的值
            synchronized(obj){
                // 当obj为锁时，要实现线程之间的通信，通过notify() wait()方法，必须要用锁的这两个方法
                obj.notifyAll();
                if (count > 0) {
//                    try {
//                        Thread.sleep(100);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
                    System.out.println(Thread.currentThread().getName() + "-" + count--);
                } else {
                    break;
                }
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

/**
 * 方式3：实现Callable接口，重写call方法，可以有返回值
 */
class Myclass3 implements Callable<Integer> {
    private int count;

    Myclass3(int cout){
        this.count = cout;
    }

    @Override
    public Integer call(){
        int total = 0;
        for (int i = 0; i < count; i++){
            total += i;
        }
        System.out.println(total);
        return total;
    }
}

