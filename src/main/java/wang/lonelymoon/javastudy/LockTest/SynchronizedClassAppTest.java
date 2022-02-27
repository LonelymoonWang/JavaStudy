package wang.lonelymoon.javastudy.LockTest;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author lonelymoon
 */
@Slf4j
public class SynchronizedClassAppTest {

    /**
     * 作用于整个类，也就是说两个线程调用同一个类的不同对象上的这种同步语句，也会进行同步。
     */
    @Test
    public void testSynchronizedCodeBlockBySame() {
        long l1 = System.currentTimeMillis();
        SynchronizedClassAppTest e1 = new SynchronizedClassAppTest();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(e1::func);
        executorService.execute(e1::func);
        log.info("testSynchronizedCodeBlockBySame");
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
        SynchronizedClassAppTest e1 = new SynchronizedClassAppTest();
        SynchronizedClassAppTest e2 = new SynchronizedClassAppTest();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(e1::func);
        executorService.execute(e2::func);
        log.info("testSynchronizedCodeBlockByNotSame");
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        long l2 = System.currentTimeMillis();
        log.info("l2-l1:{}", l2 - l1);
    }

    public void func() {
        synchronized (SynchronizedClassAppTest.class) {
            for (int i = 0; i < 10; i++) {
                try {
                    System.out.println(Thread.currentThread().getName());
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.print(i + " ");
            }
        }
    }
}
