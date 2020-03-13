package threadcoreknowledge.createthreads.wrongways;

/**
 * @ClassName AnonymousInnerClassDemo
 * @Description 使用匿名内部类创建线程
 * @Author Evan Wang
 * @Version 1.0.0
 * @Date 2020/3/13 12:35
 */
public class AnonymousInnerClassDemo {
    public static void main(String[] args) {
        new Thread() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }.start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }).start();
    }
}
