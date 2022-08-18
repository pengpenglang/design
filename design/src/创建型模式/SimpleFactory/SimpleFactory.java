package 创建型模式.SimpleFactory;

/*
简单工厂模式：又称为静态工厂模式，在简单工厂模式中可以根据参数的不同返回不同类的实例，即专门定义一个类用来负责创建其他类的实例（这些类都是某个父类的基类）
应用实例：1.JDK中的DateFormat 2.Spring中的MessageFormat
优点：客户端程序员不需要关心具体对象的创建过程，而是通过工厂方法，将对象创建的具体步骤屏蔽给客户端，实现了创建与使用的分离
缺点：每增加一种产品类型都需要增加一个具体类和对象实例，使得系统中类的个数成倍增加，在一定程度上增加了系统的复杂度(违反了开闭原则)
理解：日志记录器用户可以选择记录到本地硬盘、系统时间、远程服务器等多种方式，而日志记录器只需要实现记录方法就可以了，不需要关心具体的记录方式
 */
public class SimpleFactory {
    public static Product createProduct(String type) {
        if (type.equals("A"))
            return new ProductA();
        else
            return new ProductB();
    }

    public static void main(String[] args) {
        Product product = createProduct("A");
        product.print();
    }
}

abstract class Product {
    public abstract void print();
}

class ProductA extends Product {
    @Override
    public void print() {
        System.out.println("ProductA");
    }
}


class ProductB extends Product {
    @Override
    public void print() {
        System.out.println("ProductB");
    }
}

