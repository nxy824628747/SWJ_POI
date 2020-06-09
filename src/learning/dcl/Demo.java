package learning.dcl;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Demo {
    SynchronizedQueue<Integer> queue = new SynchronizedQueue<Integer>(2);

    private int preRe;

    Thread putThread = new Thread(() -> {
        try {
            int i = 100;
            while (!Thread.currentThread().isInterrupted()) {
                queue.put(i);
//                Thread.sleep(100);
                i++;
            }
        } catch (Exception ie) {
            ie.printStackTrace();
        }
    });

    public void get() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                int re = queue.remove();
                System.out.println(Thread.currentThread().getName() + "  get : " + re);
            }
        } catch (Exception ie) {
            System.out.println(Thread.currentThread().getName()+" error!");
            ie.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        Demo demo = new Demo();
        demo.putThread.start();
        ExecutorService executor = Executors.newFixedThreadPool(11);
        for (int i = 0; i < 10; i++) {
            executor.execute(
                    new Runnable() {
                        @Override
                        public void run() {
                            demo.get();
                        }
                    }
            );
        }

    }
}
