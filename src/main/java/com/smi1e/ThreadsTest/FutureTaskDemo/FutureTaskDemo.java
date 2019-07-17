package com.smi1e.ThreadsTest.FutureTaskDemo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

import static java.lang.Thread.sleep;

/**
 * Created by smi1e
 * Date 2019/4/7 16:51
 * Description ：获得返回结果的线程（jdk内置）
 */
@Slf4j
public class FutureTaskDemo {


    private static FutureTask<Integer> futureTask = new FutureTask<Integer>(()->{
        sleep(1000);
        return 1;
    });

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Thread thread = new Thread(futureTask);
        thread.start();
        log.info("返回结果是:{}",futureTask.get());

    }

}
