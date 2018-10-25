package org.learning.thread.sync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// ReentrantLock类是可重入、互斥、实现了Lock接口的锁， 
// 参考于https://blog.csdn.net/ghsau/article/details/7421217

class AddNumReentrantLock {
	int i = 0;
	private Lock lock = new ReentrantLock();

	public void add() {
		lock.lock();
		i++;
		lock.unlock();
	}

	public void print() {
		System.out.println("最后结果" + i);
	}
}

public class ThreadSyncReentrantLock {

	public static boolean isDone(List<Thread> list) {
		int i = 0;
		for (; i < list.size(); i++) {
			if (list.get(i).isAlive()) {
				try {
					Thread.sleep(100);
					break;
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println(i);
		if (i == list.size()) { // 谨慎！
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		AddNumReentrantLock addnum = new AddNumReentrantLock();
		Runnable r = new Runnable() {
			@Override
			public void run() {
				addnum.add();
			}
		};
		List<Thread> list = new ArrayList<Thread>();
		for (int i = 0; i < 10000; i++) {
			Thread t = new Thread(r);

			t.start();
			list.add(t);
		}
		while (!isDone(list)) {
			System.out.println("thread running");
		}
		addnum.print();
	}
}
