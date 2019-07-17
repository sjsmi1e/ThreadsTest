package com.smi1e.ThreadsTest.FutureTaskDemo;

import org.junit.Test;

/**
 * Created by smi1e
 * Date 2019/7/17 10:40
 * Description 自定义future模式(立即返回future数据，10S后返回真实数据)
 */

public class Main {
    @Test
    public void test() throws InterruptedException {
//        FutureData<String> futureData = new FutureData<>();
//        System.out.println(futureData.getData());
        Client client = new Client();
        Data request = client.request(132);
        System.out.println("请求完毕");
//        Thread.sleep(2000);
        System.out.println(request.getData());
    }

    class Client {
        public Data request(final Object queryStr) throws InterruptedException {
            final FutureData future = new FutureData();
            new Thread() {
                public void run() {// RealData的构建很慢，//所以在单独的线程中进行
                    try {
                        RealData realdata = new RealData(queryStr);
                        future.setRealData(realdata);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }.start();
            return future; // FutureData会被立即返回
        }
    }

}
