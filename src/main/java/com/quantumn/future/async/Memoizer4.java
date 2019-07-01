package com.quantumn.future.async;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class Memoizer4<A,V> implements Computable<A,V> {

    private final Map<A,Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A,V> c;

    public Memoizer4(Computable<A, V> c) {
        this.c = c;
    }
    @Override
    public V compute(A arg) throws InterruptedException {
        Future<V> f = cache.get(arg);
        if (f == null) {
            Future<V> ft = new FutureTask<>(() -> c.compute(arg));
            Future<V> result = cache.putIfAbsent(arg, ft);
            if (result == null) {
                f = ft;
                ((FutureTask<V>) ft).run();
            } else {
                f = result;
            }
        }

        try {
            return f.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
