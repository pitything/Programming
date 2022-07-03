public class TestException {
    public static void main(String[] args) {
        System.out.println(new TestException().test());

    }

    /** try-catch-finally处理异常 */
    public int test(){
        try{
            throw new MyException("测试异常。。。");
            // 可能产生异常的代码
//            int a = 1 / 0;
//            return 1;
        }catch (ArithmeticException exception){
            // 发生异常后的处理语句
            System.out.println(exception.getMessage());
            return 2;
        }catch (Exception exception){
            // 发生异常后的处理语句
            System.out.println(exception.getMessage());
            return 3;
        }finally {
            // 无论是否发生异常都会执行的语句
            System.out.println("finally...");
            return 4;
        }
    }

    /** throws声明抛出异常 */
    public int test1() throws Exception{
        return 0;
    }

    /** 使用throw手动抛出异常 */
    public int test2() throws Exception {
        throw new Exception("test2.....");
    }

}

class MyException extends RuntimeException{
    static final long serialVersionUID = -7034897190745766939L;

    MyException(){}
    MyException(String message){
        super(message);
    }
}
