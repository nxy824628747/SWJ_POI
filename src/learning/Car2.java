package learning;/***   @Author Nyr*   @Date 2019/11/19 20:48*   @Description 单例模式-静态内部类方式*/public class Car2 {    private Car2(){}    private  static class InnerCar2{        private static Car2 car2=new Car2();    }    public static Car2 getCar2(){        return InnerCar2.car2;    }}