package BasicKnowledge.设计模式.七大原则;

public class _5_开闭原则 {
    public static void main(String[] args) {
        //使用看看存在的问题
        GraphicEditor graphicEditor = new GraphicEditor();
        graphicEditor.drawShape(new Rectangle());
        graphicEditor.drawShape(new Circle());
    }
}

//这是一个用于绘图的类 [使用方]
class GraphicEditor {
    //接收 Shape 对象，然后根据type，来绘制不同的图形
    public void drawShape(Shape s) {
        if (s.m_type == 1) drawRectangle(s);
        else if (s.m_type == 2) drawCircle(s);
    }

    //绘制矩形
    public void drawRectangle(Shape r) {
        System.out.println("绘制矩形 ");
    }

    //绘制圆形
    public void drawCircle(Shape r) {
        System.out.println("绘制圆形 ");
    }
}

// Shape 类，基类
class Shape {
    int m_type;
}

class Rectangle extends Shape {
    Rectangle() {
        super.m_type = 1;
    }
}

class Circle extends Shape {
    Circle() {
        super.m_type = 2;
    }
}

class Ocp {
    public static void main(String[] args) {
//使用看看存在的问题
        GraphicEditor1 graphicEditor = new GraphicEditor1();
        graphicEditor.drawShape(new Rectangle1());
        graphicEditor.drawShape(new Circle1());
        graphicEditor.drawShape(new Triangle1());
    }

}

//这是一个用于绘图的类 [使用方]
class GraphicEditor1 {
    //接收Shape对象，调用draw方法
    public void drawShape(Shape1 s) {
        s.draw();
    }
}

//Shape类，基类
abstract class Shape1 {
    int m_type;
    public abstract void draw();//抽象方法
}

class Rectangle1 extends Shape1 {
    Rectangle1() {
        super.m_type = 1;
    }

    @Override
    public void draw() {
        System.out.println("绘制矩形 ");
    }
}

class Circle1 extends Shape1 {
    Circle1() {
        super.m_type = 2;
    }

    @Override
    public void draw() {
        System.out.println("绘制圆形 ");
    }
}

//新增画三角形
class Triangle1 extends Shape1 {
    Triangle1() {
        super.m_type = 3;
    }

    @Override
    public void draw() {
        System.out.println("绘制三角形 ");
    }
}