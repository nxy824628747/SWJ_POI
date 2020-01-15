package learning;

import cn.com.jit.assp.dsign.TestThread;
import cn.com.sdca.sof.Test;
import interfaces.People;
import org.springframework.jdbc.core.JdbcTemplate;

public class Bus  {
        Car2 car2=Car2.getCar2();

        public static void main(String[] args){
            Car car=Car.getInstance();
            ThreadTest t1=new ThreadTest(car);
            ThreadTest t2=new ThreadTest(car);
            ThreadTest t3=new ThreadTest(car);
            t1.start();
            t2.start();
            t3.start();
        }

}
