package org.learning.thread;

class RunnableClass implements Runnable {//实现Runnable接口，使得该类有了多线程类的特征
	private Thread t;
	private String threadName;

	public RunnableClass(String threadName) {
		this.threadName = threadName;
		System.out.println("Creating " + threadName);
	}

	@Override
	public void run() { //Runnable中唯一的方法，为程序中的另一个并发执行的线程建立进入点，这个线程在run()返回时结束
		// TODO Auto-generated method stub

		System.out.println("Running " + threadName);
		for (int i = 0; i < 5; i++) {
			System.out.println(threadName + " " + i);
//				try {
//					Thread.sleep(50);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
		}
	}

	
}

public class RunnableDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Runnable r1 = new RunnableClass("r1"); //先new一个实现Runnable的实例 ， 即建立一个可运行的（Runnable）对象
		
		//不管是扩展Thread类还是实现Runnable接口来实现多线程，最终还是通过Thread的对象的API来控制线程的，熟悉Thread类的API是进行多线程编程的基础。
		Thread t1 = new Thread(r1); //先通过Thread类的构造方法Thread(Runnable target) 构造出对象， 即在r1对象上构造一个线程
		t1.start(); //Thread对象的start()方法来运行多线程代码， 即开始运行线程
		
		Runnable r2 = new RunnableClass("r2");
		Thread t2 = new Thread(r2);
		t2.start();
		
		while(t1.isAlive()|| t2.isAlive()){ // 等待直到所有线程结束，不然可能会出现：主线程先结束，然后子线程才结束
		}
		System.out.println("Stop Main thread ending");
		
		
	}

}
