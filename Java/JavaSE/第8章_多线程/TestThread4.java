/**
 * 两个线程交替打印从1-100
 */
public class TestThread4 {
    public static void main(String[] args) {
        Print1to100 a = new Print1to100();
        Thread t1 = new Thread(a);
        Thread t2 = new Thread(a);
        t1.start();
        t2.start();
    }
}

class Print1to100 implements Runnable {
    private int count = 0;
    private String[] abc = new String[]{"a", "b", "c"};

    public void run() {
        while(true) {
            synchronized (Print1to100.class) {
                // 必须由锁对象调用
                Print1to100.class.notify();
                if(count <= 100) {
                    System.out.println(Thread.currentThread().getName() + " ：" + abc[(count++) % 3]);
                }else{
                    break;
                }
                try {
                    // 必须由锁对象调用
                    Print1to100.class.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
