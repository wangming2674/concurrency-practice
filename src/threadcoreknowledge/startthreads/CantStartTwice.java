package threadcoreknowledge.startthreads;

/**
 * @ClassName CantStartTwice
 * @Description 不能两次调用start方法，否则会报错
 * @Author Evan Wang
 * @Version 1.0.0
 * @Date 2020/3/22 22:40
 */
public class CantStartTwice {
    public static void main(String[] args) {
        /**
         * 线程一旦开始执行，就从new状态到后续的执行状态，
         * 一旦结束，就不能恢复到之前的状态，即不能再恢复到启动状态。
         * 简单介绍三个状态(后续有详细介绍)：
         * 1.启动新线程检查线程状态
         * 2.加入线程组
         * 3.调用start0()
         */
        Thread thread = new Thread();
        thread.start();
        thread.start();
    }
}
