package com.quantumn.future.async;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
//    final ReentrantLock lock ;
//    final Condition secondCondition;
//    final Condition thirdCondition;

    public static ReentrantLock lock=new ReentrantLock();
    public static Condition condition =lock.newCondition();

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
        threadB.start();
        threadA.start();
//        threadC.start();
    }

//    public LockTest(){
//        lock = new ReentrantLock();
//        secondCondition = lock.newCondition();
//        thirdCondition = lock.newCondition();
//    }
    public void first() {
        lock.lock();
        try {
            System.out.println("print first");
            condition.signal();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void second(){
        lock.lock();
        try {
            System.out.println("second is waiting...");
            condition.await();
            System.out.println("print second");
//            thirdCondition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

//    public void third() {
//        lock.lock();
//        try {
//            System.out.println("run third");
//            thirdCondition.await();
//            System.out.println("print third");
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }finally {
//            lock.unlock();
//        }
//    }
}
