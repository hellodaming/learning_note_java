package org.learning.thread.sync;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

// 利用BlockingQueue实现同步；其实BlockingQueue更常用于生产者和消费者模式
// 参考于https://blog.csdn.net/ghsau/article/details/7421217

class AddBlockingQueue {
	BlockingQueue<Integer> i = new LinkedBlockingQueue<Integer>();
	AddBlockingQueue() throws InterruptedException{
		i.put(0);
	}
	

	public synchronized void add() throws InterruptedException {
		i.put(i.take()+1);
	}

	public void print() throws InterruptedException {
		System.out.println("最后结果" + i.take());
	}
}

public class ThreadSyncBlockingQueue {

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

	public static void main(String[] args) throws InterruptedException {
		AddBlockingQueue addnum = new AddBlockingQueue();
		Runnable r = new Runnable() {
			@Override
			public void run() {
				try {
					addnum.add();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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
