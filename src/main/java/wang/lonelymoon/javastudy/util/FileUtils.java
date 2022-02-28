package wang.lonelymoon.javastudy.util;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

/**
 * @author lonelymoon
 */
public class FileUtils {

    public static File getFile(String url) throws Exception {
        //对本地文件命名
        String fileName = url.substring(url.lastIndexOf("."));
        File file = null;

        URL urlfile;
        InputStream inStream = null;
        OutputStream os = null;
        try {
            file = File.createTempFile("net_url", fileName);
            //下载
            urlfile = new URL(url);
            inStream = urlfile.openStream();
            os = new FileOutputStream(file);

            int bytesRead = 0;
            byte[] buffer = new byte[8192];
            while ((bytesRead = inStream.read(buffer, 0, 8192)) != -1) {
                os.write(buffer, 0, bytesRead);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != os) {
                    os.close();
                }
                if (null != inStream) {
                    inStream.close();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return file;
    }

}
