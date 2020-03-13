package threadcoreknowledge.createthreads;

/**
 * @ClassName RunnableStyle
 * @Description 用Runnable的方式创建线程
 * @Author Evan Wang
 * @Version 1.0.0
 * @Date 2020/3/12 14:19
 */
public class RunnableStyle implements Runnable {
    /**
     * 总结：
     * 两种方式对比：使用Runnable的方式更好
     * 1.run方法中具体执行的任务应与Thread类解耦
     * 2.如果使用继承Thread类的方式，每次都要去重新创建和销毁线程，开销大。
     * 3.java只支持单一继承，大大限制了子类的拓展性。
     * 综上所述选择时间Runnable的形式更好。
     * <p>
     * 准确的讲，创建线程只有一种方式那就是构造Thread类，而实现线程的执行单元有两种方式。
     * 方式一：实现Runnable接口的run方法，并把Runnable实例传给Thread类。
     * 方式二：重写Thread的run方法(继承Thread类)。
     */
    public static void main(String[] args) {
        Thread thread = new Thread(new RunnableStyle());
        thread.start();
    }

    //调用target.run()
    @Override
    public void run() {
        System.out.println("用Runnable方法实现线程");
    }
}
