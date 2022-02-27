package wang.lonelymoon.javastudy.LockTest;

import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author lonelymoon
 */
public class ReentrantLockAppTest {

    private final Lock lock = new ReentrantLock();

    public void func() {
        lock.lock();
        try {
            for (int i = 0; i < 10; i++) {
                System.out.print(i + " ");
            }
        } finally {
            lock.unlock(); // 确保释放锁，从而避免发生死锁。
        }
    }


    /**
     * ReentrantLock 是 java.util.concurrent（J.U.C）包中的锁。
     */
    @Test
    public void testReentrantLock() {
        ReentrantLockAppTest reentrantLockAppTest = new ReentrantLockAppTest();
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(reentrantLockAppTest::func);
        executorService.execute(reentrantLockAppTest::func);
    }

}
