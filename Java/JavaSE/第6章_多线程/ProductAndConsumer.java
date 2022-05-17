public class ProductAndConsumer {
    public static void main(String[] args) {
        Platform platform = new Platform();
        new Thread(() -> {
            while (true){
                try {
                    platform.addProduct();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(() -> {
            while(true){
                try {
                    platform.sellProduct();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

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
