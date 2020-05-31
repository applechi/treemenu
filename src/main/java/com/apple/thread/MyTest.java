package com.apple.thread;

import java.io.Serializable;

/**
* @program: treemenu
*
* @description: 测试类
*
* @author: Apple
*
* @create: 2019-08-01 23:32
**/
public class MyTest implements Serializable {

    public static void main(String[] args) {
        int a;
        a=6;
        System.out.print(a);
        System.out.print(a++);
        System.out.print(a);
    }
}
