package wang.lonelymoon.javastudy.ThreadCooperationTest;

/**
 * @author lonelymoon
 */
public class MyThreadB extends Thread {

    private MyThreadA myThreadA;

    MyThreadB(MyThreadA myThreadA) {
        this.myThreadA = myThreadA;
    }

    @Override
    public void run() {
        try {
            myThreadA.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("MyThreadB");
    }
}
