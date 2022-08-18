package 行为型模式.IteratorPattern;

/*
迭代器对象：可以顺序遍历集合的对象并且不需要知道集合对象的底层表示(面向接口编程)
应用实例：java中的各种集合类都把游走的责任交给迭代器
优点：1.它支持以不同的方式遍历一个集合对象 2.迭代器简化了聚合类 3.在同一个集合上可以有多个遍历 4.在迭代器模式中增加新的聚合类和迭代器类很方便
缺点：由于迭代器模式将存储数据和遍历数据的职责分离，增加新的聚合类需要对应增加新的迭代器类，类的个数成对增加一定程序上增加了系统的复杂度
理解：迭代器对象就是分离了集合对象的遍历行为，抽象出一个迭代器来负责，这样既可以做到不暴露集合对象的内部细节，又可以做到不同的遍历方式
 */
public class IteratorPattern {
    public static void main(String[] args) {
        NameRepository nameRepository = new NameRepository();
        /*
        使用者尽管创建一个迭代器对象循环调用就可以遍历所有元素了了，他不关心底层到底如何进行遍历访问
         */
        for (Iterator iterator = nameRepository.getIterator(); iterator.hasNext(); ) {
            System.out.println(iterator.next());
        }
    }
}

interface Iterator { /*迭代器接口*/
    public boolean hasNext();

    public Object next();
}

interface Container {   /*容器接口*/
    public Iterator getIterator();
}

class NameRepository implements Container {
    private String[] names = {"Robert", "John", "Julie", "Lora"};

    @Override
    public Iterator getIterator() { /*具体迭代方法交给自己内部的迭代器类来实现*/
        return new NameIterator();
    }

    /*
    这里也有抽象工厂模式的影子，在客户端使用时用的是Iterator创建迭代器，但是具体迭代器类型则是由每个容器类决定的
     */
    private class NameIterator implements Iterator {
        int index;

        @Override
        public boolean hasNext() {
            if (index < names.length) {
                return true;
            }
            return false;
        }

        @Override
        public Object next() {
            if (this.hasNext()) {
                return names[index++];
            }
            return null;
        }
    }
}