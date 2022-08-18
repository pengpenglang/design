package 行为型模式.MementoPattern;

import java.util.Stack;

/*
备忘录模式：保存一个对象的某个状态，以便在适当的时候恢复对象。一般生成一个Memento类，用来保存对象的所有成员变量的值(它可以实现一些存储信息获取的接口)
应用实例：1.游戏存档 2.数据库的事务回滚
优点：1.给用户提供了一种可以恢复的机制 2.实现了信息的封装使得用户必须要关心状态的保存细节
缺点：消耗资源，如果类的成员变量很多会占用很大的资源
*/
public class MementoPattern {
    public static void main(String[] args) {
        History history = new History();
        Document document = new Document();
        /*每次变化选择是否保存放入回退栈*/
        document.change("abc");
        history.push(document.save());

        document.change("def");
        history.push(document.save());

        document.change("ghi");
        history.push(document.save());

        document.change("jkl");
        document.resume(history.getLastVersion());
        document.print();

        document.resume(history.getLastVersion());
        document.print();
    }
}

class Document {
    private String content; //需要备份的数据

    public void change(String newContent) {
        content = newContent;
    }

    public void print() {
        System.out.println(content);
    }

    /*使用备忘录类进行保存和撤销，就是将自身公有私有的成员都传给BackUp进行构造保存*/
    public BackUp save() {
        return new BackUp(content);
    }

    public void resume(BackUp backUp) {
        content = backUp.content;
    }
}

/*
备忘录接口来存储一些备份的时间、用户等信息
 */
interface Memento {

}

/*
备忘录类应当构造函数用到所有的成员变量
 */
class BackUp implements Memento {
    String content;

    public BackUp(String content) {
        this.content = content;
    }
}

class History {
    Stack<BackUp> backUpStack = new Stack<>();

    public void push(BackUp backUp) {
        backUpStack.push(backUp);
    }

    public BackUp getLastVersion() {
        return backUpStack.pop();
    }
}