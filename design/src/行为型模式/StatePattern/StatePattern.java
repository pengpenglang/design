package 行为型模式.StatePattern;

/*
状态模式：该模式中类的行为是基于它的状态改变的，在状态模式中我们创建表示各种状态的对象和一个行为随着状态对象改变而改变的对象Context
应用实例：1.Alibaba公司开发规定超过三个if-else改为状态模式
优点：1.封装了转换原则 2.枚举可能的状态在枚举状态之前考虑了所有状态种类的可能性 3.避免了状态类的使用，使用状态模式可以将状态的判断逻辑从类中剥离出来
缺点：1.增加了系统类和对象的个数(小小的牺牲) 2.状态模式的使用必然会增加系统的复杂度 3.对开闭原则支持不是很好，增加新的状态类需要修改那些负责状态转换的源代码
理解：可能是受外部影响或者自身影响发生状态变化并导致接下来一系列的行为都不同
 */
public class StatePattern {
    public static void main(String[] args) {
        Context context = new Context();
        context.changeState(new Happy());
        context.doWork();
        context.changeState(new Sad());
        context.doWork();
    }
}

abstract class State{
    abstract void doWork();
}

class Happy extends State{
    @Override
    void doWork() {
        System.out.println("Happy");
    }
}

class Angry extends State{
    @Override
    void doWork() {
        System.out.println("Angry");
    }
}

class Sad extends State{
    @Override
    void doWork() {
        System.out.println("Sad");
    }
}

class Context{
    State state = new Happy();
    public void changeState(State state){   //设置不同的状态对象该类的行为也随之发生改变
        this.state = state;
    }
    public void doWork(){
        state.doWork();
    }
}
