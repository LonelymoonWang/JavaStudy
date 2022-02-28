package wang.lonelymoon.javastudy.test;

import org.joda.time.DateTime;
import org.junit.Test;
import wang.lonelymoon.javastudy.util.FileUtils;

import java.io.File;

public class FileUtilsTest {

    @Test
    public void downloadFile() {
        String url = "https://smfyun.oss-cn-beijing.aliyuncs.com/scrm/%E5%A3%81%E7%BA%B8001.jpg";
        try {
            File file = FileUtils.getFile(url);
            System.out.println(file.getAbsoluteFile());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testJodaTime() {
        DateTime dateTime = new DateTime();
        System.out.println(dateTime.toString("yyyy-MM-dd HH:mm:ss"));
    }


}
