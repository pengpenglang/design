package 行为型模式.StrategyPattern;

/*
策略模式：一个类的行为或其算法可以在运行时更改，在策略模式中我们创建表示各种策略的对象和一个随着策略对象改变而改变的context对象
应用实例：java的ThreadPoolExecutor线程池实现类的最后一个传入参数RejectedExcutionHandler需要定义执行策略实现不同的拒绝行为
优点：1.算法可以自由切换 2.避免使用多重条件判断 3.扩展性良好
缺点：1.策略类会增多 2.所有策略类都对外暴露
理解：银行根据不同的用户选择存储额度以及服务流程上存在区别
注意：在混合模式中基本不区分策略模式和状态模式的区别但是这里还是从两个角度进行剖析区别：
    1.观入点：状态模式是状态的不同接下来一系列行为都不同，而策略模式是针对某一行为的不同算法
    2.外部干涉：状态模式除了客户端指定还可能会被外部干扰导致自身发生状态改变，而策略模式只会由客户端决定执行哪个策略
 */
public class StrategyPattern {
    public static void main(String[] args) {
//        ThreadPoolExecutor threadPoolExecutor =new ThreadPoolExecutor();
    }
}
