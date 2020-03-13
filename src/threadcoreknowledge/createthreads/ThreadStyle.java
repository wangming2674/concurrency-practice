package threadcoreknowledge.createthreads;

/**
 * @ClassName ThreadStyle
 * @Description 用Thread的方式创建线程
 * @Author Evan Wang
 * @Version 1.0.0
 * @Date 2020/3/12 14:43
 */
public class ThreadStyle extends Thread {
    public static void main(String[] args) {
        new ThreadStyle().start();
    }

    //整个run()重写，抛弃Thread类中的判断
    @Override
    public void run() {
        System.out.println("用Thread类实现线程");
    }
}
