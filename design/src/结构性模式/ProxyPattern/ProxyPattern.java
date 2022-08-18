package 结构性模式.ProxyPattern;

/*
代理模式：一个类代表另一个类的功能，相当于创建了一个具有现有对象的对象以便对外界提供功能接口
应用实例：1.windows的快捷方式 2.装饰器模式、状态模式其实都多多少少有代理模式的影子
优点：1.代理模式可以在不修改原有类的情况下得到扩展 2.代理模式可以保护现有的类 3.高扩展性
缺点：1.多加一层就会导致请求的处理速度变慢 2.实现代理模式需要额外的工作，有时会需要很大的开发工作量
注意：代理模式是基本设计模式思想之一，很多设计模式思想都是基于代理模式的，注意它们设计初衷的不同
 */
public class ProxyPattern {
    public static void main(String[] args) throws Exception {
        new RealSubjectProxy().doWork();
    }
}

interface Subject {
    void doWork();
}

class RealSubject implements Subject { //被代理类
    @Override
    public void doWork() {
        System.out.println("Hello World");
    }
}

class RealSubjectProxy implements Subject { //代理类
    private RealSubject realSubject;

    //可能不需要传入参数利用类加载器加载类[目前实现有问题]
    public RealSubjectProxy() {
        try {
            this.realSubject = (RealSubject) this.getClass().getClassLoader().loadClass("design.ProxyPattern.RealSubject").getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

//[状态模式]这样写就需要main传入对象成为了状态模式
//    public RealSubjextProxy(RealSubject subject){
//        this.realSubject = subject;
//    }

    public void connect() {
        System.out.println("建立连接");
    }

    public void log() {
        System.out.println("断开连接");
    }

    @Override
    public void doWork() {
        connect();
        realSubject.doWork();
        log();
    }

//[装饰器模式]扩展其他功能就成为了装饰器模式
}