package 结构性模式.CompositePattern;
import java.util.ArrayList;
import java.util.List;
/*
组合模式：又称为部分整体模式，是用于把一组相似的对象当作一个单一的对象。组合模式依据树形结构来组合对象，用来表示部分以及整体层次。组合模式被用于把实体对象组合成树形结构以表示"部分-整体"的树形结构
应用实例：1.java awt和swing的组合结构；2.C++的多级继承
优点：1.高层模块调用简单 2.符合开闭原则，节点添加自由发生结构变化不用修改现有代码
缺点：使用组合模式其叶子节点和树枝的声明都是实现类而不是接口违反了依赖倒置原则(但是却符合开闭原则，神奇！我认为一般继承是is但是这里是has所以修改也不影响后续)
理解：算术表达式包括操作数、操作符和另一个操作数。其中另一个操作数也可以是另一个算术表达式
 */
public class CompositePattern {
    public static void main(String[] args) {
        Employee CEO = new Employee("张三", "CEO", 30000);
        Employee headSales = new Employee("李四", "headSales", 20000);
        Employee headMarketing = new Employee("王五", "headMarketing", 20000);
        Employee clerk1 = new Employee("赵六", "clerk", 10000);
        Employee clerk2 = new Employee("钱七", "clerk", 10000);
        Employee salesExecutive1 = new Employee("孙八", "salesExecutive", 10000);
        Employee salesExecutive2 = new Employee("周九", "salesExecutive", 10000);
        CEO.add(headSales);
        CEO.add(headMarketing);
        headSales.add(salesExecutive1);
        headSales.add(salesExecutive2);
        headMarketing.add(clerk1);
        headMarketing.add(clerk2);
        /*
        |-CEO
            |-headSales
            |    |-salesExecutive1
            |    |-salesExecutive2
            |-headMarketing
                 |-clerk1
                 |-clerk2
         */
        //打印结构
        System.out.println(CEO);
        for (Employee headEmployee : CEO.getSubordinates()) {
            System.out.println(headEmployee);
            for (Employee employee : headEmployee.getSubordinates()) {
                System.out.println(employee);
            }
        }
    }
}

class Employee{
    private String name;
    private String dept;
    private int salary;
    private List<Employee> subordinates;

    public Employee(String name,String dept,int sal){
        this.name = name;
        this.dept = dept;
        this.salary = sal;
        subordinates = new ArrayList<Employee>();
    }

    public void add(Employee e){
        subordinates.add(e);
    }

    public void remove(Employee e){
        subordinates.remove(e);
    }

    public List<Employee> getSubordinates(){
        return subordinates;
    }

    public String toString(){
        return ("Employee :[ Name : " + name + ", dept : " + dept + ", salary :" + salary + "]");
    }
}

