package threadcoreknowledge.stopthreads;

/**
 * @ClassName RightWayInterrupted
 * @Description 注意Thread.interrupted()方法的目标对象是"当前线程"，而不管本方法来自于哪个对象
 * @Author Evan Wang
 * @Version 1.0.0
 * @Date 2020/3/31 15:18
 */
public class RightWayInterrupted {
    public static void main(String[] args) throws InterruptedException {
        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                for (; ; ) {
                }
            }
        });

        // 启动线程
        threadOne.start();
        //设置中断标志
        threadOne.interrupt();
        //获取中断标志，上面进行了中断，所以返回ture
        System.out.println("isInterrupted: " + threadOne.isInterrupted());
        //获取中断标志并重置，其实执行threadOne.interrupted()的是main函数，主线程没有被中断，所以是false
        System.out.println("isInterrupted: " + threadOne.interrupted());
        //获取中断标志并重直，同上，仍然是返回主线程的状态，所以也返回false
        System.out.println("isInterrupted: " + Thread.interrupted());
        //获取中断标志，不会清除threadOne的线程状态，仍然是返回ture
        System.out.println("isInterrupted: " + threadOne.isInterrupted());
        threadOne.join();
        System.out.println("Main thread is over.");

        /**
         * 总结： threadOne.interrupted()，Thread.interrupted()，这两个<静态方法>，只关心当前执行方法的线程。
         * 对其他线程的状态并不关心。
         */
    }
}
