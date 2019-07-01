package com.quantumn.future.async;

import java.math.BigInteger;

public class ExpensiveFunction implements Computable<String, BigInteger> {


    @Override
    public BigInteger compute(String arg) throws InterruptedException {
        Thread.sleep(3000);
        return new BigInteger(arg);
    }
}
