package threadcoreknowledge.createthreads;

/**
 * @ClassName BothRunnableThread
 * @Description 同时使用Runnable和Thread两种线程的方式。
 * @Author Evan Wang
 * @Version 1.0.0
 * @Date 2020/3/12 15:13
 */
public class BothRunnableThread {
    public static void main(String[] args) {
        //会打印来自Thread，因为run()被重写
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("来自Runnable");
            }
        }) {
            @Override
            public void run() {
                System.out.println("来自Thread");
            }
        }.start();
    }
}
