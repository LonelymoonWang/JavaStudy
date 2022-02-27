package wang.lonelymoon.javastudy.JUCTest;

import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

/**
 * 主要用于并行计算中，和 MapReduce 原理类似，都是把大的计算任务拆分成多个小任务并行计算。
 *
 * @author lonelymoon
 */
public class ForkJoinTest {

    @Test
    public void testForkJoin() throws ExecutionException, InterruptedException {
        ForkJoinBean example = new ForkJoinBean(1, 10000);
        ForkJoinPool forkJoinPool = new ForkJoinPool();
        Future<Integer> result = forkJoinPool.submit(example);
        System.out.println(result.get());
    }

    /**
     * ForkJoin 使用 ForkJoinPool 来启动，它是一个特殊的线程池，线程数量取决于 CPU 核数。
     * <p>
     * ForkJoinPool 实现了工作窃取算法来提高 CPU 的利用率。
     * <p>
     * 每个线程都维护了一个双端队列，用来存储需要执行的任务。
     * <p>
     * 工作窃取算法允许空闲的线程从其它线程的双端队列中窃取一个任务来执行。
     * <p>
     * 窃取的任务必须是最晚的任务，避免和队列所属线程发生竞争。例如下图中，Thread2 从 Thread1 的队列中拿出最晚的 Task1 任务，Thread1 会拿出 Task2 来执行，这样就避免发生竞争。
     * <p>
     * 但是如果队列中只有一个任务时还是会发生竞争。
     */
    private static class ForkJoinBean extends RecursiveTask<Integer> {
        private int first;
        private int last;

        public ForkJoinBean(int first, int last) {
            this.first = first;
            this.last = last;
        }

        public ForkJoinBean() {
        }

        @Override
        protected Integer compute() {
            int result = 0;
            int threshold = 5;
            if (last - first <= threshold) {
                // 任务足够小则直接计算
                for (int i = first; i <= last; i++) {
                    result += i;
                }
            } else {
                // 拆分成小任务
                int middle = first + (last - first) / 2;
                ForkJoinBean leftTask = new ForkJoinBean(first, middle);
                ForkJoinBean rightTask = new ForkJoinBean(middle + 1, last);
                leftTask.fork();
                rightTask.fork();
                result = leftTask.join() + rightTask.join();
            }
            return result;
        }
    }

}
