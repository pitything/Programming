import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * 多线程
 */
public class TestThread1 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        /** Thread类的构造器 */
        // Thread()：创建新的Thread对象
        Thread aa = new Thread();
        // Thread(String threadname)：创建线程并指定线程实例名
        Thread bb = new Thread("bb");
        // Thread(Runnable target)：指定创建线程的目标对象，它实现了Runnable接口中的run方法
        Thread cc = new Thread(new Runnable() {
            @Override
            public void run() {
//                System.out.println("cc");
            }
        });
        // Thread(Runnable target, String name)：创建新的Thread对象
        Thread dd = new Thread(new Runnable() {
            @Override
            public void run() {
//                System.out.println("dd");
            }
        }, "dd");


        /** 创建多线程的方式 */
        /** 1.继承Thread类，重写run方法  */
        Thread1 thread1 = new Thread1();
        thread1.start();

        new Thread("test01"){
            @Override
            public void run() {
//                System.out.println("test01");
            }
        }.start();

        /** 2.实现Runnable接口，重写run方法  */
        new Thread(new Thread2()).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
//                System.out.println("test02");
            }
        }).start();

        /** 3.实现Callable接口，重写call方法 */
        Thread3 thread3 = new Thread3(666);
        FutureTask futureTask = new FutureTask(thread3);
        new Thread(futureTask).start();
        System.out.println("Thread3----" + futureTask.get());

        FutureTask futureTask1 = new FutureTask<Integer>(new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                return 999;
            }
        });
        new Thread(futureTask1).start();
        System.out.println(futureTask1.get());

        /** 4.使用线程池 */
        System.out.println("----------------使用线程池-------------------");
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        // 1.execute 用于执行Runnable接口的线程
        Thread2 thread22 = new Thread2();
        executorService.execute(thread22);
        // 2.submit 用于执行Callable接口的线程
        Thread3 thread33 = new Thread3(333);
        Future<?> submit = executorService.submit(thread33);
        System.out.println("submit: " + submit.get());
        // 3.invokeAll 用于执行实现了Callable接口的线程列表
        List<Callable<Integer>> taskList = new ArrayList<>();
        Thread3 thread34;
        for (int i = 0; i < 10; i++) {
            thread34 = new Thread3(i);
            taskList.add(thread34);
        }
        List<Future<Integer>> futures = executorService.invokeAll(taskList);
        for (Future entity : futures) {
            System.out.println(entity.get());
        }
    }

}

/** 1.继承Thread类，重写run方法  */
class Thread1 extends Thread{
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            if( i == 300) {
                try {
                    sleep(400);
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
//            System.out.println("Thread1");
        }
    }
}

/** 2.实现Runnable接口，重写run方法  */
class Thread2 implements Runnable{
    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName() + ": Thread2");
        }
    }
}

/** 3.实现Callable接口，重写call方法 */
class Thread3 implements Callable<Integer>{
    private int count;
    Thread3(int count){this.count = count;}

    @Override
    public Integer call() throws Exception {
        System.out.println(Thread.currentThread().getName());
        return count;
    }
}