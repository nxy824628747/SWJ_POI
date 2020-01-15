package learning;

import java.io.Serializable;

public class Car {
    //构造函数私有，禁止通过常规方式实例化
    private Car(){}
    //单例对象的引用
    static volatile Car car=null;
    //DCL获取单例对象，静态工厂方法
    static Car getInstance(){
        if(car==null){
            synchronized(Car.class){
                if(car==null){
                    car=new Car();
                }
            }
        }
        return car;
    }

    private Object readResolve() {
        return getInstance();
    }
}
