package wang.lonelymoon.javastudy.ThreadCooperationTest;

import org.junit.Test;

/**
 * 线程之间的协作 join()
 * <p>
 * 在线程中调用另一个线程的 join() 方法，会将当前线程挂起，而不是忙等待，直到目标线程结束。
 * <p>
 * 对于以下代码，虽然 b 线程先启动，但是因为在 b 线程中调用了 a 线程的 join() 方法，b 线程会等待 a 线程结束才继续执行，因此最后能够保证 a 线程的输出先于 b 线程的输出。
 *
 * @author lonelymoon
 */
public class ThreadCooperationJoinAppTest {

    @Test
    public void testThreadCooperationJoinApp() {
        MyThreadA myThreadA = new MyThreadA();
        MyThreadB myThreadB = new MyThreadB(myThreadA);
        myThreadB.start();
        myThreadA.start();
    }

}
