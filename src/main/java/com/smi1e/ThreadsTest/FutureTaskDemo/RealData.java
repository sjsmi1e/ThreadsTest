package com.smi1e.ThreadsTest.FutureTaskDemo;

/**
 * Created by smi1e
 * Date 2019/7/17 10:09
 * Description
 */
public class RealData<V> implements Data {
    protected V data;

    public RealData(V data) throws InterruptedException {
        Thread.sleep(10000);
        this.data=data;
    }

    @Override
    public V getData() {
        return this.data;
    }
}
