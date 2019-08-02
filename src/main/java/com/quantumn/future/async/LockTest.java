package com.quantumn.future.async;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
    final ReentrantLock lock ;
    final Condition secondCondition;
    final Condition thirdCondition;

    public static void main(String[] args) {
        LockTest lockTest = new LockTest();
        Thread threadA = new Thread(new Runnable() {
            @Override
            public void run() {
                lockTest.first();
            }
        });
        Thread threadB = new Thread((new Runnable() {
            @Override
            public void run() {
                lockTest.second();
            }
        }));
//        Thread threadC = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                lockTest.third();
//            }
//        });
        threadA.start();
        threadB.start();
//        threadC.start();
    }

    public LockTest(){
        lock = new ReentrantLock();
        secondCondition = lock.newCondition();
        thirdCondition = lock.newCondition();
    }
    public void first() {
        lock.lock();
        try {
            System.out.println("print first");
            secondCondition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void second(){
        lock.lock();
        try {
            System.out.println("run second ");
            secondCondition.await();
            System.out.println("print second");
//            thirdCondition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void third() {
        lock.lock();
        try {
            System.out.println("run third");
            thirdCondition.await();
            System.out.println("print third");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
