package 行为型模式.ObserverPattern;

import java.util.ArrayList;

/*
观察者模式：当对象间存在一对多关系时，则使用观察者模式。一个对象的状态发生改变时，则通知其他对象
应用实例：1.java中的观察者模式类
优点：观察者与被观察者抽象耦合 2.建立了一套触发机制
理解：1、拍卖的时候，拍卖师观察最高价和最低价，然后通知给其他竞买者，2、抽签的时候，观察者模式就是最佳的应用场景
 */
public class OberserverPattern {
    public static void main(String[] args) {
        Debit zhangSan = new ZhangSan();
        zhangSan.borrow(new LiSi());
        zhangSan.borrow(new WangWu());
        //一旦没钱了状态需要发生改变
        zhangSan.notifyCredits();
    }
}

interface Debit {    //贷款方接口(主题对象)
    void borrow(Credit credit);
    void notifyCredits();
}

class ZhangSan implements Debit {
    private ArrayList<Credit> allCredits = new ArrayList<>();   //添加观察者对象
    private Integer state = 0;  //1代表有钱了

    @Override
    public void borrow(Credit credit) {
        allCredits.add(credit);
    }

    @Override
    public void notifyCredits() {
        allCredits.forEach(credit -> credit.takeMoney());
    }
}

interface Credit {   //借款方接口(观察者)
    void takeMoney();
}

class LiSi implements Credit {
    @Override
    public void takeMoney() {
        System.out.println("李四拿钱了");
    }
}

class WangWu implements Credit {
    @Override
    public void takeMoney() {
        System.out.println("王五拿钱了");
    }
}
