package 结构性模式.AdapterPattern;

/*
适配器模式：通过对象的适配，将一个类的接口转换成客户希望的另外一个接口，从而使原本由于接口不兼容而不能一起工作的那些类可以一起工作
两种实现方法：1.继承(注意java至多继承一个抽象适配器类) 2.实现接口
应用实例：1.linux上运行windows程序 2.java中的jdbc
优点：1.可以解决类的适配问题 2.可以降低耦合度 3.节省系统开发成本
缺点：1.过多使用导致系统凌乱(考虑重构)
理解：1.新闻联播的手语主持人帮助聋哑人“听”到新闻 2.用电器的充电接口转换器
 */
public class AdapterPattern {
    public static void main(String[] args) {

    }
}

class Speaker {
    public String speak() {
        return "China NO.1";
    }
}

/*
方法一：继承
 */
class AdapterA extends Speaker { //再添加一个功能
    public String translate() {
        String result = speak();
        //翻译成手语
        return result;
    }
}

/*
方法二：实现接口
注意和装饰器的区别：实现的目的不同(适配器是功能兼容，装饰器是功能扩展)
*/
interface Translator {
    public String translate();
}

class AdapterB implements Translator {
    private Speaker speaker;

    public AdapterB(Speaker speaker) {
        this.speaker = speaker;
    }

    @Override
    public String translate() {
        String result = speaker.speak();
        //翻译成手语
        return result;
    }
}