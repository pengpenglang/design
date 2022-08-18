package 行为型模式.TemplatePattern;

/*
模板模式：一个抽象类中定义了一个算法的骨架，而将一些步骤延迟到子类中。模板方法使得子类可以不改变一个算法的结构即可重定义该算法的某些特定步骤。
应用实例：1.spring中对Hibernate的支持将一些已经定好的方法封装起来(开启事务、获取Session、关闭Session等)
优点：1.封装不变部分，扩展可变部分(符合开闭原则) 2.提取公共代码便于维护 3.父类控制，子类实现
缺点：每个不同的实现都需要一个子类来实现，导师类的数量增加，导致系统变得很庞大(小小的牺牲)
理解：比如做饭框架都有配菜和掌勺两个步骤，但是不同的菜模板相同但具体步骤的内容不同
注意：策略模式更注重的是某一问题不同的算法选择(步骤框架不同)，模板模式更注重某一算法具体步骤的实现内容
 */
public class TemplatePattern {
    public static void main(String[] args) {
        Cooking bbq = new BBQ();
        Cooking soup = new Soup();
        bbq.cook();
        soup.cook();
    }
}


abstract class Cooking { /*模板抽象父类，定义步骤框架*/
    protected abstract void step1();

    protected abstract void step2();

    public void cook() {
        step1();
        step2();
    }
}

class BBQ extends Cooking {
    @Override
    protected void step1() {
        System.out.println("起锅烧油");
    }

    @Override
    protected void step2() {
        System.out.println("穿肉烤至金黄酥脆");
    }
}

class Soup extends Cooking {
    @Override
    protected void step1() {
        System.out.println("洗净锅放水");
    }

    @Override
    protected void step2() {
        System.out.println("下面煮汤");
    }
}