package concurrency.artofconcurrency.chapter_four;

import concurrency.support.SleepUtils;
/**
 * @author mengfh
 *
 * @version 2020-5-22上午9:15:16
 *
 * @description 线程状态
 */
public class ThreadState {
	
    public static void main(String[] args) {
        new Thread(new TimeWaiting (), "TimeWaitingThread").start();
        new Thread(new Waiting(), "WaitingThread").start();
        // 使用两个Blocked线程，一个获取锁成功，另一个被阻塞
        new Thread(new Blocked(), "BlockedThread-1").start();
        new Thread(new Blocked(), "BlockedThread-2").start();
    }
    
    // 该线程不断地进行睡眠
    static class TimeWaiting implements Runnable {
        @Override
        public void run() {
            while (true) {
                SleepUtils.second(100);
            }
        }
    }
    
    // 该线程在Waiting.class实例上等待
    static class Waiting implements Runnable {        
        public void run() {
            while (true) {
                synchronized (Waiting.class) {
                    try {
                        Waiting.class.wait();   //要被唤醒才能接着往下执行，否则就停在这里
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    
    // 该线程在Blocked.class实例上加锁后，不会释放该锁
    static class Blocked implements Runnable {
        public void run() {
            synchronized (Blocked.class) {
                while (true) {
                    SleepUtils.second(100); 
                }
            }
        }
    }
    
}