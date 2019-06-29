package com.quantumn.future.async;

public interface Computable<A, V> {
    V compute(A arg) throws InterruptedException;
}
