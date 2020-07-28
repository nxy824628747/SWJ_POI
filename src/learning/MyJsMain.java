package learning;

import java.util.Stack;

public class MyJsMain {
    //主任务栈
    private Stack<Runnable> mainStack = new Stack<Runnable>();
    //回调任务栈
    private Stack<Runnable> callBackStack = new Stack<Runnable>();
    //回调任务栈是主线程与其它线程共享的，操作回调任务栈的锁
    private Object callBackLock = new Object();
    //主任务栈锁
    private Object mainLock = new Object();

    //解释器线程向主任务栈压任务
    public void pushMainTask(Runnable run) {
        if(run == null){return;}
        synchronized (mainLock) {
            mainStack.push(run);
        }
    }

    //主线程从主任务栈取任务
    private Runnable getMainTask(){
        synchronized (mainLock){
            if(!mainStack.empty()){
                return mainStack.pop();
            }
        }
        return null;
    }

    //是否有主任务
    private boolean haveMainTask(){
        synchronized (mainLock){
            return !mainStack.empty();
        }
    }

    //暴露给其它线程，入定时器或者进行 ajax 请求的线程，执行完后将回调函数及执行结果压入回调栈
    public void pushCallBackTask(Runnable run) {
        if(run == null){return;}
        synchronized (callBackLock) {
            callBackStack.push(run);
        }
    }

    //主线程获取回调任务
    private Runnable getCallBackTask() {
        synchronized (callBackLock) {
            if (!callBackStack.empty()) {
                return callBackStack.pop();
            }
        }
        return null;
    }

    //是否有回调任务
    private boolean haveCallBackTask(){
        synchronized (callBackLock){
            return !callBackStack.empty();
        }
    }

    //主线程
    public static void main(String args) {
        MyJsMain myJsMain = new MyJsMain();
        while (!Thread.currentThread().isInterrupted()) {
            // 执行主线程中的任务
            while (myJsMain.haveMainTask()) {
                Runnable run = myJsMain.mainStack.pop();
                if(run!=null){
                    run.run();
                }
            }
            //执行回调线程中的任务
            while (myJsMain.haveCallBackTask()) {
                Runnable run = myJsMain.getCallBackTask();
                if(run!=null){
                    run.run();
                }
            }
        }
    }

}
