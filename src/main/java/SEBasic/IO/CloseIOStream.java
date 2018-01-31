package SEBasic.IO;

import org.junit.Test;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

/**
 * @Author: yihau UESTC
 * @Date: Created in 上午11:03 18-1-23
 * @Description:
 */
public class CloseIOStream {
    @Test
    public void old(){
        Reader reader = null;
        try {
            reader = new FileReader("t1.txt");
            int data = 0;
            while ((data = reader.read()) != -1){
                System.out.println(data);
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void newJava7(){
        try(Reader reader = new FileReader("t1.txt");){
            int data = 0;
            while ((data = reader.read()) != -1){
                System.out.println(data);
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        new CloseIOStream().newJava7();
    }
}
