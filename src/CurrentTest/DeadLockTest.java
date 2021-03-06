package CurrentTest;

public class DeadLockTest {

    static Object aaa=new Object();
    public Object test=new Object();
    public Object test2=new Object();
    public Object fromGitHub=new Object();
    static Object bbb=new Object();
    public static void main(String[] args){
        new Thread1().start();
        new Thread2().start();
    }

    public static class Thread1 extends Thread{
        @Override
        public void run() {
            for (; ; ) {
                synchronized (aaa) {
                    synchronized (bbb) {
                        System.out.println("Thread1 get A and B");
                    }
                }
            }
        }
    }

    public static class Thread2 extends Thread{
        @Override
        public void run() {
            for (; ; ) {
                synchronized (bbb) {
                    synchronized (aaa) {
                        System.out.println("Thread2 get B and A");
                    }
                }
            }
        }
    }
}
