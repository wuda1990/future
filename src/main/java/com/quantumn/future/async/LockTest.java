package com.quantumn.future.async;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class LockTest {
    final ReentrantLock lock ;
    final Condition secondCondition;
    final Condition thirdCondition;
    volatile boolean isFirstFinished = false;
    volatile boolean isSecondFinished = false;

    public static void main(String[] args) {
        LockTest lockTest = new LockTest();
        Thread threadA = new Thread(() -> lockTest.first(()-> System.out.println("one")));
        Thread threadB = new Thread((() -> lockTest.second(()-> System.out.println("two"))));
        Thread threadC = new Thread(() -> lockTest.third(()-> System.out.println("three")));

        threadA.start();
        threadB.start();
        threadC.start();
    }

    public LockTest(){
        lock = new ReentrantLock();
        secondCondition = lock.newCondition();
        thirdCondition = lock.newCondition();
    }
    public void first(Runnable printOne) {
        lock.lock();
        try {
            printOne.run();
            isFirstFinished = true;
            secondCondition.signal();
        } finally {
            lock.unlock();
        }
    }

    public void second(Runnable printTwo){
        lock.lock();
        try {
            while(!isFirstFinished) {
                secondCondition.await();
            }
            printTwo.run();
            isSecondFinished = true;
            thirdCondition.signal();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }

    public void third(Runnable printThird) {
        lock.lock();
        try {
            while (!isSecondFinished) {
                thirdCondition.await();
            }
            printThird.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}
