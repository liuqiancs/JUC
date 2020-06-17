package com.mashibing.juc.c_000;

public class T04_ThreadState {

    static class MyThread extends Thread {
        @Override
        public void run() {
            System.out.println("MyThread print:"+this.getState());
            for(int i=0; i<5; i++) {
                System.out.println(i);
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }
    }

    public static void main(String[] args) {
        //参考 https://blog.csdn.net/pange1991/article/details/53860651
        Thread t = new MyThread();

        System.out.println("main print:"+t.getState());

        t.start();

        try {
            //t.join();
            t.join(3000);
            System.out.println("123");
            System.out.println("main print:"+t.getState());
            t.join();
            System.out.println("main print:"+t.getState());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }



    }
}
