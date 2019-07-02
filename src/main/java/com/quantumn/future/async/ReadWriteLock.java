package com.quantumn.future.async;

import java.util.HashSet;
import java.util.Set;

public class ReadWriteLock {

    private Set<Thread> readersSet = new HashSet<>(10);
    private ThreadLocal<Integer> reentrantReadCnt = new ThreadLocal<>();
    private Thread writeThread;
    private int writeRequests;
    private int writeAccess;

    public synchronized void lockRead() throws InterruptedException {
        Thread currentThread = Thread.currentThread();
        while (!isGrantReadAccess(currentThread)) {
            wait();
        }
        if (reentrantReadCnt.get() == null) {
            reentrantReadCnt.set(1);
        }else{
            reentrantReadCnt.set(reentrantReadCnt.get()+1);
        }
        readersSet.add(currentThread);
    }

    private boolean isGrantReadAccess(Thread callingThread) {
        if (callingThread == writeThread) return true;
        if (writeThread!=null)  return false;
        if (readersSet.contains(callingThread)) return true;
        if (writeRequests>0) return false;
        return true;
    }

    public synchronized void unlockRead() {
        Thread callingThread = Thread.currentThread();
        if(!readersSet.contains(callingThread)){
            throw new IllegalMonitorStateException(
                    "Calling Thread does not hold a read lock on this ReadWriteLock");
        }
        Integer cnt = reentrantReadCnt.get();
        if (cnt == 1) {
            readersSet.remove(Thread.currentThread());
        } else {
            cnt--;
            reentrantReadCnt.set(cnt);
        }
        notifyAll();

    }

    public synchronized void lockWrite() throws InterruptedException {
        writeRequests++;
        Thread currentThread = Thread.currentThread();
        while (!isGrantWriteAccess(currentThread)) {
            wait();
        }
        writeRequests--;
        writeAccess++;
        writeThread = currentThread;
    }

    private boolean isGrantWriteAccess(Thread callingThread) {
        if (writeThread == callingThread) {
            return true;
        }
        if (readersSet.size() == 1 && readersSet.contains(callingThread)) {
            return true;
        }
        if (!readersSet.isEmpty()) {
            return false;
        }
        if (writeThread != null) {
            return false;
        }
        return true;
    }

    public synchronized void unlockWrite() {
        writeAccess--;
        if (writeAccess == 0) {
            writeThread = null;
        }
        notifyAll();
    }
}
