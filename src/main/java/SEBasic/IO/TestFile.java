package SEBasic.IO;

import org.junit.Test;

import java.io.*;

/**
 * @Author: yihau UESTC
 * @Date: Created in 下午4:04 18-1-23
 * @Description:
 */
public class TestFile {
    @Test
    public void test1() throws IOException {
        InputStream is = new FileInputStream("E:\\code\\ubiquitous\\resource\\1.txt");
        File f = new File("E:\\code\\ubiquitous\\resource\\2.txt");
        if (!f.exists())
            f.createNewFile();
        OutputStream os = new FileOutputStream(f);
        byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = is.read(buffer)) != -1) {
            System.out.println(new String(buffer, "utf-8"));
            os.write(buffer, 0, len);//这里一定要写上len，不然会写入很多0
            os.flush();
        }
        is.close();
        os.close();
    }


}
