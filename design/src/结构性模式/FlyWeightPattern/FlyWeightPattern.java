package 结构性模式.FlyWeightPattern;

import java.util.*;

/*
享元模式：在程序运行时创建大量相同的对象，这些对象可以共享，从而减少创建对象的数量，提高程序的性能。
    客户端：存储一些使用者自身信息，每次从享元池(享元工厂)中获取享元对象时，都会把这些信息传递给享元对象
    享元池：客户端获取享元对象的唯一入口，一般设计为单例模式
    享元对象：分为内部状态和外部状态，内部状态记录一些自身物品的相关信息，外部状态可以将客户端传入的用户信息传递给内部状态，从而实现对象的复用
应用实例：常量池、线程池、字符串池以及数据库的连接池等
优点：1.减少了创建对象的数量，提高了程序的性能 2.减少了内存的开销，提高了程序的性能。
缺点：1.结构与实现都相对复杂，它的实现需要依赖系统本身的复杂性，这导致了系统的不稳定
理解：就像共享单车一样，共享单车可以被多个人使用，使用者通过APP来获取单车占为己有
 */
public class FlyWeightPattern {
    public static void main(String[] args) {
        /*模拟的客户端*/
        BikeFlyWeight bike1 = BikeFlyWeightFactory.getFactory().getBike();
        bike1.ride("张三");
        BikeFlyWeight bike2 = BikeFlyWeightFactory.getFactory().getBike();
        bike2.ride("李四");
        /*
        如果享元池不能自行扩充，那么前面两个人必须还一辆后王五才能用上单车
         */
        BikeFlyWeight bike3 = BikeFlyWeightFactory.getFactory().getBike();
        bike3.ride("王五");
    }
}

abstract class BikeFlyWeight {   //享元抽象类
    //内部状态
    protected Integer state = 0; //0被占用，1空闲

    //外部状态
    public abstract void ride(String userName);    //记录客户端信息

    public abstract void back();   //归还

    public Integer getState() {  //供享元池获取当前对象内部状态的接口
        return state;
    }

}

class MoBikeFlyWeight extends BikeFlyWeight {    //某品牌单车类
    private String bikeId;  //单车自身的编号信息，享元池创建的时候可以设置

    public MoBikeFlyWeight(String bikeId) {
        this.bikeId = bikeId;
    }

    @Override
    public void ride(String userName) {    //记录客户端信息
        state = 1;
        System.out.println(bikeId + "被" + userName + "使用");
    }

    @Override
    public void back() {
        state = 0;
    }
}

/*
饿汉式单例模式实现的享元池
 */
class BikeFlyWeightFactory {
    private int idx = 0;
    private static BikeFlyWeightFactory factory = new BikeFlyWeightFactory();
    private Set<BikeFlyWeight> pool = new HashSet<>();  //一般享元对象用HashTable存储

    private BikeFlyWeightFactory() {    //起初池内有2个单车对象
        for (int i = 0; i < 2; i++) {
            pool.add(new MoBikeFlyWeight("单车" + (++idx) + "号"));
        }
    }

    public static BikeFlyWeightFactory getFactory() {
        return factory;
    }

    public BikeFlyWeight getBike() {
        boolean hasBike = false;
        for (BikeFlyWeight bike : pool)
            if (bike.getState() == 0) {
                hasBike = true;
                return bike;
            }
        if (!hasBike) { //遇到线程池不够情况可以考虑扩充
            MoBikeFlyWeight newBike = new MoBikeFlyWeight("单车" + (++idx) + "号");
            pool.add(newBike);
            return newBike;
        }
        return null;
    }
}