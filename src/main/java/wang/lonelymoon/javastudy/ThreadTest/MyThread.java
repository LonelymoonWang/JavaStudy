package wang.lonelymoon.javastudy.ThreadTest;

import lombok.extern.slf4j.Slf4j;

/**
 * @author lonelymoon
 */
@Slf4j
public class MyThread extends Thread{

    @Override
    public void run() {
        System.out.println("1111");
        /**
         * TODO 为什么不打印log日志，而会输入System.out.println的输出内容
         */
        log.info("测试Thread");
    }

}
