package com.quantumn.future.async;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.*;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
public class LockExample {
    Integer pageCount = 0;
    @Test
    public void testReadWriteLock() throws ExecutionException, InterruptedException {
        ReadWriteLock lock = new ReadWriteLock();
        CountDownLatch latch = new CountDownLatch(2);
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Callable<Integer> reader = () -> {
            try {
                lock.lockRead();
                log.info("reader "+Thread.currentThread().getName()+" is running");
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlockRead();
                log.info("reader "+Thread.currentThread().getName()+" is done");
            }
            return pageCount;
        };

        Callable<Integer> writer = () -> {
            try {
                lock.lockWrite();
                lock.lockWrite();
                log.info("writer "+Thread.currentThread().getName()+" is running");
                Thread.sleep(3000);
                pageCount++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                lock.unlockWrite();
                lock.unlockWrite();
                latch.countDown();
            }
            return pageCount;
        };
        Future<Integer> f = executorService.submit(reader);
        Thread.sleep(1000);
        executorService.submit(writer);
        executorService.submit(writer);
        latch.await();
        Future<Integer> f1 = executorService.submit(reader);
        log.info("page count is :"+ f1.get());
        Future<Integer> f2 = executorService.submit(reader);
        log.info("page count is :"+ f2.get());
    }

    static final int SHARED_SHIFT   = 16;
    static final int SHARED_UNIT    = (1 << SHARED_SHIFT);
    static final int MAX_COUNT      = (1 << SHARED_SHIFT) - 1;
    static final int EXCLUSIVE_MASK = (1 << SHARED_SHIFT) - 1;

    /** Returns the number of shared holds represented in count  */
    static int sharedCount(int c)    { return c >>> SHARED_SHIFT; }
    /** Returns the number of exclusive holds represented in count  */
    static int exclusiveCount(int c) { return c & EXCLUSIVE_MASK; }

    @Test
    public void testRightShift() {
        System.out.println(SHARED_SHIFT);
        System.out.println(EXCLUSIVE_MASK);
        System.out.println("shared:"+sharedCount(-1));
        System.out.println("shared:"+sharedCount(SHARED_UNIT+3));
        System.out.println("exclusive:"+exclusiveCount(SHARED_UNIT));
    }

}
