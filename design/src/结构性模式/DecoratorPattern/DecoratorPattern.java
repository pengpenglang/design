package 结构性模式.DecoratorPattern;

/*
装饰器模式：允许向一个现有的对象添加新的功能，同时又不改变其结构。这种类型的设计模式没有在类层面上进行修改，而是在原有类上包装(is和has的区别)，动态的给原有类添加一些新功能
应用实例：1.扩展一个类的功能 2.动态增加功能，动态撤销 3.JAVA的IO流接口，比如过滤输入流验证输入
优点：装饰类和被装饰类可以独立发展，不会相互耦合，装饰模式是继承的一个替代模式，装饰模式可以动态扩展一个实现类的功能
缺点：多层装饰比较复杂
理解：孙悟空72变但始终本质还是猴子，只是表面发生了变化
 */
public class DecoratorPattern {
}

interface Robot {
    void doSomething();
}

class FirstRobot implements Robot {
    @Override
    public void doSomething() {
        System.out.println("对话");
        System.out.println("唱歌");
    }
}

/*
一般情况下这个装饰器会被设为抽象类然后扩展出各种具体的功能装饰器类，这里只是演示就不写了
 */
class RobotDecrator implements Robot {
    private Robot robot;

    public RobotDecrator(Robot robot) {
        this.robot = robot;
    }

    @Override
    public void doSomething() {
        robot.doSomething();
    }

    public void doMoreThing() {
        System.out.println("打电话");
        System.out.println("看电影");
    }
}
