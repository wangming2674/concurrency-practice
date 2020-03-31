package threadcoreknowledge.stopthreads.volatiledemo;

/**
 * @ClassName WrongWayVolatile
 * @Description 描述：演示volatile的局限，part1看似可行
 * volatile可以保证属性的可见性，可见性的简单理解：多个线程之间都可看到这个属性的值(JMM)
 * @Author Evan Wang
 * @Version 1.0.0
 * @Date 2020/3/30 16:06
 */
public class WrongWayVolatile implements Runnable {

    private volatile boolean canceled = false;

    public static void main(String[] args) throws InterruptedException {
        WrongWayVolatile wrongWayVolatile = new WrongWayVolatile();
        Thread thread = new Thread(wrongWayVolatile);
        thread.start();
        Thread.sleep(5000);
        wrongWayVolatile.canceled = true;
    }

    @Override
    public void run() {
        int num = 0;
        try {
            while (num <= 10000 && !canceled) {
                if (num % 100 == 0) {
                    System.out.println(num + "是100的倍数");
                }
                num++;
                Thread.sleep(5);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
