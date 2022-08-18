package 创建型模式.PrototypePattern;

/*
原型模式：用于创建重复的对象，同时又能保证性能，这种模式一般是实现一个用于创建当前对象的原型接口。当直接创建对象的代价比较大时，允许clone的类就可以提供这个方法
应用实例：1.java中的Object clone()方法 2.细胞分裂
优点：一个对象需要在一个高代价的db操作后创建，我们可以缓存该对象在下一个请求是返回它的clone减少创建查询的代价
缺点：如果原型对象的构造函数复杂，那么clone的时候就会比较麻烦
理解：自己外部克隆对象无法赋值其内部私有成员，而类实现这个内部的clone原型接口保证了可以利用自身直接完美拷贝
 */
public class PrototypePattern {
    public static void main(String[] args) {
        Plane plane = new Plane();
        Plane otherPlane = (Plane) plane.clone();
        System.out.println(plane == otherPlane); /*地址不同说明不是一个对象*/
        System.out.println(plane.equals(otherPlane)); /*值相同说明实现了clone*/
    }
}

interface Prototype { /*克隆接口*/
    Object clone();
}

class Plane implements Prototype {
    private String name;
    private String type;

    public Plane() {
        name = "Name" + Math.random();
        type = "Type" + Math.random();
    }

    public Plane(Plane plane) {
        this.name = plane.name;
        this.type = plane.type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    @Override
    public Object clone() {
        return new Plane(this);
    }

    @Override
    public boolean equals(Object obj) {
        return this.name.equals(((Plane) obj).name) && this.type.equals(((Plane) obj).type);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}