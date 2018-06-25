package redis;

import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.concurrent.*;

public class JedisTest {

    @Test
    public void testJedis1() {
        Jedis jedis = new Jedis("192.168.0.203", 6379);

        jedis.set("wang", "yihao");

        System.out.println(jedis.get("wang"));
        jedis.close();
    }

    @Test
    public void testJedisPool() throws ExecutionException, InterruptedException {
        JedisPool pool = new JedisPool("192.168.0.203", 6379);
        Jedis jedis = pool.getResource();
        jedis.set("wang", "1");
        System.out.println(jedis.get("wang"));
        jedis.del("wang");
        System.out.println(jedis.get("wang"));
        jedis.close();
        pool.close();
    }

    @Test
    public void testAsy() {


    }


}
