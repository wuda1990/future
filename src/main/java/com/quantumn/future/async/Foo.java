package com.quantumn.future.async;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Foo {
    public Foo() {

        objectList.add(new Object());
        objectList.add(new Object());
    }
    List<Object> objectList = new ArrayList<>(2);

    public void first(Runnable printFirst) throws InterruptedException {


        // printFirst.run() outputs "first". Do not change or remove this line.
        printFirst.run();
        objectList.get(0).notifyAll();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        objectList.get(0).wait();

        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        objectList.get(1).notifyAll();
    }

    public void third(Runnable printThird) throws InterruptedException {

        objectList.get(1).wait();
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
    }

    public static void main(String[] args) {
        Foo foo = new Foo();
        AsyncFoo1 asyncFoo1 = new AsyncFoo1(foo,()-> System.out.println("one"));
        AsyncFoo2 asyncFoo2 = new AsyncFoo2(foo,()-> System.out.println("two"));
        AsyncFoo3 asyncFoo3 = new AsyncFoo3(foo,()-> System.out.println("three"));
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        executorService.submit(asyncFoo2);
        executorService.submit(asyncFoo1);
        executorService.submit(asyncFoo3);


    }
}

class AsyncFoo1 implements Runnable{
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
class AsyncFoo2 implements Runnable{
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
class AsyncFoo3 implements Runnable{
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
