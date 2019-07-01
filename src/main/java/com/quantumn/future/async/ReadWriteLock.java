package com.quantumn.future.async;

public class ReadWriteLock {
    private int readers;
    private int writers;
    private int writeRequests;

    public synchronized void lockRead() throws InterruptedException {
        if (writers > 0 || writeRequests > 0) {
            wait();
        }
        readers++;
    }

    public synchronized void unlockRead() {
        readers--;
        notifyAll();
    }

    public synchronized void lockWrite() throws InterruptedException {
        writeRequests++;
        if (readers > 0 || writers > 0) {
            wait();
        }
        writers++;
    }

    public synchronized void unlockWrite() {
        writers--;
        notifyAll();
    }
}
