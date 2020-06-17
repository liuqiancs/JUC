package com.mashibing.juc.c_000;

public class DeadThread {
    public static void main(String[] args) {
        Object locObj1 = new Object();
        Object locObj2 = new Object();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (locObj1){
                    System.out.println(Thread.currentThread().getName() + "run");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (locObj2){
                        System.out.println(Thread.currentThread().getName() + "locObj1-->locObj2");
                    }
                }
            }
        },"t1");
        t1.start();
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (locObj2){
                    System.out.println(Thread.currentThread().getName() + "run");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (locObj1){
                        System.out.println(Thread.currentThread().getName() + "locObj2-->locObj1");
                    }
                }
            }
        },"t2");
        t2.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
