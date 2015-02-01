package husd.wsi.util;

import org.apache.commons.io.IOUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.InputStreamReader;

/**
 * Created by shengdong on 2015/1/18.
 */
public class FileUtil {

    public static String readFile(String file) {
        String sb = null;
        FileInputStream fileInputStream = null;
        try {
            fileInputStream = new FileInputStream(file);
            sb = IOUtils.toString(fileInputStream, "utf-8");
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            IOUtils.closeQuietly(fileInputStream);
        }
        return sb;
    }
}
