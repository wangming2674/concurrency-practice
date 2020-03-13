package threadcoreknowledge.createthreads.wrongways;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @ClassName DemoTimerTask
 * @Description 使用定时器创建线程
 * @Author Evan Wang
 * @Version 1.0.0
 * @Date 2020/3/13 12:25
 */
public class DemoTimerTask {
    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }, 1000, 1000);
    }
}
