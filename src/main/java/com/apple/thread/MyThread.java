package com.apple.thread;

/**
 * @program: treemenu
 * @description: 多线程测试
 * @author: Apple
 * @create: 2019-07-19 20:16
 **/
public class MyThread implements Runnable {

    public int money=100;

    /**
     * 这里如果不加synchronized，两个线程启动会争夺进入这个方法操作肯呢个导致数据不同步
     */
    @Override
    public  synchronized void run() {
        //比如有两个线程执行时，不加同步，可能一个线程还没有执行完的时候，就都进入了这个条件执行，也会导致数据不同步
       /* if (money>0){
            System.out.println("---Apple Boy---"+(money-100));
        }*/
        for (int i = 0; i < 10; i++) {
            System.out.println(Thread.currentThread().getName()+"  :  "+i);
        }

    }

    public static void main(String[] args) {
        System.out.println(Thread.currentThread().getName());
        MyThread myThread1 = new MyThread();
        Thread   thread    = new Thread(myThread1);
        MyThread myThread2 = new MyThread();
        Thread   thread2   = new Thread(myThread1);
        thread.start();
        thread2.start();

    }
}
