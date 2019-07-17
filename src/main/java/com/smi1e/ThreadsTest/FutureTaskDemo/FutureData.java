package com.smi1e.ThreadsTest.FutureTaskDemo;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by smi1e
 * Date 2019/7/17 10:08
 * Description
 */
public class FutureData<V> implements Data {

    public RealData realData = null;
    private boolean isGetReal = false;

    public synchronized void setRealData(RealData realData) throws InterruptedException {
        if (isGetReal)
            return;
        this.realData = realData;
        isGetReal = true;
        notifyAll();
    }


    @Override
    public synchronized V getData() {
        while (true) {
            if (!isGetReal) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return (V) this.realData.data;
        }
    }
}

