package com.apple.thread;

/**
 * sleep()方法来自于Thread类，从源码给出的解释来看，sleep()方法可以做到如下几点：
 *
 * （1）首先，调用sleep()之后，会引起当前执行的线程进入暂时中断状态，也即睡眠状态。
 *
 * （2）其次，虽然当前线程进入了睡眠状态，但是依然持有monitor对象。
 *
 * （3）在中断完成之后，自动进入唤醒状态从而继续执行代码。
 *
 *
 * 我们可以看出程序虽然在运行过程中中断了3秒，但是在3秒结束之后依然会继续执行代码，直到运行结束。在睡眠的期间内，线程会一直持有monitor对象。
 *
 * 总结：
 * 那么从以上的理论和实践来分析，我们能得出如下结论：
 *
 * （1）在线程的运行过程中，调用该线程持有monitor对象的wait()方法时，该线程首先会进入等待状态，并将自己持有的monitor对象释放。
 *
 * （2）如果一个线程正处于等待状态时，那么唤醒它的办法就是开启一个新的线程，通过notify()或者notifyAll()的方式去唤醒。当然，需要注意的一点就是，必须是同一个monitor对象。
 *
 * （3）sleep()方法虽然会使线程中断，但是不会将自己的monitor对象释放，在中断结束后，依然能够保持代码继续执行。
 *
 *
 */
public class SleepThread {
    public static void main(String[] args) {
        SleepThread main = new SleepThread();
        main.startThread();
    }

    /**
     * 启动线程
     */
    public void startThread() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("开始执行线程。。。");
                System.out.println("进入睡眠状态。。。");
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("线程结束。。。");
            }
        });
        t.start();
    }
}