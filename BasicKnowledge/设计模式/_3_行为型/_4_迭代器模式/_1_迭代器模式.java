package BasicKnowledge.设计模式._3_行为型._4_迭代器模式;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class _1_迭代器模式 {
    public static void main(String[] args) {
        //创建学院
        List<College> collegeList = new ArrayList<College>();

        ComputerCollege computerCollege = new ComputerCollege();
        InfoCollege infoCollege = new InfoCollege();

        collegeList.add(computerCollege);
        //collegeList.add(infoCollege );

        OutPutImpl outPutImpl = new OutPutImpl(collegeList);
        outPutImpl.printCollege();
    }
}

interface College {
    public String getName();

    //增加系的方法
    public void addDepartment(String name, String desc);

    //返回一个迭代器,遍历
    public Iterator createIterator();
}

class ComputerCollege implements College {
    Department[] departments;
    int numOfDepartment = 0;// 保存当前数组的对象个数

    public ComputerCollege() {
        departments = new Department[5];
        addDepartment("Java专业", "Java专业 ");
        addDepartment("PHP专业", "PHP专业 ");
        addDepartment("大数据专业", " 大数据专业 ");
    }

    @Override
    public String getName() {
        return "计算机学院";
    }

    @Override
    public void addDepartment(String name, String desc) {
        Department department = new Department(name, desc);
        departments[numOfDepartment] = department;
        numOfDepartment += 1;
    }

    @Override
    public Iterator createIterator() {

        return new ComputerCollegeIterator(departments);
    }
}

class ComputerCollegeIterator implements Iterator {
    //这里我们需要 Department  是以怎样的方式存放=>数组
    Department[] departments;
    int position = 0;//遍历的位置

    public ComputerCollegeIterator(Department[] departments) {
        this.departments = departments;
    }

    //判断是否还有下一个元素
    @Override
    public boolean hasNext() {

        if (position >= departments.length || departments[position] == null) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public Object next() {
        Department department = departments[position];
        position += 1;
        return department;
    }

    //删除的方法，默认空实现
    public void remove() {
    }
}

//系
class Department {
    private String name;
    private String desc;

    public Department(String name, String desc) {
        super();
        this.name = name;
        this.desc = desc;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}

class InfoColleageIterator implements Iterator {
    List<Department> departmentList;// 信息工程学院是以List方式存放系
    int index = -1;//索引

    public InfoColleageIterator(List<Department> departmentList) {
        this.departmentList = departmentList;
    }

    //判断list中还有没有下一个元素
    @Override
    public boolean hasNext() {
        if (index >= departmentList.size() - 1) {
            return false;
        } else {
            index += 1;
            return true;
        }
    }

    @Override
    public Object next() {
        return departmentList.get(index);
    }

    //空实现remove
    public void remove() {}
}

class InfoCollege implements College {
    List<Department> departmentList;

    public InfoCollege() {
        departmentList = new ArrayList<Department>();
        addDepartment("信息安全专业", " 信息安全专业 ");
        addDepartment("网络安全专业", " 网络安全专业 ");
        addDepartment("服务器安全专业", " 服务器安全专业 ");
    }

    @Override
    public String getName() {
        return "信息工程学院";
    }

    @Override
    public void addDepartment(String name, String desc) {
        Department department = new Department(name, desc);
        departmentList.add(department);
    }

    @Override
    public Iterator createIterator() {
        return new InfoColleageIterator(departmentList);
    }

}

class OutPutImpl {
    //学院集合
    List<College> collegeList;

    public OutPutImpl(List<College> collegeList) {
        this.collegeList = collegeList;
    }

    //遍历所有学院,然后调用print Department  输出各个学院的系
    public void printCollege() {
        //从collegeList 取出所有学院,Java 中的 List 已经实现 Iterator
        Iterator<College> iterator = collegeList.iterator();

        while (iterator.hasNext()) {
            //取出一个学院
            College college = iterator.next();
            System.out.println("===" + college.getName() + "=====");
            printDepartment(college.createIterator());//得到对应迭代器
        }
    }
    //输出 学院输出 系
    public void printDepartment(Iterator iterator) {
        while (iterator.hasNext()) {
            Department d = (Department) iterator.next();
            System.out.println(d.getName());
        }
    }
}