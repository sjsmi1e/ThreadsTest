package com.smi1e.ThreadsTest.CyclicBarrierDemo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.*;

import static java.lang.Thread.sleep;

/**
 * Created by smi1e
 * Date 2019/4/7 16:25
 * Description 计数器可重置、拥有屏障
 */
@Slf4j
public class CyclicBarrierTest {

    private static CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

    private static int clientNum = 20;//线程数量

    public static void main(String[] args) throws InterruptedException {
        //线程池
        ExecutorService executorService = Executors.newCachedThreadPool();
        for (int i = 0;i<clientNum;i++){
            final int num = i+1;
            Thread.sleep(1000);
            executorService.execute(
                    ()->{
                        try {
                            log.info("线程{}准备",num);
                            cyclicBarrier.await();
                            log.info("线程{}正在运行",num);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        } catch (BrokenBarrierException e) {
                            e.printStackTrace();
                        }
                        //sleep(5000);

                    }
            );
        }
        executorService.shutdown();

    }

}
