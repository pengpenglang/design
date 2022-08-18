package 行为型模式.ChainRespPattern;

/*
责任链模式：它让多个处理器都有机会处理该请求，知道某个处理成功为止。责任链模式把多个处理器串成链，然后让请求在链上传递
应用实例：1.JS中的事件冒泡 2.JAVA中的异常处理 3.JSP Severlet的Filter
优点：1.对请求的发送者和接受者进行了解耦 2.简化了对象不需要知道链的结构 3.系统更加灵活 4.增加新的请求处理类很灵活
缺点：1.不能保证请求一定被处理 2.系统更加复杂，性能受到一定的影响 3.debug不方便可能会陷入循环递归
理解：相当于不同程度的批假可能需要逐个上级领导确定直到最高级领导可以批准，理解成按照权值大小将不同的对象串成链表
 */
public class ChainRespPattern {
    public static void main(String[] args) {
        Handler leader = new Leader();
        Handler boss = new Boss();
        leader.setNextHandler(boss);
        leader.process(5);
        leader.process(15);
    }
}

abstract class Handler { //责任链上处理者抽象类
    protected Handler nextHandler;

    public void setNextHandler(Handler nextHandler) {
        this.nextHandler = nextHandler;
    }

    public abstract void process(Integer info);
}

class Leader extends Handler {
    @Override
    public void process(Integer info) {
        if (info >= 1 && info <= 10) //1~10批准
            System.out.println("Leader批准");
        else //否则交给下一个责任链上的处理者
            nextHandler.process(info);
    }
}

class Boss extends Handler {
    @Override
    public void process(Integer info) {
        if (info >= 11 && info <= 20)
            System.out.println("Boss批准");
        else
            nextHandler.process(info);
    }
}