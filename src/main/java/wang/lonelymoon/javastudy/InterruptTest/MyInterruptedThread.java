package wang.lonelymoon.javastudy.InterruptTest;

/**
 * @author lonelymoon
 */
public class MyInterruptedThread extends Thread {
    @Override
    public void run() {
        try {
            while (!interrupted()) {
                System.out.println("Thread run");
            }
            System.out.println("Thread end");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
