package wang.lonelymoon.javastudy.ThreadTest;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

@Slf4j
public class MyCallable implements Callable<Integer> {

    public static final int RETURN_NUM = 1;

    @Override
    public Integer call() throws Exception {
        log.info("测试Callable");
        return RETURN_NUM;
    }
}
