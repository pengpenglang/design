package 结构性模式.FacadePattern;

/*
外观模式：隐藏系统的复杂性，并向客户端提供了一个客户端可以访问系统的接口。它主要目的是向现有的系统添加了一个类来隐藏系统的复杂性
应用实例：1.java的三层开发模式 2.课程管理系统等等中间层
优点：1.减少了系统的相互依赖 2.提高了安全性预防低水平人员带来的风险 3.子系统相对独立
缺点：违背了开闭原则，如果需要修改东西很麻烦，继承重写都不合适
理解：医院看病不需要自己去挂号、门诊、划价、取药，全部交给接待人员处理
 */
public class FacadePattern {
    public static void main(String[] args) {
        System.out.println(new Facade().prove());
    }
}

/*
三个分支事件
 */
class SubFlow1 {
    boolean isTrue() {
        return true;
    }
}

class SubFlow2 {
    boolean isTrue() {
        return true;
    }
}

class SubTrue3 {
    boolean isTrue() {
        return true;
    }
}

/*
外观类，相当于政务服务人员来帮助客户进行材料的整理收集
 */
class Facade {
    SubFlow1 subFlow1 = new SubFlow1();
    SubFlow2 subFlow2 = new SubFlow2();
    SubTrue3 subTrue3 = new SubTrue3();

    public boolean prove() {
        return subFlow1.isTrue() && subFlow2.isTrue() && subTrue3.isTrue();
    }
}
