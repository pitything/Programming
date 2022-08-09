package BasicKnowledge.设计模式._2_结构型._7_代理模式;

public class _1_静态代理 {
    public static void main(String[] args) {
        //创建目标对象(被代理对象)
        TeacherDao teacherDao = new TeacherDao();
        //创建代理对象, 同时将被代理对象传递给代理对象
        TeacherDaoProxy teacherDaoProxy = new TeacherDaoProxy(teacherDao);

        //通过代理对象，调用到被代理对象的方法
        //即：执行的是代理对象的方法，代理对象再去调用目标对象的方法
        teacherDaoProxy.teach();
    }
}

//接口
interface ITeacherDao {
    void teach();// 授课的方法
}

class TeacherDao implements ITeacherDao {
    @Override
    public void teach() {
        System.out.println("老师授课中 。。。。。");
    }
}

//代理对象,静态代理
class TeacherDaoProxy implements ITeacherDao {
    private ITeacherDao target;// 目标对象，通过接口来聚合

    //构造器
    public TeacherDaoProxy(ITeacherDao target) {
        this.target = target;
    }

    @Override
    public void teach() {
        System.out.println("开始代理 完成某些操作。。。。。 ");//方法
        target.teach();
        System.out.println("提交。。。。。");//方法
    }
}