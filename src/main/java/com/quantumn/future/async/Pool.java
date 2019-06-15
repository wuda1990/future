package com.quantumn.future.async;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Pool {

    public static void main(String[] args) {
        ExecutorService test = Executors.newCachedThreadPool();
        test.submit(new Runnable() {
            @Override
            public void run() {
//                try {
//                    Thread.sleep(2000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }


                System.out.println("task is running");

            }
        });

        Thread.yield();

        System.out.println("main class is running");
    }



}
