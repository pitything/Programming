package BasicKnowledge.设计模式._3_行为型._8_解释器模式;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Stack;

public class _1_解释器模式 {
    public static void main(String[] args) throws IOException {
        String expStr = "a+b";
        HashMap<String, Integer> var = new HashMap<String, Integer>(){{put("a", 10);put("b", 20);}};

        Calculator calculator = new Calculator(expStr);
        System.out.println("运算结果：" + expStr + "=" + calculator.run(var));
    }
}

class Calculator {
    // 定义表达式
    private Expression expression;

    // 构造函数传参，并解析
    public Calculator(String expStr) {//expStr=a+b
        // 安排运算先后顺序
        Stack<Expression> stack = new Stack<>();
        // 表达式拆分成字符数组
        char[] charArray = expStr.toCharArray();//[a,+,b]
        Expression left = null;
        Expression right = null;
        //遍历我们的字符数组， 即遍历 [a,+,b]
        // 针对不同的情况，做处理
        for (int i = 0; i < charArray.length; i++) {
            switch (charArray[i]) {
                case '+'://
                    left = stack.pop();// 从stack取出left=>"a"
                    right = new VarExpression(String.valueOf(charArray[++i]));// 取出右表达式 "b"
                    stack.push(new AddExpression(left, right));// 然后根据得到left 和 right 构建 AddExpresson加入stack
                    break;
                case '-'://
                    left = stack.pop();
                    right = new VarExpression(String.valueOf(charArray[++i]));
                    stack.push(new SubExpression(left, right));
                    break;
                default:
                    //如果是一个Var 就创建要给 VarExpression  对象，并push到 stack
                    stack.push(new VarExpression(String.valueOf(charArray[i])));
                    break;
            }
        }
        //当遍历完整个 charArray 数组后，stack 就得到最后 Expression
        this.expression = stack.pop();
    }

    public int run(HashMap<String, Integer> var) {
        //最后将表达式a+b和 var={a= 10 ,b= 20 }
        //然后传递给expression的 interpreter进行解释执行
        return this.expression.interpreter(var);
    }
}

/**
 * 抽象类表达式，通过HashMap 键值对, 可以获取到变量的值
 *
 * @author Administrator
 */
abstract class Expression {
    //a+b-c
    // 解释公式和数值,key 就是公式(表达式) 参数[a,b,c],value就是就是具体值
    //HashMap{a= 10 ,b= 20 }
    public abstract int interpreter(HashMap<String, Integer> var);
}

/**
 * 变量的解释器
 *
 * @author  Administrator
 */
class VarExpression extends Expression {
    private String key;//key=a,key=b,key=c

    public VarExpression(String key) {
        this.key = key;
    }

    //var 就是{a= 10 ,b= 20 }， interpreter 根据 变量名称，返回对应值
    @Override
    public int interpreter(HashMap<String, Integer> var) {
        return var.get(this.key);
    }
}

/**
 * 抽象运算符号解析器 这里，每个运算符号，都只和自己左右两个数字有关系，
 * 但左右两个数字有可能也是一个解析的结果，无论何种类型，都是 Expression 类的实现类
 *
 * @author  Administrator
 */
class SymbolExpression extends Expression {
    protected Expression left;
    protected Expression right;

    public SymbolExpression(Expression left, Expression right) {
        this.left = left;
        this.right = right;
    }

    //因为 Symbol Expression  是让其子类来实现，因此  interpreter 是一个默认实现
    @Override
    public int interpreter(HashMap<String, Integer> var) {
        return 0;
    }
}

/**
 * 加法解释器
 *
 * @author Administrator
 */
class AddExpression extends SymbolExpression {
    public AddExpression(Expression left, Expression right) {
        super(left, right);
    }
    //处理相加
    //var 仍然是 {a= 10 ,b= 20 } 一会我们debug 源码,就ok
    public int interpreter(HashMap<String, Integer> var) {
        // super.left. interpreter(var) ： 返回 left 表达式对应的值 a= 10
        // super.right. interpreter(var): 返回right 表达式对应值 b= 20
        return super.left.interpreter(var) + super.right.interpreter(var);
    }

}

class SubExpression extends SymbolExpression {
    public SubExpression(Expression left, Expression right) {
        super(left, right);
    }

    //求出left 和 right 表达式相减后的结果
    public int interpreter(HashMap<String, Integer> var) {
        return super.left.interpreter(var) - super.right.interpreter(var);
    }
}