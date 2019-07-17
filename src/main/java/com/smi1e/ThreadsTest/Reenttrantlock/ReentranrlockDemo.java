package com.smi1e.ThreadsTest.Reenttrantlock;


import org.junit.Test;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import java.util.concurrent.locks.ReentrantLock;


/**
 * Created by smi1e
 * Date 2019/7/16 14:23
 * Description
 */
public class ReentranrlockDemo {
    public static int sum = 0;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ReentrantLock lock = new ReentrantLock(true);
        for (int i = 0; i <= 10000; i++) {
            final int index = i;
            executorService.execute(() -> {
                try {
                    lock.lock();
                    System.out.println(index+" is adding sum : " + sum++);
                    Thread.sleep(0);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                }
            });
        }
    }

    public void go() {

    }
}
