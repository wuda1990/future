package com.quantumn.future.async;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class Foo {
    Object lock1;
    Object lock2;

    public Foo() {
        lock1 = new Object();
        lock2 = new Object();
    }

    public void first(Runnable printFirst) throws InterruptedException {

         synchronized (lock1) {
            printFirst.run();
            lock1.notifyAll();
        }
    }

    public void second(Runnable printSecond) throws InterruptedException {
        synchronized (lock1){
            lock1.wait();
            // printSecond.run() outputs "second". Do not change or remove this line.
            synchronized (lock2) {
                printSecond.run();
                lock2.notifyAll();
            }
        }

    }

    public void third(Runnable printThird) throws InterruptedException {
        synchronized (lock2) {
                lock2.wait();
                // printThird.run() outputs "third". Do not change or remove this line.
                printThird.run();
        }

        printThird.run();
    }

    public static void main(String[] args) {
        FooReentrant foo = new FooReentrant();
        AsyncFoo1 asyncFoo1 = new AsyncFoo1(foo,()-> System.out.println("one"));
        AsyncFoo2 asyncFoo2 = new AsyncFoo2(foo,()-> System.out.println("two"));
        AsyncFoo3 asyncFoo3 = new AsyncFoo3(foo,()-> System.out.println("three"));
        asyncFoo1.start();
        asyncFoo2.start();
        asyncFoo3.start();
    }
}

class FooReentrant extends Foo{
    ReentrantLock lock ;
    Condition secondCondition;
    Condition thirdCondition;
    public FooReentrant(){
        lock = new ReentrantLock();
        secondCondition = lock.newCondition();
        thirdCondition = lock.newCondition();
    }
    public void first(Runnable printFirst) {
        lock.lock();
        System.out.println("run first");
        printFirst.run();
        secondCondition.signal();
        lock.unlock();
    }

    public void second(Runnable printSecond){
        lock.lock();
        try {
            System.out.println("run second ");
            secondCondition.await();
            System.out.println("print second");
            printSecond.run();
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
            System.out.println("run third");
            thirdCondition.await();
            System.out.println("print third");
            printThird.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            lock.unlock();
        }
    }
}

class AsyncFoo1 extends Thread{
    Foo foo;
    Runnable runnable;

    public AsyncFoo1(Foo foo, Runnable runnable) {
        this.foo = foo;
        this.runnable = runnable;
    }


    @Override
    public void run() {
        try {
            foo.first(runnable);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class AsyncFoo2 extends Thread{
    Foo foo;
    Runnable runnable;

    public AsyncFoo2(Foo foo, Runnable runnable) {
        this.foo = foo;
        this.runnable = runnable;
    }


    @Override
    public void run() {
        try {
            foo.second(runnable);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class AsyncFoo3 extends Thread{
    Foo foo;
    Runnable runnable;

    public AsyncFoo3(Foo foo, Runnable runnable) {
        this.foo = foo;
        this.runnable = runnable;
    }


    @Override
    public void run() {
        try {
            foo.third(runnable);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
