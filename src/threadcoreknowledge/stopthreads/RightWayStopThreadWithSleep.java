package threadcoreknowledge.stopthreads;

/**
 * @ClassName RightWayStopThreadWithSleep
 * @Description 带有sleep()中断线程的写法
 * @Author Evan Wang
 * @Version 1.0.0
 * @Date 2020/3/23 21:06
 */
public class RightWayStopThreadWithSleep {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;
            try {
                while (num <= 300 && !Thread.currentThread().isInterrupted()) {
                    if (num % 100 == 0) {
                        System.out.println(num + " 是100的倍数");
                    }
                    num++;
                }
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                //响应外部中断的方式(即在休眠中响应interrupt()中断)，抛出异常
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(500);
        thread.interrupt();
    }
}
