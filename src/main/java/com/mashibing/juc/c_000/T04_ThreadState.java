package com.mashibing.juc.c_000;

public class T04_ThreadState {
    public static void main(String[] args) {
        Object lockObj = new Object();
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() + " state " +Thread.currentThread().getState());
            }
        },"t1");
        System.out.println(t1.getName() + " state " +t1.getState());
        t1.start();
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t2");
        t2.start();
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (this){
                        this.wait();
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t3");
        t3.start();
        Thread t4 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    synchronized (lockObj){
                        Thread.sleep(2000);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        },"t4");
        t4.start();
        Thread t5 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (lockObj){
                }
            }
        },"t5");
        t5.start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(t1.getName() + " state " +t1.getState());
        System.out.println(t2.getName() + " state " +t2.getState());
        System.out.println(t3.getName() + " state " +t3.getState());
        System.out.println(t4.getName() + " state " +t4.getState());
        System.out.println(t5.getName() + " state " +t5.getState());

    }
}
