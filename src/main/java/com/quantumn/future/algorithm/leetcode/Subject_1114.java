package com.quantumn.future.algorithm.leetcode;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 我们提供了一个类：
 *
 * public class Foo {
 *   public void one() { print("one"); }
 *   public void two() { print("two"); }
 *   public void three() { print("three"); }
 * }
 *
 * 三个不同的线程将会共用一个 Foo 实例。
 *
 *     线程 A 将会调用 one() 方法
 *     线程 B 将会调用 two() 方法
 *     线程 C 将会调用 three() 方法
 *
 * 请设计修改程序，以确保 two() 方法在 one() 方法之后被执行，three() 方法在 two() 方法之后被执行。
 *
 *
 *
 * 示例 1:
 *
 * 输入: [1,2,3]
 * 输出: "onetwothree"
 * 解释:
 * 有三个线程会被异步启动。
 * 输入 [1,2,3] 表示线程 A 将会调用 one() 方法，线程 B 将会调用 two() 方法，线程 C 将会调用 three() 方法。
 * 正确的输出是 "onetwothree"。
 *
 * 示例 2:
 *
 * 输入: [1,3,2]
 * 输出: "onetwothree"
 * 解释:
 * 输入 [1,3,2] 表示线程 A 将会调用 one() 方法，线程 B 将会调用 three() 方法，线程 C 将会调用 two() 方法。
 * 正确的输出是 "onetwothree"。
 *
 *
 *
 * 注意:
 *
 * 尽管输入中的数字似乎暗示了顺序，但是我们并不保证线程在操作系统中的调度顺序。
 *
 * 你看到的输入格式主要是为了确保测试的全面性。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/print-in-order
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * @author: huajun.wu
 * @create: 2020-01-14
 **/
public class Subject_1114 {
    ReentrantLock lock;
    Condition condition;
    int step;

    public Subject_1114() {
        lock = new ReentrantLock();
        condition = lock.newCondition();
        step = 0;
    }
    public void first(Runnable printFirst) throws InterruptedException {
            lock.lock();
            // printFirst.run() outputs "first". Do not change or remove this line.
            printFirst.run();
            step = 1;
            condition.signalAll();
            lock.unlock();
    }

    public void second(Runnable printSecond) throws InterruptedException {
        lock.lock();
        //while must be used here instead of if, because The Thread-2 maybe waked twice, program should judge again once thread waked
        while (step < 1) {
            condition.await();
        }
        // printSecond.run() outputs "second". Do not change or remove this line.
        printSecond.run();
        step = 2;
        condition.signalAll();
        lock.unlock();

    }

    public void third(Runnable printThird) throws InterruptedException {
        lock.lock();
        //while must be used here instead of if, because The Thread-2 maybe waked twice, program should judge again once thread waked
        while (step < 2) {
            condition.await();
        }
        // printThird.run() outputs "third". Do not change or remove this line.
        printThird.run();
        lock.unlock();

    }

    public static void main(String[] args) throws InterruptedException {
        Subject_1114 demo = new Subject_1114();
        Thread thread1 = new Thread() {
            public void run() {
                try {
                    demo.first(()->{
                        System.out.println("one...");
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread2 = new Thread() {
            public void run() {
                try {
                    demo.second(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("two...");
                        }
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        Thread thread3 = new Thread() {
            public void run() {
                try {
                    demo.third(()->{
                        System.out.println("three...");
                    });
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        thread2.start();
        Thread.sleep(1000);
        thread1.start();
        Thread.sleep(1000);
        thread3.start();
    }
}
