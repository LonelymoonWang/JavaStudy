package wang.lonelymoon.javastudy.InterruptTest;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 线程中断测试
 *
 * @author lonelymoon
 */
@Slf4j
public class InterruptAppTest {

    /**
     * 线程抛异常，不会阻断主线程执行
     * 如果主线程没执行完，子线程会先执行，如果主线程执行完，子线程没执行完，子线程会挂掉
     * 如果使用thread.interrupt()，子线程由于sleep了2秒，该线程会throw sleep interrupted
     */
    @Test
    public void testInterruptedException() {
        long l1 = System.currentTimeMillis();
        Thread thread = new MyInterruptedExceptionThread();
        thread.start();
//        thread.interrupt();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("testInterruptedException");
        long l2 = System.currentTimeMillis();
        log.info("l2-l1:{}", l2 - l1);
    }

    /**
     * 如果一个线程的 run() 方法执行一个无限循环，并且没有执行 sleep() 等会抛出 InterruptedException 的操作
     * ，那么调用线程的 interrupt() 方法就无法使线程提前结束。
     * <p>
     * 但是调用 interrupt() 方法会设置线程的中断标记，此时调用 interrupted() 方法会返回 true。
     * 因此可以在循环体中使用 interrupted() 方法来判断线程是否处于中断状态，从而提前结束线程。
     */
    @Test
    public void testInterrupted() {
        long l1 = System.currentTimeMillis();
        Thread thread = new MyInterruptedThread();
        thread.start();
        thread.interrupt();
        log.info("testInterrupted");
        long l2 = System.currentTimeMillis();
        log.info("l2-l1:{}", l2 - l1);
    }

    /**
     * 调用 Executor 的 shutdown() 方法会等待线程都执行完毕之后再关闭，但是如果调用的是 shutdownNow() 方法，则相当于调用每个线程的 interrupt() 方法。
     * <p>
     * 以下使用 Lambda 创建线程，相当于创建了一个匿名内部线程。
     * <p>
     * 如果只想中断 Executor 中的一个线程，可以通过使用 submit() 方法来提交一个线程，
     * <p>
     * 它会返回一个 Future\<?\> 对象，通过调用该对象的 cancel(true) 方法就可以中断线程。
     */
    @Test
    public void testInterruptedExecutor() {
        long l1 = System.currentTimeMillis();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> {
            try {
                Thread.sleep(2000);
                System.out.println("Thread run");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        Future<?> future = executorService.submit(() -> {
            // ..
        });
        future.cancel(true);
//        executorService.shutdownNow();
        log.info("testInterruptedExecutor");
        long l2 = System.currentTimeMillis();
        log.info("l2-l1:{}", l2 - l1);
    }

}
