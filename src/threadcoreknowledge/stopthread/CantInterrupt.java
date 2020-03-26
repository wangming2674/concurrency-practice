package threadcoreknowledge.stopthread;

/**
 * @ClassName CantInterrupt
 * @Description 如果while里面放try/catch，会到最后中断失效
 * @Author Evan Wang
 * @Version 1.0.0
 * @Date 2020/3/23 21:37
 */
public class CantInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Runnable runnable = () -> {
            int num = 0;
            /**
             * 判断Thread.currentThread().isInterrupted()
             * 不生效的原因是：sleep()函数设计是一旦在sleep过程中响应中断
             * 就会清楚掉线程的中断标记，所以判断当前线程是否中断，就不生效了
             */
            while (num <= 10000 && Thread.currentThread().isInterrupted()) {
                if (num % 100 == 0) {
                    System.out.println(num + " 是100的倍数");
                }
                num++;
                //当try放在循环内层的时候，抛出的interruptedException异常会被catch，不会中断线程
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    //响应外部中断的方式(即在休眠中响应interrupt()中断)，抛出异常
                    e.printStackTrace();
                }
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();
        Thread.sleep(5000);
        thread.interrupt();
    }
}
