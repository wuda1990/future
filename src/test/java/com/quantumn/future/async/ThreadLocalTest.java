package com.quantumn.future.async;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Random;
import java.util.concurrent.CompletableFuture;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ThreadLocalTest implements Runnable{
    //创建线程局部变量studentLocal，在后面你会发现用来保存Student对象
    private final static ThreadLocal studentLocal = new ThreadLocal();

    @Test
    public void test1() throws InterruptedException {
        ThreadLocalTest td = new ThreadLocalTest();
        Thread t1 = new Thread(td, "a");
        Thread t2 = new Thread(td, "b");
        t1.start();
        t2.start();
        Thread.sleep(3000);
    }

    @Test
    public void test2() {
        CompletableFuture future1 = CompletableFuture.runAsync(()->{
            Thread.currentThread().setName("a");
            accessStudent();
        });
        CompletableFuture<Integer> future2 = CompletableFuture.supplyAsync(()->{
            Thread.currentThread().setName("b");
            accessStudent();
            return 200;
        });
        CompletableFuture allFuture = CompletableFuture.allOf(future1, future2);
        allFuture.join();

        System.out.println("all done");
    }

    public void run() {
        accessStudent();
    }

    /**
     * 示例业务方法，用来测试
     */
    public void accessStudent() {
        //获取当前线程的名字
        String currentThreadName = Thread.currentThread().getName();
        System.out.println(currentThreadName + " is running!");
        //产生一个随机数并打印
        Random random = new Random();
        int age = random.nextInt(100);
        System.out.println("thread " + currentThreadName + " set age to:" + age);
        //获取一个Student对象，并将随机数年龄插入到对象属性中
        Student student = getStudent();
        student.setAge(age);
        System.out.println("thread " + currentThreadName + " first read age is:" + student.getAge());
        try {
            Thread.sleep(500);
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        System.out.println("thread " + currentThreadName + " second read age is:" + student.getAge());
    }

    protected Student getStudent() {
        //获取本地线程变量并强制转换为Student类型
        Student student = (Student) studentLocal.get();
        //线程首次执行此方法的时候，studentLocal.get()肯定为null
        if (student == null) {
            //创建一个Student对象，并保存到本地线程变量studentLocal中
            student = new Student();
            studentLocal.set(student);
        }
        return student;
    }
}

class Student {
    private int age = 0;   //年龄

    public int getAge() {
        return this.age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
