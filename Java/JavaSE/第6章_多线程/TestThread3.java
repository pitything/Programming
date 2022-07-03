/** 银行有一个账户。
    有两个储户分别向同一个账户存3000元，每次存1000，存3次。每次存完打印账户余额。
 */
public class TestThread3 {
    public static void main(String[] args) {
        Account a = new Account();
        Thread t1 = new Thread(a);
        Thread t2 = new Thread(a);

        t1.start();
        t2.start();
    }

}

class Account implements Runnable{
    private int balance = 0;
    @Override
    public void run() {
        synchronized (Account.class) {
            for (int i = 0; i < 3; i++) {
                balance += 1000;
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + " 余额：" + balance);
            }
        }
    }
}
