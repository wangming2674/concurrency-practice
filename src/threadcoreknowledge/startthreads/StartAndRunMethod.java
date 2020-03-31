package threadcoreknowledge.startthreads;

/**
 * @ClassName StartAndRunMethod
 * @Description 对比start和run两种启动线程的方式
 * @Author Evan Wang
 * @Version 1.0.0
 * @Date 2020/3/22 22:24
 */
public class StartAndRunMethod {
    public static void main(String[] args) {
        Runnable runnable = () -> {
            System.out.println(Thread.currentThread().getName());
        };
        runnable.run();
        /**
         * start()方法的含义：
         * 一、通知jvm在有空闲的情况下 启动新线程，不由程序员决定，由线程调度器去执行。
         * 调用start()方法的顺序，并不代表执行顺序。
         * 必须有一个线程(主线程)去执行start()方法，然后才是创建的子线程去执行任务。
         *
         * 二、新启的子线程要去做准备工作，先让自己处于准备状态(获取了除cpu资源以外的其他资源，例如设置了上下文，获取了栈，指向了PC寄存器)。
         * 然后获取cpu资源，最终启动子线程执行run()方法内的代码。
         *
         * 三、不能重复的执行start()
         */
        new Thread(runnable).start();
    }
}
