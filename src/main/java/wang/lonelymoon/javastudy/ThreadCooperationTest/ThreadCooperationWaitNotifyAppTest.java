package wang.lonelymoon.javastudy.ThreadCooperationTest;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * wait() notify() notifyAll()
 * <p>
 * 调用 wait() 使得线程等待某个条件满足，线程在等待时会被挂起，当其他线程的运行使得这个条件满足时，其它线程会调用 notify() 或者 notifyAll() 来唤醒挂起的线程。
 * <p>
 * 它们都属于 Object 的一部分，而不属于 Thread。
 * <p>
 * 只能用在同步方法或者同步控制块中使用，否则会在运行时抛出 IllegalMonitorStateException。
 * <p>
 * 使用 wait() 挂起期间，线程会释放锁。这是因为，如果没有释放锁，那么其它线程就无法进入对象的同步方法或者同步控制块中，那么就无法执行 notify() 或者 notifyAll() 来唤醒挂起的线程，造成死锁。
 *
 * @author lonelymoon
 */
public class ThreadCooperationWaitNotifyAppTest {

    public synchronized void before() {
        System.out.println("before");
        notifyAll();
    }

    public synchronized void after() {
        try {
            wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("after");
    }

    @Test
    public void testThreadCooperationWaitNotifyApp() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ThreadCooperationWaitNotifyAppTest app = new ThreadCooperationWaitNotifyAppTest();
        executorService.execute(app::after);
        executorService.execute(app::before);
    }

}
