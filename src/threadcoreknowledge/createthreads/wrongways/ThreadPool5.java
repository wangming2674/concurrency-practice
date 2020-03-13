package threadcoreknowledge.createthreads.wrongways;


import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @ClassName ThreadPool5
 * @Description 线程池创建线程的方法
 * @Author Evan Wang
 * @Version 1.0.0
 * @Date 2020/3/12 15:48
 */
public class ThreadPool5 {
    public static void main(String[] args) {
        //原理也是通过new Thread()然后传入Runnable来创建线程
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 1000; i++) {
            executorService.submit(new Task());
        }
    }
}

class Task implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName());
    }
}
