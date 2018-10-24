package org.learning.thread.sync;
/*
 * 使用volatile关键子来修饰共享遍历i，并不能保证i最后得到我们想要的结果；即不能保证线程安全！！！
 * 
 * 参考于https://blog.csdn.net/ghsau/article/details/7421217
 * 
 */

import java.util.ArrayList;
import java.util.List;

class AddNumVolatile {
	// 网上资料显示：Volatile真正解决的问题是 JVM 在-server模式下(注意普通运行模式下没有此问题),
	// 线程优先取用自己的线程私有stack中的变量值, 而不是公共堆中的值, 造成变量值老旧的问题.
	// 换句话说, Volatile强制要求了所有线程在使用变量的时候要去公共内存堆中获取值, 不可以偷懒使用自己的.
	// Volatile绝对不保证原子性, 原子性只能用Synchronized同步修饰符实现.
	volatile int i = 0;

	public void add() {
		i++;
	}

	public void print() {
		System.out.println("最后结果" + i);
	}
}

public class ThreadSyncVolatile {

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
		if (i == list.size()) {
			return true;
		} else {
			return false;
		}
	}

	public static void main(String[] args) {
		AddNumVolatile addnum = new AddNumVolatile();
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
