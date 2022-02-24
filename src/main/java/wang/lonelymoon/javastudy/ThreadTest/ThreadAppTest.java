package wang.lonelymoon.javastudy.ThreadTest;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;

/**
 * 线程测试
 *
 * @author lonelymoon
 */
@Slf4j
public class ThreadAppTest {
    @Test
    public void runnableTest() {
        MyRunnable myRunnable = new MyRunnable();
        Thread thread = new Thread(myRunnable);
        log.info("测试Runnable");
        thread.start();
    }


    @Test
    public void callableTest() {
        try {
            MyCallable mc = new MyCallable();
            FutureTask<Integer> ft = new FutureTask<>(mc);
            Thread thread = new Thread(ft);
            thread.start();
            log.info("ft:{}", ft.get());
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } catch (Exception ignored) {

        }
    }

    @Test
    public void threadTest() {
        MyThread myThread = new MyThread();
        myThread.start();
    }

    @Test
    public void executorCachedThreadPoolTest() {
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new MyRunnable());
        }
        executorService.shutdown();
    }

    @Test
    public void executorFixedThreadPoolTest() {
        ExecutorService executorService = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 5; i++) {
            executorService.execute(new MyRunnable());
        }
        executorService.shutdown();
    }

    @Test
    public void executorSingleThreadExecutorTest() {
        ExecutorService executorService = Executors.newSingleThreadExecutor();
        for (int i = 0; i < 5; i++) {
            executorService.execute(new MyRunnable());
        }
        executorService.shutdown();
    }


    @Test
    public void executorDaemonTest() {
        /**
         * TODO 为什么设置Daemon为true的时候，会打印Runnable中打印的日志
         */
        Thread thread = new Thread(new MyRunnable());
        thread.setDaemon(true);
        thread.start();
        log.info("时间cuo:{}", System.currentTimeMillis());
    }

    @Test
    public void executorSleepTest() {
        try {
            long l1 = System.currentTimeMillis();
            log.info("l1:{}", l1);
            Thread.sleep(1000);
            long l2 = System.currentTimeMillis();
            log.info("l2:{}", l2);
            log.info("l2-l1:{}", l2 - l1);
        } catch (InterruptedException e) {
            log.error("error", e);
        }
    }
}
