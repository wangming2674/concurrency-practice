package threadcoreknowledge.stopthread;

/**
 * @ClassName RunThrowException
 * @Description run()无法抛出checked Exception，只能用try/catch
 * @Author Evan Wang
 * @Version 1.0.0
 * @Date 2020/3/29 18:45
 */
public class RunThrowException {

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    //只能使用try/catch
                    throw new Exception();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    public void normalMethod() throws Exception {
        throw new Exception();
    }
}
