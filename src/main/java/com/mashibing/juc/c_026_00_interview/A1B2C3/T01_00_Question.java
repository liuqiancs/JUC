package com.mashibing.juc.c_026_00_interview.A1B2C3;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.LockSupport;

public class T01_00_Question {

    static Thread t1 = null,t2 = null;

    public static void main(String[] args) {
        //要求用线程顺序打印A1B2C3....Z26
        char[] aC = "ABCDEFG".toCharArray();
        char[] aI = "1234567".toCharArray();
        t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(char str : aC){
                    System.out.print(str);
                    LockSupport.unpark(t2);
                    LockSupport.park();
                }
            }
        },"t1");

        t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                for(char inte : aI){
                    LockSupport.park();
                    System.out.print(inte);
                    LockSupport.unpark(t1);
                }
            }
        },"t2");

        t1.start();
        t2.start();
    }
}
