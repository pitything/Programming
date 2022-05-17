import java.util.Arrays;

public class TestString {
    public static void main(String[] args) {
        TestStringChange testStringChange = new TestStringChange();
        System.out.println(testStringChange);
        testStringChange.change(testStringChange.name1, testStringChange.name2,
                testStringChange.name3, testStringChange.name4);
        System.out.println(testStringChange);


    }
}

class TestStringChange{
    String name1 = "Hello String";
    StringBuilder name2 = new StringBuilder("Hello StringBuilder");
    StringBuffer name3 = new StringBuffer("Hello StringBuffer");
    char[] name4 = new char[]{'0', '1','2','3'};

    public void change(String str, StringBuilder stringBuilder, StringBuffer stringBuffer, char[] chars){
        /** String 为final，不可改变，因此只能修改str形参的引用 */
        str += "Hello java";
        /** 下面三个是可以改变的，因此把引用的内容改变了，内容改变后，传入对象的值也会变动 */
        stringBuilder.append("java");
        stringBuffer.append("java");
        chars[0] = 'j';
    }

    @Override
    public String toString() {
        return "TestStringChange{" +
                "name1='" + name1 + '\'' +
                ", name2=" + name2 +
                ", name3=" + name3 +
                ", name4=" + Arrays.toString(name4) +
                '}';
    }
}

