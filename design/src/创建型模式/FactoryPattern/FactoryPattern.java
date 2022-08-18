package 创建型模式.FactoryPattern;

/*
抽象工厂模式：由于简单工厂违背了开闭原则（修改是封闭的，扩展是开放的），因此抽象工厂模式是一种重新设计。定义一个用于创建工厂对象的接口，让工厂子类决定实例化哪个类，使一个产品类对象的实例化延迟到工厂子类
应用实例：Collection集合接口的实现类，JDK提供的各种集合类
优点：1.提高了系统的可扩展性 2.增加了新的集合类很容易，无须修改已有系统
缺点：产品族扩展非常困难，新添某一系列的某一新产品需要既在抽象的Creator中加代码还要在具体的里面加代码
理解：某个公司销售多个系列多种产品，每个系列建立一个独立生产线
 */
public class FactoryPattern {
    public static void main(String[] args) {
        Factory factory = new HuaweiFactory();
        Phone phone = factory.createPhone();
        phone.print();
    }
}

interface Phone{
    public abstract void print();
} //产品结构和简单工厂模式一样

class iPhone implements Phone{
    @Override
    public void print() {
        System.out.println("iPhone");
    }
}
class HuaWeiPhone implements Phone{
    @Override
    public void print() {
        System.out.println("HuaWeiPhone");
    }
}

interface Factory{ //区别在于工厂模式设为超级工厂抽象类
    Phone createPhone();
}

/*
这里还可以进一步扩展，比如像上文注释写的工厂子类负责一系列产品，那么内部是简单工厂模式再根据型号创建一系列不同型号的产品(工厂模式升级之处）
iPhone抽象基类->iPhoneX、iPhone8、iPhone7
HuaWei抽象基类->HuaWeiP20、HuaWeiP10
写多了还是容易臃肿退化为简单工厂模式[说明设计模式只是一种指导思想]
*/
class AppleFactory implements Factory{  //工厂子类决定实例化哪个类
    @Override
    public Phone createPhone() {
        return new iPhone();
    }
}

class HuaweiFactory implements Factory{
    @Override
    public HuaWeiPhone createPhone() {
        return new HuaWeiPhone();
    }
}