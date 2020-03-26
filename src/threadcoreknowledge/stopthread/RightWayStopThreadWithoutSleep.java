package threadcoreknowledge.stopthread;

/**
 * @ClassName RightWayStopThreadWithoutSleep
 * @Description run()方法内没有sleep()或wait()方法时，停止线程。
 * @Author Evan Wang
 * @Version 1.0.0
 * @Date 2020/3/23 20:42
 */
public class RightWayStopThreadWithoutSleep implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadWithoutSleep());
        thread.start();
        Thread.sleep(2000);
        thread.interrupt();
    }

    @Override
    public void run() {
        int num = 0;
        //Thread.currentThread().isInterrupted() 普通情况下，用来判断线程是否被中断
        while (!Thread.currentThread().isInterrupted() && num <= Integer.MAX_VALUE / 2) {
            if (num % 10000 == 0) {
                System.out.println(num + " 是10000的倍数");
            }
            num++;
        }
        System.out.println("任务运行结束了");
    }
}
