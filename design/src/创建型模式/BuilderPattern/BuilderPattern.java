package 创建型模式.BuilderPattern;

/*
建造者模式：使用多个简单的Builder对象一步步构建出复杂的目的对象，它提供了一种创建对象的最佳方式
应用实例：方法链方式创建(可以少写很多目的类的构造函数)lombok库提供了Builder模式
理解：指定参数传参也可以做到构建复杂对象，这样写相当于构建过程是一个链式的过程
优点：1.建造者独立易于扩展 2.便于控制细节风险
缺点：产品必须有共同点 2.如果内部变化复杂会产生很多的建造类
 */
public class BuilderPattern {
    public static void main(String[] args) {
        House house = new House.Builder().setDoor(new Door()).setWindow(new Window()).setWall(new Wall()).build();
    }
}

class Window {
}

class Door {
}

class Wall {
}

class House {
    private Window window;
    private Door door;
    private Wall wall;

    /*用生成器类来完成自身构造函数，本身只需要一个构造函数*/
    public House(Builder builder) {
        this.window = builder.window;
        this.door = builder.door;
        this.wall = builder.wall;
    }

    static final class Builder { /*内部设置一个静态(常量：主要是防止被继承和多态)类通过不同函数完成不同成员的创建*/
        private Window window;
        private Door door;
        private Wall wall;

        /*
        每个set成员变量函数最终返回的还是自己这个Builder对象
         */
        public Builder setWindow(Window window) {
            this.window = window;
            return this;
        }

        public Builder setDoor(Door door) {
            this.door = door;
            return this;
        }

        public Builder setWall(Wall wall) {
            this.wall = wall;
            return this;
        }

        /*成员变量都创建完成然后生成器类调用House传入Builder类的构造函数完成创建*/
        public House build() {
            return new House(this);
        }
    }
}