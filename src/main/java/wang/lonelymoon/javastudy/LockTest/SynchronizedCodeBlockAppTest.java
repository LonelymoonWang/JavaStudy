package wang.lonelymoon.javastudy.LockTest;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lonelymoon
 */
@Slf4j
public class SynchronizedCodeBlockAppTest {

    /**
     * 同步一个代码块和一个方法
     * 它只作用于同一个对象，如果调用两个对象上的同步代码块，就不会进行同步。
     * <p>
     * 对于以下代码，使用 ExecutorService 执行了两个线程，
     * 由于调用的是同一个对象的同步代码块，因此这两个线程会进行同步，当一个线程进入同步语句块时，另一个线程就必须等待。
     */
    @Test
    public void testSynchronizedCodeBlockBySame() {
        long l1 = System.currentTimeMillis();
        SynchronizedCodeBlockAppTest e1 = new SynchronizedCodeBlockAppTest();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(e1::func1);
        executorService.execute(e1::func1);
        log.info("testSynchronizedCodeBlock");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long l2 = System.currentTimeMillis();
        log.info("l2-l1:{}", l2 - l1);
    }

    @Test
    public void testSynchronizedCodeBlockByNotSame() {
        long l1 = System.currentTimeMillis();
        SynchronizedCodeBlockAppTest e1 = new SynchronizedCodeBlockAppTest();
        SynchronizedCodeBlockAppTest e2 = new SynchronizedCodeBlockAppTest();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(e1::func);
        executorService.execute(e2::func);
//        log.info("testSynchronizedCodeBlockByNotSame");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long l2 = System.currentTimeMillis();
        log.info("l2-l1:{}", l2 - l1);
    }

    public void func() {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print(i + " ");
            }
        }
    }

    public synchronized void func1() {
        for (int i = 0; i < 10; i++) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.print(i + " ");
        }
    }

}
