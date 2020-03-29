package threadcoreknowledge.stopthread;

/**
 * @ClassName RightWayStopThreadWithSleepEveryLoop
 * @Description 如果在执行过程中，每次循环都会sleep()或wait()等方法,
 * 不需要每次迭代(即循环)都检查是否已中断。
 * @Author Evan Wang
 * @Version 1.0.0
 * @Date 2020/3/23 21:23
 */
public class RightWayStopThreadWithSleepEveryLoop {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;
            /**
             * 循环内部抛出的InterruptException并没有被catch，所以能正常中断。
             * 总之，不要在循环内部try/catch 否则线程会无法正确的响应中断。
             */
            try {
                while (num <= 10000) {
                    if (num % 100 == 0) {
                        System.out.println(num + " 是100的倍数");
                    }
                    num++;
                    Thread.sleep(10);
                }
            } catch (InterruptedException e) {
                //响应外部中断的方式(即在休眠中响应interrupt()中断)，抛出异常
                e.printStackTrace();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }
}
