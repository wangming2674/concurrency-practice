package threadcoreknowledge.stopthreads.volatiledemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * @ClassName WrongWayVolatileCantStop
 * @Description 演示用volatile的局限part2
 * 陷入阻塞时，volatile是无法停止线程的
 * 此例中，生产者的生产速度很快，消费者消费速度慢
 * 所以会出现阻塞队列满了以后，生产者会阻塞，等待消费者进一步消费。
 * @Author Evan Wang
 * @Version 1.0.0
 * @Date 2020/3/30 23:12
 */
public class WrongWayVolatileCantStop {

    public static void main(String[] args) throws InterruptedException {
        //阻塞队列，满了放不进去，空了取不出来，两种情况都会阻塞。
        ArrayBlockingQueue storage = new ArrayBlockingQueue(10);

        Producer producer = new Producer(storage);
        Thread producerThread = new Thread(producer);
        producerThread.start();
        //给生产者时间，让其塞满队列，塞满后队列阻塞，相当于生产者进行休息
        Thread.sleep(1000);

        Consumer consumer = new Consumer(storage);
        while (consumer.needMoreNums()) {
            System.out.println(consumer.storage.take() + "被消费了");
            Thread.sleep(100);
        }
        System.out.println("消费者不需要更多数据了。");
        //一旦消费者不需要更多数据了，我们就应该让生产者也停下来，但是实际情况
        producer.canceled = true;
        System.out.println(producer.canceled);
    }
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
            /**
             * 使用volatile不能正常停止线程的原因分析：
             * 当我们在满足的某种条件后，将canceled置为true，但是由于storage满了，
             * 所以在storage.put(num);这里发生了阻塞，但是由于我们是在循环上判断的canceled，
             * 而前面的阻塞导致了我们无法走到循环判断canceled的代码，从而导致了线程没办法正常终止。
             */
            while (num <= 10000 && !canceled) {
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