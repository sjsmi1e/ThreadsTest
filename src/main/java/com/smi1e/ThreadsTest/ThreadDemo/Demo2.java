package com.smi1e.ThreadsTest.ThreadDemo;

import lombok.extern.slf4j.Slf4j;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Description ：能同一时间运行的最大线程
 */

@Slf4j
public class Demo2 {
    private static int ThreadNum = 200;
    private static int clientNum = 5000;
    private static int count = 0;

    public static void main(String[] args) {
        final Semaphore semaphore = new Semaphore(ThreadNum);
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0;i<clientNum;i++){
            executorService.execute(
                    ()->{
                        try {
                            semaphore.acquire();
                            count++;
                            semaphore.release();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
            );
        }
        executorService.shutdown();
        log.info("{}",count);
    }
}
