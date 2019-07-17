package com.smi1e.ThreadsTest.Reenttrantlock;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by smi1e
 * Date 2019/7/16 14:56
 * Description
 */
public class TryLockTest {
    private static int sum = 0;

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ReentrantLock lock = new ReentrantLock();

        for (int i = 0 ; i <= 100; i++){
            final int index = i;
            executorService.execute(()->{
                boolean b=false;
                try {
                    if ( b = lock.tryLock(100,TimeUnit.MILLISECONDS)){
                        System.out.println(index + " is adding sum : "+sum++);
                        Thread.sleep(10);
                    }else {
                        System.out.println(index + " isn't has lock !!!");
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    if (b)
                        lock.unlock();
                }

            });
        }
        executorService.shutdown();
    }
}
