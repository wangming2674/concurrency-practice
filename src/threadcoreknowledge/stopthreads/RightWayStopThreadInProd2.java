package threadcoreknowledge.stopthreads;

/**
 * @ClassName RightWayStopThreadInProd2
 * @Description 最佳实践2: 在catch子句中调用Thread.currentThread().interrupt()来回复设置的线程中断状态，
 * 以便在后续的执行中，依然能够检查到刚才发生了中断。
 * 回到刚才的RightWayStopThreadInProd补上中断，让它跳出。(恢复中断)
 * @Author Evan Wang
 * @Version 1.0.0
 * @Date 2020/3/29 19:19
 */
public class RightWayStopThreadInProd2 implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadInProd2());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }

    @Override
    public void run() {
        while (true) {
            if (Thread.currentThread().isInterrupted()) {
                System.out.println("Interrupted,程序运行结束");
                break;
            }
            reInterrupt();
        }
    }

    private void reInterrupt() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            //catch后再次调用中断，即可完成标记
            Thread.currentThread().interrupt();
            e.printStackTrace();
        }
    }
}