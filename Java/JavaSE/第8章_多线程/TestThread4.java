/**
 * 两个线程交替打印从1-100
 */
public class TestThread4 {
    public static void main(String[] args) {
        Print1to100 a = new Print1to100();
        Thread t1 = new Thread(a);
        Thread t2 = new Thread(a);
        Thread t3 = new Thread(a);
        Thread t4 = new Thread(a);
        Thread t5 = new Thread(a);
        Thread t6 = new Thread(a);
        t1.start();
        t2.start();
        // t3.start();
        // t4.start();
        // t5.start();
        // t6.start();
    }
}

class Print1to100 implements Runnable {
    Integer count = 1;
    static Object object = new Object();

    @Override
    public void run() {
        while (true) {
            // System.out.println(Thread.currentThread().getName() + "---------------------");
            synchronized (object) {
            // synchronized (Print1to100.class) {
                if (count <= 100) {
                    // Print1to100.class.notifyAll();
                    System.out.println(Thread.currentThread().getName() + " -----> " + count++);
                    // try {
                    //     Print1to100.class.wait();
                    // } catch (InterruptedException e) {
                    //     e.printStackTrace();
                    // }
                }else{
                    break;
                }
            }
        }
        // while(count <= 100){
        //     System.out.println(Thread.currentThread().getName() + "---------------------");
        //     synchronized (Print1to100.class){
        //         System.out.println(Thread.currentThread().getName() + "---------notifyall------------");
        //         Print1to100.class.notifyAll();
        //         // try {
        //         //     Thread.sleep(100);
        //         // } catch (InterruptedException e) {
        //         //     e.printStackTrace();
        //         // }
        //         System.out.println(Thread.currentThread().getName() + " -----> " + count++);
        //         try {
        //             System.out.println(Thread.currentThread().getName() + "---------wait------------");
        //             Print1to100.class.wait();
        //         } catch (InterruptedException e) {
        //             e.printStackTrace();
        //         }
        //     }
        // }

    }
}

// class Print1to100 implements Runnable {
//     private int count = 0;
//     private String[] abc = new String[]{"a", "b", "c"};
//
//     public void run() {
//         while(true) {
//             synchronized (Print1to100.class) {
//                 // 必须由锁对象调用
//                 Print1to100.class.notify();
//                 if(count <= 100) {
//                     System.out.println(Thread.currentThread().getName() + " ：" + abc[(count++) % 3]);
//                 }else{
//                     break;
//                 }
//                 try {
//                     // 必须由锁对象调用
//                     Print1to100.class.wait();
//                 } catch (InterruptedException e) {
//                     e.printStackTrace();
//                 }
//             }
//         }
//     }
// }
