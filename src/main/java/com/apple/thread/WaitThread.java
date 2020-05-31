package com.apple.thread;

import java.util.HashMap;
import java.util.Map;

/**
 * 首先wait()是属于Object类的方法，从源码给出的解释来看，wait()方法可以做到如下几点：
 *
 * （1）首先，调用了wait()之后会引起当前线程处于等待状状态。
 *
 * （2）其次，每个线程必须持有该对象的monitor。如果在当前线程中调用wait()方法之后，该线程就会释放monitor的持有对象并让自己处于等待状态。
 *
 * （3）如果想唤醒一个正在等待的线程，那么需要开启一个线程通过notify()或者notifyAll()方法去通知正在等待的线程获取monitor对象。如此，该线程即可打破等待的状态继续执行代码。
 *
 *
 *
 *
 * 在执行线程和线程结束之间，我们先让该线程获取object对象作为自己的object's monitor，然后调用了object对象的*wait()方法从而让其进入等待状态。
 * 程序在未被唤醒之后，将不再打印“线程结束”，并且程序无法执行完毕一直处于等待状态。
 */
public class WaitThread {
    public static void main(String[] args) {
        WaitThread main = new WaitThread();
        Map<String,String> map=new HashMap<>();
        main.startThread();
    }

    /**
     * 线程锁
     */
    private final Object object = new Object();

    /**
     * 启动线程
     */
    public void startThread() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("开始执行线程。。。");
                System.out.println("进入等待状态。。。");
                synchronized (object) {
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("线程结束。。。");
            }
        });
        t.start();
    }
}