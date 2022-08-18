package 行为型模式.MediatorPattern;

import java.util.List;
import java.util.ArrayList;

/*
中介者模式：降低多个对象和类之间的通信复杂性，这种模式提供了一个中介类，该类通常处理不同类之间的通信，并支持松耦合，使代码易于维护
应用实例：1.MVC框架中的Controller就是Model和View的中介者 2.同理MVVM中的ViewModel就是Model和View的中介者
优点：1.降低了类的复杂度由一对多变成了多对一 2.各类之间的解耦 3.符合迪米特法则(一个实体类尽量少的与其他实体之间发生相互作用使得系统功能模块相对独立)
缺点：中介者会庞大逐渐变得难以维护
理解：就是中央削藩集权——P2P变C/S，网状结构变星状结构。生活中婚姻中介、房地产中介都是帮助我们快速实现快速交流从而匹配
- 和观察者模式、外观模式都是一对多，但是前两者都是一个结点和其他多个节点有单向关系
 */
public class MediatorPattern {
    public static void main(String[] args) {
        MarriageAgency marriageAgency = new MarriageAgencyImpl();
        Person Giao桑 = new Person("Giao桑", 18, Sex.MALE, 18, marriageAgency);
        Person 迪丽热巴 = new Person("迪丽热巴", 25, Sex.FEMALE, 18, marriageAgency);
        Person 郭老师 = new Person("郭老师", 18, Sex.FEMALE, 18, marriageAgency);
        marriageAgency.register(Giao桑);
        marriageAgency.register(迪丽热巴);
        marriageAgency.register(郭老师);
        marriageAgency.pair(Giao桑);
    }
}

enum Sex {
    MALE, FEMALE
}

/*
婚介所代理接口
 */
interface MarriageAgency {
    void pair(Person person);   //为person配对

    void register(Person person);   //注册会员
}

/*
会员，其中需要记住谁是媒人
 */
class Person {
    String name;
    int age;
    Sex sex;
    int requestAge;
    MarriageAgency agency;

    public Person(String name, int age, Sex sex, int requestAge, MarriageAgency agency) {
        this.name = name;
        this.age = age;
        this.sex = sex;
        this.requestAge = requestAge;
        this.agency = agency;
    }
}

/*
媒人，帮助进行记录和匹配
 */
class MarriageAgencyImpl implements MarriageAgency {
    List<Person> people = new ArrayList<>(); //存储会员

    @Override
    public void register(Person person) {
        people.add(person);
    }

    @Override
    public void pair(Person person) {
        for (Person p : people)
            if (p.age == person.requestAge && p.sex != person.sex) {
                System.out.println("将" + person.name + "与" + p.name + "送入房间");
                return;
            }
    }
}