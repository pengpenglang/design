package 结构性模式.BridgePattern;

/*
桥接模式：用于把抽象化和实现化解耦，使得二者可以独立变化，通过提供抽象化和实现化的关联实现
应用实例：1.数据库的连接 2.文件的读写
优点：1.抽象和实现的分离 2.优秀的扩展能力 3.实现细节对客户透明
缺点：桥接模式引入增加了系统的理解和设计难度，由于聚合关联关系建立在抽象层，要求开发者针对抽象进行设计与编码，对于抽象的设计没有独立性
理解：
- 和命令模式很像(将指令和执行指令的对象分开)，我认为命令模式主要强调分离操作方法和操作对象，而桥接模式是对于重复的操作剥离出实体并和逻辑提供关联组合
- 和策略模式更像(都是传入一个对象实现不同行为)，从类图中不难发现策略模式侧重行为，桥接模式侧重结构，桥接模式不仅implementor有变化，Abstraction同样可以变化，策略模式强调抽象接口提供的算法是无状态的无数据的但是桥接模式的Implementor是基于有状态的高层次操作
想象这样一个场景：画画分为画什么图形和用什么颜色画图形，假设画什么是抽象类，用什么颜色画图形是行为接口
 */
public class BridgePattern {
    public static void main(String[] args) {
        Shape redCircle = new Circle(100, 100, 10, new RedCircle());
        Shape greenCircle = new Circle(100, 100, 10, new GreenCircle());

        redCircle.draw();
        greenCircle.draw();
    }
}

/*
桥接实现接口
 */
interface DrawAPI {
    public void drawCircle(int radius, int x, int y);
}

/*
实体桥接实现类
 */
class RedCircle implements DrawAPI {
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing Circle[ color: red, radius: " + radius + ", x: " + x + ", " + y + "]");
    }
}

class GreenCircle implements DrawAPI {
    public void drawCircle(int radius, int x, int y) {
        System.out.println("Drawing Circle[ color: green, radius: " + radius + ", x: " + x + ", " + y + "]");
    }
}

/*
DrawAPI接口创建抽象类
 */
abstract class Shape {
    protected DrawAPI drawAPI;

    protected Shape(DrawAPI drawAPI) {
        this.drawAPI = drawAPI;
    }

    public abstract void draw();
}

/*
实现了Shape抽象类的实体类
 */
class Circle extends Shape {
    private int x, y, radius;

    public Circle(int x, int y, int radius, DrawAPI drawAPI) {
        super(drawAPI);
        this.x = x;
        this.y = y;
        this.radius = radius;
    }

    @Override
    public void draw() {
        drawAPI.drawCircle(radius, x, y);
    }
}