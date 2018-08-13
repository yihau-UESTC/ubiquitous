package Concurrent.cache;

import java.math.BigInteger;

public class ExpensiveFunction implements Computable<String, BigInteger> {
    @Override
    public BigInteger compute(String s) throws InterruptedException {
        //中间有很长时间的计算过程
        return new BigInteger(s);
    }
}
