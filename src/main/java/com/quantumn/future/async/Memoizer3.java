package com.quantumn.future.async;

import java.util.Map;
import java.util.concurrent.*;

public class Memoizer3<A,V> implements Computable<A,V> {

    private final Map<A,Future<V>> cache = new ConcurrentHashMap<>();
    private final Computable<A,V> c;

    public Memoizer3(Computable<A, V> c) {
        this.c = c;
    }
    @Override
    public V compute(A arg) throws InterruptedException {
        Future<V> f = cache.get(arg);
        if (f == null) {
            Future<V> ft = new FutureTask<>(() -> c.compute(arg));
            f = ft;
            cache.put(arg, ft);
            ((FutureTask<V>) ft).run();
        }

        try {
            return f.get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
