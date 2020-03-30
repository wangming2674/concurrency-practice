package threadcoreknowledge.stopthread;

/**
 * @ClassName StopThread
 * @Description 错误的停止方法：用stop()方法来停止线程，会导致线程运行一半突然停止，没办法完成一个基本单位的操作。
 * 例：(一个连队为一个基本单位)，会造成脏数据(有的连队多领取少领取装备)。
 * suspend()和resume()会将线程挂起，恢复之前不会释放，其他线程不及时唤醒该线程，或需要该线程的锁才能唤醒，就容易造成死锁。
 * @Author Evan Wang
 * @Version 1.0.0
 * @Date 2020/3/30 15:00
 */
public class StopThread implements Runnable {

    public static void main(String[] args) {
        Thread thread = new Thread(new StopThread());
        thread.start();
        try {
            Thread.sleep(1240);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        thread.interrupt();
    }

    @Override
    public void run() {
        //模拟指挥军队：一共5个连队，每个连队100人，以连队为单位，发放武器弹药，叫到号的士兵前去领取。
        for (int i = 1; i < 6; i++) {
            //改： for (int i = 1; i < 6&&!Thread.currentThread().isInterrupted(); i++) {
            System.out.println("连队" + i + "开始领取武器");
            for (int j = 1; j < 11; j++) {
                System.out.println(j);
                try {
                    Thread.sleep(50);
                } catch (InterruptedException e) {
                    //改： Thread.currentThread().interrupt();
                    e.printStackTrace();
                }
            }
            System.out.println("连队" + i + "已经领取完毕");
        }
    }
}
