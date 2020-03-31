package threadcoreknowledge.stopthreads;

/**
 * @ClassName RightWayStopThreadInProd
 * @Description 最佳实践：catch了InterruptException之后的优先选择(在方法签名中抛出异常)
 * 理由: 如果这样做，那么在run()方法中就会强制try/catch(传递中断)
 * @Author Evan Wang
 * @Version 1.0.0
 * @Date 2020/3/26 22:01
 */
public class RightWayStopThreadInProd implements Runnable {

    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(new RightWayStopThreadInProd());
        thread.start();
        Thread.sleep(1000);
        thread.interrupt();
    }

    @Override
    //高层次，父方法，执行靠前
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            System.out.println("simulation business logic");
            try {
                throwInMethod();
            } catch (InterruptedException e) {
                //在这里try/catch，可以感知到中断，从而进行例如：保存日志，停止程序的操作。
                //注意：这里只是收到了中断的信息，但是并没有真实的中断，还是需要自己做额外处理。
                System.out.println("保存日志");
                e.printStackTrace();
            }
        }
    }

    //低层次处理，不在run()上，在子方法的深处。
    private void throwInMethod() throws InterruptedException {
        /**
         * 还是那个原因,thread在sleep()中被中断，会清除中断状态的标记，所以外部判断没办法拿到中断状态。
         * 同时，在这里catch异常，异常并不会导致线程中断，也就进而造成了throwInMethod()方法没法正确的响应中断的问题。
         * 结论:不应在子方法内处理线程相关异常，应该直接抛出，在run()方法内处理。
         */
        Thread.sleep(2000);
    }
}
