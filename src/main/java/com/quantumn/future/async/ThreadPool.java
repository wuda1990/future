package com.quantumn.future.async;

import org.apache.tomcat.util.buf.HexUtils;

public class ThreadPool {

    private static final int COUNT_BITS = Integer.SIZE - 3;
    private static final int CAPACITY   = (1 << COUNT_BITS) - 1;
    //~CAPACITY   11100000000000000000000000000000
    // CAPACITY   00011111111111111111111111111111
    // RUNNING    11100000000000000000000000000000
    // SHUTDOWN   00000000000000000000000000000000
    // STOP       00100000000000000000000000000000
    // TIDYING    01000000000000000000000000000000
    // TERMINATED 01100000000000000000000000000000

    // runState is stored in the high-order bits
    private static final int RUNNING    = -1 << COUNT_BITS;
    private static final int SHUTDOWN   =  0 << COUNT_BITS;
    private static final int STOP       =  1 << COUNT_BITS;
    private static final int TIDYING    =  2 << COUNT_BITS;
    private static final int TERMINATED =  3 << COUNT_BITS;

    // Packing and unpacking ctl
    private static int runStateOf(int c)     { return c & ~CAPACITY; }
    private static int workerCountOf(int c)  { return c & CAPACITY; }
    private static int ctlOf(int rs, int wc) { return rs | wc; }
    public static void main(String[] args) {

        System.out.println(~CAPACITY);
        System.out.println(runStateOf(TERMINATED));
        System.out.println(workerCountOf(TERMINATED));
        System.out.println(ctlOf(TERMINATED,0));
    }

    public void test() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
