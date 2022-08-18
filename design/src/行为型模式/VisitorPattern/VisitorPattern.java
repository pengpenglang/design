package 行为型模式.VisitorPattern;

/*
访问者模式：在使用了一个访问者类改变了元素类的执行算法。通过这种方式，元素的执行算法可以随着访问者改变而改变
优点：符合单一职责原则 2.优秀的扩展性 3.灵活性
缺点：1.具体元素对访问者公布了细节违背了迪米特原则(因为发起者最终在accept中要调用访问者对自身的传入暴露了类型[反向代理、回调的感觉]) 2.需要对一个对象结构中的对象进行很多不同并且不相关的操作，而需要避免让这些操作“污染”这些对象的类也不希望在增加新操作时修改这些类
理解：实现了有发起者允许访问者进入到最终反过来访问者对发起者进行修改，类似电脑中的组件升级或者插入USB设备
 */
public class VisitorPattern {
    public static void main(String[] args) {
        ComputerPart computer = new Computer();
        computer.accept(new ComputerPartDisplayVisitor());
    }
}

/*
元素的接口
 */
interface ComputerPart {
    void accept(ComputerPartVisitor computerPartVisitor);
}

/*
创建扩展了上述类的实体类
 */
class Keyboard implements ComputerPart {
    @Override
    public void accept(ComputerPartVisitor computerPartVisitor) {
        computerPartVisitor.visit(this);
    }
}

class Monitor implements ComputerPart {
    @Override
    public void accept(ComputerPartVisitor computerPartVisitor) {
        computerPartVisitor.visit(this);
    }
}

class Mouse implements ComputerPart {
    @Override
    public void accept(ComputerPartVisitor computerPartVisitor) {
        computerPartVisitor.visit(this);
    }
}

class Computer implements ComputerPart {
    ComputerPart[] parts;

    public Computer() {
        this.parts = new ComputerPart[]{
                new Mouse(),
                new Keyboard(),
                new Monitor()
        };
    }

    @Override
    public void accept(ComputerPartVisitor computerPartVisitor) {
        for (ComputerPart part : parts) { //先访问组件
            part.accept(computerPartVisitor);
        }
        computerPartVisitor.visit(this);    //再访问自己
    }
}

/*
访问者接口
 */
interface ComputerPartVisitor {
    void visit(Keyboard keyboard);

    void visit(Monitor monitor);

    void visit(Mouse mouse);

    void visit(Computer computer);
}

/*
创建实现了上述类的实体访问者
 */
class ComputerPartDisplayVisitor implements ComputerPartVisitor {
    @Override
    public void visit(Keyboard keyboard) {
        System.out.println("Displaying Keyboard");
    }

    @Override
    public void visit(Monitor monitor) {
        System.out.println("Displaying Monitor");
    }

    @Override
    public void visit(Mouse mouse) {
        System.out.println("Displaying Mouse");
    }

    @Override
    public void visit(Computer computer) {
        System.out.println("Displaying Computer");
    }
}