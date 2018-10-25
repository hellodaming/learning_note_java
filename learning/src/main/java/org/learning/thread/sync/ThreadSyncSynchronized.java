package org.learning.thread.sync;

import java.util.ArrayList;
import java.util.List;

// 同步方法
// 参考于https://blog.csdn.net/ghsau/article/details/7421217

class AddNumSynchronized {
	int i = 0;

	public synchronized void add() {
		i++;
	}

	public void print() {
		System.out.println("最后结果" + i);
	}
}

public class ThreadSyncSynchronized {

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
		AddNumSynchronized addnum = new AddNumSynchronized();
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
