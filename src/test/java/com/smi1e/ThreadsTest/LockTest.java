package com.smi1e.ThreadsTest;

import org.junit.Test;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author smi1e
 * Date 2019/7/19 11:11
 * Description
 * 偏向锁，轻量级锁，自旋锁总结
 * 不是Java语言层面的锁优化方法
 * 内置于JVM中的获取锁的优化方法和获取锁的步骤
 * – 偏向锁可用会先尝试偏向锁
 * – 轻量级锁可用会先尝试轻量级锁
 * – 以上都失败，尝试自旋锁
 * – 再失败，尝试普通锁，使用OS互斥量在操作系统层挂起
 */

public class LockTest {

    /**
     * 锁消除
     * -server -XX:+DoEscapeAnalysis -XX:+EliminateLocks  550ms
     * -server -XX:+DoEscapeAnalysis -XX:-EliminateLocks  990ms
     * 虚拟机自动检测变量是否跨作用域，当不跨时自动将原本存在的锁消除
     */
    @Test
    public void test() {
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            craeteStringBuffer("JVM", "Diagnosis");
        }
        long bufferCost = System.currentTimeMillis() - start;
        System.out.println("craeteStringBuffer: " + bufferCost + " ms");
    }

    public static String craeteStringBuffer(String s1, String s2) {
        StringBuffer sb = new StringBuffer();
        sb.append(s1);
        sb.append(s2);
        return sb.toString();
    }


    /**
     * 偏向锁
     *  大部分情况是没有竞争的，所以可以通过偏向来提高性能
     *  所谓的偏向，就是偏心，即锁会偏向于当前已经占有锁的线程
     *  将对象头Mark的标记设置为偏向，并将线程ID写入对象头Mark
     *  只要没有竞争，获得偏向锁的线程，在将来进入同步块，不需要做同步
     *  当其他线程请求相同的锁时，偏向模式结束
     * -XX:+UseBiasedLocking
     * – 默认启用
     * 3688 3798 3698（启用延迟没有设为0）
     * 3609 3521 3558（启用延迟设为0）
     */
    private static List<Integer> numberList = new Vector<Integer>();

    @Test
    public void test2() {
        long begin = System.currentTimeMillis();
        int count = 0;
        int startnum = 0;
        while (count < 10000000) {
            numberList.add(startnum);
            startnum += 2;
            count++;
        }
        long end = System.currentTimeMillis();
        System.out.println(end - begin);
    }
}

