package com.smi1e.ThreadsTest.FutureTaskDemo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * Created by smi1e
 * Date 2019/4/7 16:51
 * Description ：获得返回结果的线程
 */
@Slf4j
public class FutureTaskDemo {


    private static FutureTask<Integer> futureTask = new FutureTask<Integer>(new Callable<Integer>() {
        @Override
        public Integer call() throws Exception {
            return 1;
        }
    });

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Thread thread = new Thread(futureTask);
        thread.start();
        log.info("返回结果是:{}",futureTask.get());
    }

}
