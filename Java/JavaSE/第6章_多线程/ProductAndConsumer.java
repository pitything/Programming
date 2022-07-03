public class ProductAndConsumer {
    static int balance = 0;

    public static void main(String[] args) {
        new Thread(){
            @Override
            public void run() {
                System.out.println(this);
                while(true){
                    // 注意：用ProductAndConsumer.class来锁，不然用this的话锁不住
                    synchronized (ProductAndConsumer.class){
                        if(balance < 100){
                            System.out.println("生产商品：" + ++balance);
                            ProductAndConsumer.class.notify();
                        }else {
                            System.out.println("货物过剩，不生产了。。。");
                            try {
                                ProductAndConsumer.class.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }.start();

        new Thread(){
            @Override
            public void run() {
                System.out.println(this);
                while(true){
                    synchronized (ProductAndConsumer.class){
                        if(balance > 0){
                            System.out.println("消费商品：" + balance--);
                            ProductAndConsumer.class.notify();
                        }else {
                            System.out.println("货物缺少，开始生产。。。");
                            try {
                                ProductAndConsumer.class.wait();
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }.start();

//        Platform platform = new Platform();
//        new Thread(() -> {
//            while (true){
//                try {
//                    platform.addProduct();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//
//        new Thread(() -> {
//            while(true){
//                try {
//                    platform.sellProduct();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
    }
}

class Platform{
    private int product = 0;

    public synchronized void addProduct() throws InterruptedException {
        if(product >= 20){
            System.out.println("不生产了，商品太多了。。。");
            wait();
        }else{
            notifyAll();
            System.out.println("生产商品" + ++product);
        }
    }

    public synchronized void sellProduct() throws InterruptedException {
        if(product <= 0){
            System.out.println("商品卖完了。。。。");
            wait();
        }else{
            notifyAll();
            System.out.println("卖出商品" + product--);
        }
    }
}