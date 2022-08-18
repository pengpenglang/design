package 行为型模式.CommandPattern;

/*
命令模式：数据驱动的设计模式，请求以命令的形式包裹在对象中并传给调用对象。调用对象寻找可以处理该命令的合适的对象，并把该命令传给相应的对象，该对象执行命令
应用实例：1.ctrl+c和APP中的一键赋值按钮都申请的拷贝指令 2.Qt开发中槽函数实现多个button可以使用任意功能指令 3.适用于需要命令撤销和恢复场景
优点：1.降低了系统的耦合度和重复性(满足开闭原则) 2.新的命令可以很容易的加到系统中 3.可以进行多种组合实现更复杂的操作
缺点：使用命令模式可能会导致某些系统有过多的具体命令类
理解：和享元模式有异曲同工之处，享元模式是物理中实现对有数量限制的物品共享，而命令模式是逻辑中实现对无数量限制的功能共享
考虑一下情景：文本框存储文字，按钮点击显示文字，那么可以通过考虑设置一个命令has文本框组件，然后提供显示文字指令并将其绑定到按钮GUI上
*/
public class CommandPattern {
    public static void main(String[] args) {
        TextBox textBox = new TextBox();    //创建文本框
        SaveButton saveButton = new SaveButton();   //创建按钮
        PrintCommand printCommand = new PrintCommand(textBox); //创建指令并传入组件
        saveButton.bindCommand(printCommand); //绑定按钮和指令
        /*接下来每次可以执行文本框修改，按钮打印*/
        textBox.setContext("Hello World"); //设置文本框的内容
        saveButton.doPrint(); //执行按钮的指令
        /*这样写的好处很明显了：如果多个地方需要这个功能，那么我们创建好三个对象然后绑定好就可以了无序每次按钮重复逻辑代码*/
    }
}

/*
[组件]文本框
 */
class TextBox{
    private String context;
    public String getContext(){
        return context;
    }
    public void setContext(String context) {
        this.context = context;
    }
}

/*
[GUI层]保存按钮
 */
class SaveButton{
    private Command command;

    public void bindCommand (Command command){
        this.command = command;
    }
    public void doPrint(){  /*自身并没有实际功能，全靠指令功能(感觉像是代理模式)*/
        if(command == null)
            throw new RuntimeException("设备初始化失败");
        command.execute();
    }
}

/*
[业务逻辑层]打印服务
 */
class PrintService{
    public void print(String text){
        System.out.println(text);
    }
}

/*
命令抽象类
 */
interface Command {
    void execute();
}

/*
具体命令，依赖于业务逻辑方
 */
class PrintCommand implements Command{
    private PrintService serviceProvider = new PrintService();
    private TextBox box;    /*内部拥有文本框组件(感觉很像是装饰器模式)*/

    public PrintCommand(TextBox box){
        this.box = box;
    }

    @Override
    public void execute() { /*打印指令就是直接调用文本框提供的获取文字接口并打印*/
        serviceProvider.print(box.getContext());
    }
}
