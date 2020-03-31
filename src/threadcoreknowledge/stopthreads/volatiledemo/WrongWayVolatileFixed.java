package threadcoreknowledge.stopthreads.volatiledemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @ClassName WrongWayVolatileFixed
 * @Description 用中断来修复刚才的无尽等待问题
 * @Author Evan Wang
 * @Version 1.0.0
 * @Date 2020/3/31 13:48
 */
public class WrongWayVolatileFixed {
    //采用内部类的方式
    public static void main(String[] args) throws InterruptedException {

        WrongWayVolatileFixed body = new WrongWayVolatileFixed();

        //阻塞队列，满了放不进去，空了取不出来，两种情况都会阻塞。
        ArrayBlockingQueue storage = new ArrayBlockingQueue(10);

        Producer producer = body.new Producer(storage);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        //给生产者时间，让其塞满队列，塞满后队列阻塞，相当于生产者进行休息
        Thread.sleep(1000);

        Consumer consumer = body.new Consumer(storage);
        while (consumer.needMoreNums()) {
            System.out.println(consumer.storage.take() + "被消费了");
            Thread.sleep(100);
        }
        System.out.println("消费者不需要更多数据了。");

        /**
         *   Thread.interrupted(); 清除标记状态
         *   producerThread.isInterrupted(); 不清除标记状态
         */

        producerThread.interrupt();
        System.out.println(producer.canceled);
    }

    class Producer implements Runnable {

        protected volatile boolean canceled = false;

        BlockingQueue storage;

        public Producer(BlockingQueue storage) {
            this.storage = storage;
        }

        @Override
        public void run() {
            int num = 0;
            try {
                //Thread.currentThread().isInterrupted() 能在休眠或阻塞时，响应中断。
                while (num <= 10000 && !Thread.currentThread().isInterrupted()) {
                    if (num % 100 == 0) {
                        //一开始塞满10个后开始阻塞，被消费后小于10个又继续生产
                        storage.put(num);
                        System.out.println(num + "是100的倍数，被放到仓库中了");
                    }
                    num++;
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                System.out.println("生产者停止运行");
            }
        }
    }

    class Consumer {

        BlockingQueue storage;

        public Consumer(BlockingQueue storage) {
            this.storage = storage;
        }

        public boolean needMoreNums() {
            if (Math.random() > 0.95) {
                return false;
            }
            return true;
        }
    }
}




