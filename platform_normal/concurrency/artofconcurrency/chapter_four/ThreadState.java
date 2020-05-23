package concurrency.artofconcurrency.chapter_four;

import concurrency.support.SleepUtils;
/**
 * @author mengfh
 *
 * @version 2020-5-22����9:15:16
 *
 * @description �߳�״̬
 */
public class ThreadState {
	
    public static void main(String[] args) {
        new Thread(new TimeWaiting (), "TimeWaitingThread").start();
        new Thread(new Waiting(), "WaitingThread").start();
        // ʹ������Blocked�̣߳�һ����ȡ���ɹ�����һ��������
        new Thread(new Blocked(), "BlockedThread-1").start();
        new Thread(new Blocked(), "BlockedThread-2").start();
    }
    
    // ���̲߳��ϵؽ���˯��
    static class TimeWaiting implements Runnable {
        @Override
        public void run() {
            while (true) {
                SleepUtils.second(100);
            }
        }
    }
    
    // ���߳���Waiting.classʵ���ϵȴ�
    static class Waiting implements Runnable {        
        public void run() {
            while (true) {
                synchronized (Waiting.class) {
                    try {
                        Waiting.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
    
    // ���߳���Blocked.classʵ���ϼ����󣬲����ͷŸ���
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