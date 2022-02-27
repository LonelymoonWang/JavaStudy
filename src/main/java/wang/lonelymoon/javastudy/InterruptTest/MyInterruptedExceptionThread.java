package wang.lonelymoon.javastudy.InterruptTest;

/**
 * @author lonelymoon
 */
public class MyInterruptedExceptionThread extends Thread {
    @Override
    public void run() {
        try {
            Thread.sleep(2000);
            System.out.println("Thread run");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
