import java.util.Arrays;

public class OOP1 {

    public static int aa;
    public OOP1(){}
    public OOP1(String str){System.out.println(str);}

    public static void main(String[] args) throws InterruptedException {
        // 可变参数方法
        variableNumParam("aaa");
    }


    /**
     * 方法的重载
     */
    public int add(int a, int b){return a + b;}
    public int add(int a, int b, int c){return a + b + c;}
    public double add(double a, double b){return a + b;}

    /**
     * 可变参数方法
     * @param
     * @param strs
     */
    //JDK 5.0以前：采用数组形参来定义方法，传入多个同一类型变量
    static void variableNumParam(Double[] strs){System.out.println(Arrays.toString(strs));}
    //JDK5.0：采用可变个数形参来定义方法，传入多个同一类型变量
    static void variableNumParam(String aaa, String... strs){System.out.println(Arrays.toString(strs));}
}