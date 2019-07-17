package com.smi1e.ThreadsTest.Reenttrantlock;

import com.sun.jmx.remote.internal.ArrayQueue;

import java.util.Queue;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by smi1e
 * Date 2019/7/16 15:22
 * Description 生产者消费者模式
 */
public class ConditionTest {
    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock(true);
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        ExecutorService executorService2 = Executors.newFixedThreadPool(10);
        Condition proCondition = lock.newCondition();
        Condition comCondition = lock.newCondition();
        int qSize = 20;
        Queue<String> queue = new ConcurrentLinkedQueue<String>();
//        ExecutorService executorService = new ThreadPoolExecutor(5,10,10L,
//                TimeUnit.MILLISECONDS,new ArrayBlockingQueue<>(20),new ThreadPoolExecutor.AbortPolicy());
        for (int i = 0; i < 5; i++) {
            int index = i;
            executorService.execute(() -> {
//                System.out.println("生产者"+index+": " + Thread.currentThread().getName());
                while (true) {
                    try {
                        lock.lock();
                        if (queue.size() == qSize) {
                            System.out.println("队列已经满了，请等待。。。");
                            proCondition.await();
                        } else {
                            System.out.println("生产者"+index+"正在存入数据：" + Thread.currentThread().getName());
                            Thread.sleep(1000);
                            queue.offer(Thread.currentThread().getName());
                            comCondition.signalAll();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            });
        }

        for (int i = 0; i < 5; i++) {
            int index = i;
            executorService2.execute(() -> {
//                System.out.println("消费者"+index+": " + Thread.currentThread().getName());
                while (true) {
                    try {
                        lock.lock();
                        if (queue.isEmpty()) {
                            System.out.println("队列已经空了，请等待。。。");
                            comCondition.await();
                        } else {
                            System.out.println("消费者"+index+"正在取出数据：" + Thread.currentThread().getName());
                            Thread.sleep(10);
                            String peek = queue.poll();
                            System.out.println("消费者"+index+"取出数据：" + peek);
                            proCondition.signalAll();
                        }
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } finally {
                        lock.unlock();
                    }
                }
            });
        }

    }
}
