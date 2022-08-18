package 创建型模式.SingletonPattern;

/*
单例模式是一种常用的设计模式，它的主要目的是保证一个类仅有一个实例，并提供一个访问它的全局访问点。
实现方式：1.饿汉式（在类进行加载的时候立刻进行实例化） 2.懒汉式（在第一次使用的时候进行实例化） 3.双重检查锁（在第一次使用的时候进行实例化，并且保证线程安全）
应用实例：唯一序列号、计数器等无状态问题
多个实例情况：1.分布式系统中多个JVM虚拟机的各自类加载器同时加载 2.同一个JVM的多个类加载器同时加载
优点：1.节省了系统资源 2.简化了系统的维护 3.提高了系统的可扩展性 4.提高了系统的灵活性
缺点：1.类加载的时候就实例化，会浪费内存（违背了单一职责原则） 2.如果类加载过程中出现异常，会导致类加载失败，导致程序崩溃 3.没有接口不能继承
理解：1.多个班级但是为一个班主任 2.多个打印机只有一个需要打印文件
 */
public class SingletonPattern {
    public static void main(String[] args) {

    }
}

class class1 { //饿汉式
    private class1() { //将构造方法私有化
    }

    private static class1 instance = new class1();  //类加载器直接构造

    public static class1 getInstance() {    //获取返还创建的唯一实例
        return instance;
    }
}

class class2 {  //懒汉式
    private class2() { //将构造方法私有化
    }

    private static class2 instance;

    public synchronized class2 getInstance() { //考虑多线程情况
        if (instance == null) { //类变量为空的时候才进行实例化
            instance = new class2();
        }
        return instance;
    }
}

/*
双重检查锁（Double-Checked Locking）
好处是减少了锁的粒度，提高了性能：
    1.封锁类创建器的访问，只有第一次加载类的时候才会进行实例化，后续加载类的时候不会进行实例化，提高了性能
    2.类加载器存在缓存优化（分配内存->初始化->指向刚分配的地址)，2 3步可能出现缓存重排序，通过volatile关键字强制缓存一致性（各线程之间写发生在读之前）解决
 */
class class3 { //双重检查锁（懒汉式基础上进一步优化）
    private class3() { //将构造方法私有化
    }

    private volatile static class3 instance;

    public static class3 getInstance() {
        if (instance == null) {
            synchronized (class3.class) {
                if (instance == null) {
                    instance = new class3();
                }
            }
        }
        return instance;
    }
}


