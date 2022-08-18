package 行为型模式.InterpreterPattern;

/*
解释器模式：提供了评估语言的语法和表达式的方式，这种模式实现一个表达式的接口，该接口解释一个上下文
应用实例：1.SQL解析 2.编译器 3.符号处理引擎
优点：1.可扩展性高 2.增加了新的解表达式的方式 3.易于实现简单文法
缺点：1.可利用场景比较少 2.对于复杂的文法维护比较困难 3.解释器模式会引起类膨胀 4.解释器模式采用递归调用方式
理解：首先定义好计算规则类，然后利用计算规则类互相调用设定表达式，最后传入参数就可以计算出结果了
 */
public class InterpreterPattern {
    public static void main(String[] args) {
        Expression isMale = getMaleExpression();
        Expression isMarriedWoman = getMarriedWomanExpression();

        System.out.println("John is male?" + isMale.interpret("John"));
        System.out.println("Julie is a married women?" + isMarriedWoman.interpret("Married Julie"));
    }

    //使用Expression类来创建规则并且解析
    public static Expression getMaleExpression() {
        //规则：Robert 和 John是男性
        Expression robert = new TerminalExpression("Robert");
        Expression john = new TerminalExpression("John");
        return new OrExpression(robert, john);
    }

    //规则：Julie是一个已婚的女性
    public static Expression getMarriedWomanExpression() {
        Expression julie = new TerminalExpression("Julie");
        Expression married = new TerminalExpression("Married");
        return new AndExpression(julie, married);
    }
}

/*
创建一个表达式类
 */
interface Expression {
    public boolean interpret(String context);
}

class TerminalExpression implements Expression {
    private String data;

    public TerminalExpression(String data) {
        this.data = data;
    }

    @Override
    public boolean interpret(String context) {
        if (context.contains(data)) {
            return true;
        }
        return false;
    }
}

class OrExpression implements Expression {
    private Expression expr1 = null;
    private Expression expr2 = null;

    public OrExpression(Expression expr1, Expression expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    @Override
    public boolean interpret(String context) {
        return expr1.interpret(context) || expr2.interpret(context);
    }
}

class AndExpression implements Expression {
    private Expression expr1 = null;
    private Expression expr2 = null;

    public AndExpression(Expression expr1, Expression expr2) {
        this.expr1 = expr1;
        this.expr2 = expr2;
    }

    @Override
    public boolean interpret(String context) {
        return expr1.interpret(context) && expr2.interpret(context);
    }
}

