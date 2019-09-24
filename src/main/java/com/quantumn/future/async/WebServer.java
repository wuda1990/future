package com.quantumn.future.async;

import java.util.concurrent.*;

public class WebServer {
    private static ExecutorService executorService = Executors.newFixedThreadPool(2);



    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable<Integer> task = () -> {
            Thread.sleep(10000);
            return 1;
        };
        Future<Integer> f = executorService.submit(task);
        System.out.println(f.get());
    }





}
