package org.learning.thread;

class ThreadClass extends Thread {
	private Thread t;
	private String threadName;

	public ThreadClass(String threadName) {
		this.threadName = threadName;
		System.out.println("Creating " + threadName);
	}

	public void run() { //run()方法是多线程程序的一个约定。所有的多线程代码都在run方法里面。
		System.out.println("Running " + threadName);
		for (int i = 0; i < 5; i++) {
			System.out.println(threadName + " " + i);
//			try {
//				Thread.sleep(50);//Thread.sleep()方法调用目的是不让当前线程独自霸占该进程所获取的CPU资源，以留出一定时间给其他线程执行的机会。
//			} catch (InterruptedException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
		}
		System.out.println("Running " + threadName + " end ");
	}
}

public class ThreadDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Thread t1 = new ThreadClass("thread-1");
		t1.start();//start()方法的调用后并不是立即执行多线程代码，而是使得该线程变为可运行态（Runnable），什么时候运行是由操作系统决定的。从程序运行的结果可以发现，多线程程序是乱序执行。因此，只有乱序执行的代码才有必要设计为多线程。
		//t1.run();
		
		Thread t2 = new ThreadClass("thread-2");
		t2.start();
		//t2.run();
		
		//让主线程等待一下thread-1和thread-2两个线程结束后再结束主线程
		try {
			t1.join(0);
			t2.join(0);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("main thread end ");

	}
}
