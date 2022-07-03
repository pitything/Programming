import java.util.concurrent.locks.ReentrantLock;

/**
 * 多线程的同步问题
 */
public class TestThread2 {
    public static int ticketCount = 100;
    public static void main(String[] args) {
        /** 不共享一个seller，但是ticketCount是静态的static */
//        Thread thread1 = new Thread(new Seller(), "售票处1");
//        Thread thread2 = new Thread(new Seller(), "售票处2");
//        Thread thread3 = new Thread(new Seller(), "售票处3");
        Thread thread1 = new Thread(new Seller2(), "售票处1");
        Thread thread2 = new Thread(new Seller2(), "售票处2");
        Thread thread3 = new Thread(new Seller2(), "售票处3");

        /** 共享同一个seller，共用一个ticketCount */
//        Seller2 seller = new Seller2();
//        Thread thread1 = new Thread(seller, "售票处1");
//        Thread thread2 = new Thread(seller, "售票处2");
//        Thread thread3 = new Thread(seller, "售票处3");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}

class Seller implements Runnable{
    //        public static int ticketCount = 100;
    public int ticketCount = 1000;
    Object object = new Object();
    @Override
    public void run() {
        while (true) {
            synchronized (this) {
                if (ticketCount > 0) {
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println(Thread.currentThread().getName() + "售出车票，tick号为： " + ticketCount--);
                }else{
                    break;
                }
            }
        }
    }
}

class Seller2 implements Runnable{
    //        public static int ticketCount = 100;
    public static int ticketCount = 300;
    Object object = new Object();
    @Override
    public void run() {
        while (true) {
            method();
        }
    }

    public static synchronized void method(){
//    public synchronized void method(){
        if (ticketCount > 0) {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "售出车票，tick号为： " + ticketCount--);
        }
    }
}


