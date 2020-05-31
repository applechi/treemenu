package com.apple.thread;

import java.util.LinkedList;

/**
* @program: treemenu
*
* @description: 消费者生产者
*
* @author: Apple
*
* @create: 2019-07-20 17:23
**/
public class TestFour<T> {
/**
 * 创建一个同步容器，拥有put和get方法，以及getCount方法(当容器满了，就不能put了；当容器没了，就不能get了)
 * 能够支持2个生产者线程以及10个消费者线程的阻塞调用
 * 使用wait和notify/notifyAll来实现
 */
    private final LinkedList<T> lists = new LinkedList<T>();
    private static int MAX=10;//最多十个元素
    private static int count=0;
    /**
     * 为什么用while而不是if?假设此时容器是满的，wait，当消费者拿走一个，这个时候就要执行wait后面的代码（该执行list.add(t)了），如果是if，就会直接执行lists.add(t)方法，
     * 如果有两个线程，第一个线程执行完，往里面放了一个元素，此时里面有10个，线程1释放锁，线程2得到锁，继续执行（list.add(t)）就会造成里面有11个元素，就会超出容量；如果用while，需要在while处再检查一遍，就不会有问题;99.9%和while一起用
     */
    public synchronized void put(T t){
        try {
            while(lists.size()==MAX){
                this.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lists.add(t);
        ++count;
        this.notifyAll();//通知消费者线程你赶紧消费;为什么不是notify？因为有可能你叫醒的线程又是一个生产者,然后这个生产者又wait，然后程序就死了
    }
    public synchronized T get(){
        T t = null;
        try {
            while(lists.size()==0){
                this.wait();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t=lists.removeFirst();
        count--;
        this.notifyAll();//通知生产者进行生产
        return t;
    }
    public static void main(String[] args) throws InterruptedException {
        final TestFour<String> test = new TestFour<String>();


//启动生产者线程
        for(int i=0;i<2;i++){
            new Thread(new Runnable(){
                public void run() {
                    for(int j=0;j<25;j++){
                        test.put(Thread.currentThread().getName()+""+j);
                    }
                }
            },"p"+i).start();

        }
        Thread.sleep(1000);
        //启动消费者线程
        for(int i=0;i<10;i++){
            new Thread(new Runnable(){
                public void run() {
                    for(int j=0;j<5;j++){
                        System.out.println(test.get());
                    }
                }
            },"c"+i).start();

        }
    }
}

