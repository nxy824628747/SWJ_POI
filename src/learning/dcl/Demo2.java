package learning.dcl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo2 {
    public static void main(String[] args){
        final Buf buf = new Buf();
        ExecutorService es = Executors.newFixedThreadPool(11);
        for (int i = 0; i < 1; i++)
            es.execute(new Runnable() {

                @Override
                public void run() {
                    while (true ) {
                        try {
                            buf.put(1);
                            Thread.sleep(20);
                        }
                        catch (InterruptedException e) {
                            e.printStackTrace();
                            break;
                        }
                    }
                }
            });
        for (int i = 0; i < 10; i++) {
            es.execute(new Runnable() {

                @Override
                public void run() {
                    while (true ) {
                        try {
                            System.out.println(Thread.currentThread().getName()+" : get");
                            buf.get();
                            Thread.sleep(10);
                        }
                        catch (InterruptedException e) {
                            e.printStackTrace();
                            break;
                        }
                    }
                }
            });
        }

    }
}
