package learning.decorator;public class OpenFoglight extends CarDecorator {    OpenFoglight(Car car){        super(car);    }    @Override    public void run(){        checkUp();        System.out.println("开启雾灯");        car.run();    }}