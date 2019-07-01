package com.quantumn.future.async;

import org.junit.Test;

import java.util.concurrent.*;

public class LockTest {
    Integer pageCount = 0;
    @Test
    public void testReadWriteLock() throws ExecutionException, InterruptedException {
        ReadWriteLock lock = new ReadWriteLock();
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        Callable<Integer> reader = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                lock.lockRead();
                System.out.println("reader "+Thread.currentThread().getName()+" is running");
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                lock.unlockRead();
                return pageCount;
            }
        };

        Callable<Integer> writer = new Callable<Integer>() {
            @Override
            public Integer call() throws Exception {
                lock.lockWrite();
                System.out.println("writer "+Thread.currentThread().getName()+" is running");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                pageCount++;
                lock.unlockWrite();
                return pageCount;
            }
        };
        Future<Integer> f = executorService.submit(reader);
        System.out.println("page count is :"+ f.get());
        executorService.submit(writer);
        Future<Integer> f1 = executorService.submit(reader);
        System.out.println("page count is :"+ f1.get());
    }

}
