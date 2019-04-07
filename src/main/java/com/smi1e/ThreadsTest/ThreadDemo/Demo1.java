package com.smi1e.ThreadsTest.ThreadDemo;


import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

/**
 * 简单测试多线程并发访问
 * CountDownLatch  减到0时等待的线程继续执行
 */
@Slf4j
public class Demo1 {
    //代表线程数
    public static int ThreadNum = 100;
    public static CountDownLatch countDownLatch = new CountDownLatch(ThreadNum);

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        Thread[] threads = new Thread[ThreadNum];
        //初始化线程
        for (int i=0;i<ThreadNum;i++ ){
            threads[i] = new Thread( ()->{
                list.add((int) Math.random());
                countDownLatch.countDown();
            }
            );
        }
        //运行线程
        for (int i=0;i<ThreadNum;i++){
            threads[i].start();
        }

        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //System.out.println(list.size());
        log.info(""+list.size());
    }
}
