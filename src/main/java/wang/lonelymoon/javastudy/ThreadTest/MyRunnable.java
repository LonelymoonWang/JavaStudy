package wang.lonelymoon.javastudy.ThreadTest;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.LoggerFactory;
import org.slf4j.MDC;

import java.util.logging.Logger;

/**
 * @author lonelymoon
 */
@Slf4j
public class MyRunnable implements Runnable{

    @Override
    public void run() {
        try {
            System.out.println(System.currentTimeMillis());
            /**
             * TODO 为什么不打印log日志，而会输入System.out.println的输出内容
             */
            log.info("测试Runnable");
        } catch (Exception e) {
            log.error(e.getMessage());
        }
    }
}
