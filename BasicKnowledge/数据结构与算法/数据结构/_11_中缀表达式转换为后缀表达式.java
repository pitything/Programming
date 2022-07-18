package 数据结构;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class _11_中缀表达式转换为后缀表达式 {
    public static void main(String[] args) {
        //完成将一个中缀表达式转成后缀表达式的功能：1+((2+3)*4)-5 => 转成 1 2 3 + 4 × + 5 –
        // 2. 因为直接对str 进行操作不方便，因此 先将 "1+((2+3)*4)-5"=》 中缀的表达式对应的List
        // 即 "1+((2+3)*4)-5"=>ArrayList[ 1 ,+,(,(, 2 ,+, 3 ,),*, 4 ,),-, 5 ]
        // 3. 将得到的中缀表达式对应的List=> 后缀表达式对应的List
        // 即ArrayList[ 1 ,+,(,(, 2 ,+, 3 ,),*, 4 ,),-, 5 ] =》ArrayList[ 1 , 2 , 3 ,+, 4 ,*,+, 5 ,–]
        String expression = "1+((2+3)*4)-5";
        List<String> infixExpressionList = toInfixExpressionList(expression);
        System.out.println("中缀表达式对应的List=" + infixExpressionList);//ArrayList[ 1 ,+,(,(, 2 ,+, 3 ,),*, 4 ,),-, 5 ]
        List<String> suffixExpreesionList = parseSuffixExpreesionList(infixExpressionList);
        System.out.println("后缀表达式对应的List" + suffixExpreesionList);//ArrayList[ 1 , 2 , 3 ,+, 4 ,*,+, 5 ,–]

        System.out.printf("expression=%d", _10_逆波兰计算器.calculate(suffixExpreesionList));
        System.out.println();
    }

    /**
     * 中缀表达式List => 后缀表达式List
     */
    public static List<String> parseSuffixExpreesionList(List<String> ls) {
        //定义两个栈
        Stack<String> s1 = new Stack<String>();// 符号栈
        //说明：因为s2这个栈，在整个转换过程中，没有pop操作，而且后面我们还需要逆序输出
        //因此比较麻烦，这里我们就不用 Stack<String > 直接使用 List<String >s2
        //Stack<String >s2= new Stack<String >();// 储存中间结果的栈s2
        List<String> s2 = new ArrayList<String>();// 储存中间结果的Lists2

        //遍历ls
        for (String item : ls) {
            //如果是一个数，加入s2
            if (item.matches("\\d+")) {
                s2.add(item);
            } else if (item.equals("(")) {
                s1.push(item);
            } else if (item.equals(")")) {
                //如果是右括号“)”，则依次弹出s1栈顶的运算符，并压入s2，直到遇到左括号为止，此时将这一对括号丢弃
                while (!s1.peek().equals("(")) {
                    s2.add(s1.pop());
                }
                s1.pop();//!!! 将 (弹出 s1栈， 消除小括号
            } else {
                //当item的优先级小于等于s1栈顶运算符, 将s1栈顶的运算符弹出并加入到s2中，再次转到( 4. 1 )与s1中新的栈顶运算符相比较
                //问题：我们缺少一个比较优先级高低的方法
                while (s1.size() != 0 && Operation.getValue(s1.peek()) >= Operation.getValue(item)) {
                    s2.add(s1.pop());
                }
                //还需要将item压入栈
                s1.push(item);
            }
        }

        //将s1中剩余的运算符依次弹出并加入s2
        while (s1.size() != 0) {
            s2.add(s1.pop());
        }
        return s2;//注意因为是存放到List, 因此按顺序输出就是对应的后缀表达式对应的List
    }

    /**
     * 中缀表达式 => 对应的List
     */
    public static List<String> toInfixExpressionList(String s) {
        //定义一个List,存放中缀表达式 对应的内容
        List<String> ls = new ArrayList<String>();
        int i = 0;//这时是一个指针，用于遍历 中缀表达式字符串
        String str;// 对多位数的拼接
        char c;// 每遍历到一个字符，就放入到c
        do {
            //如果c是一个非数字，我需要加入到ls
            if ((c = s.charAt(i)) < 48 || (c = s.charAt(i)) > 57) {
                ls.add("" + c);
                i++;//i需要后移
            } else {//如果是一个数，需要考虑多位数
                str = "";//先将str 置成""' 0 '[ 48 ]->' 9 '[ 57 ]
                while (i < s.length() && (c = s.charAt(i)) >= 48 && (c = s.charAt(i)) <= 57) {
                    str += c;//拼接
                    i++;
                }
                ls.add(str);
            }
        } while (i < s.length());
        return ls;//返回
    }
}

//编写一个类 Operation 可以返回一个运算符 对应的优先级
class Operation {
    private static int ADD = 1;
    private static int SUB = 1;
    private static int MUL = 2;
    private static int DIV = 2;

    //写一个方法，返回对应的优先级数字
    public static int getValue(String operation) {
        int result = 0;
        switch (operation) {
            case "+":
                result = ADD;
                break;
            case "-":
                result = SUB;
                break;
            case "*":
                result = MUL;
                break;
            case "/":
                result = DIV;
                break;
            case "(":
                break;
            case ")":
                break;
            default:
                System.out.println("不存在该运算符");
                break;
        }
        return result;
    }
}   