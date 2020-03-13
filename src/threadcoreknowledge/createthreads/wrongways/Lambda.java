package threadcoreknowledge.createthreads.wrongways;

/**
 * @ClassName Lambda
 * @Description lambda表达式创建线程
 * @Author Evan Wang
 * @Version 1.0.0
 * @Date 2020/3/13 13:31
 */
public class Lambda {
    public static void main(String[] args) {
        new Thread(() -> System.out.println(Thread.currentThread().getName())).start();
    }
}
