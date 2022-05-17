import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class DeadLock {
    private static StringBuffer s1 = new StringBuffer();
    private static StringBuffer s2 = new StringBuffer();

    static Lock lock1 = new ReentrantLock();
    static Lock lock2 = new ReentrantLock();

    public static void main(String[] args) {

        new Thread() {
            @Override
            public void run(){
//                synchronized(s1){
                lock1.lock();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    s1.append("a");
                    s2.append("1");
//                    synchronized(s2){
                lock2.lock();
                        s1.append("b");
                        s2.append("2");
                        System.out.println(s1);
                        System.out.println(s2);
                        lock2.unlock();
//                    }
//                }
                lock1.unlock();
            }
        }.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
//                synchronized (s2) {
                lock2.lock();
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    s1.append("c");
                    s2.append("3");
//                    synchronized (s1) {
                lock1.lock();
                        s1.append("d");
                        s2.append("4");
                        System.out.println(s1);
                        System.out.println(s2);
                lock1.unlock();
//                    }
                    lock2.unlock();
//                }
            }
        }).start();
    }
}

