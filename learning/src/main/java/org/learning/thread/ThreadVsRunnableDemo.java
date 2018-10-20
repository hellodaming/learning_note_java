package org.learning.thread;

/**
 * Thread和Runnable是实现java多线程的2种方式，runable是接口，thread是类，建议使用runable实现 java多线程，不管如何，最终都需要通过thread.start()来使线程处于可运行状态。
 * Runnable比Thread优势点一：如果一个类继承Thread，则不适合资源共享。但是如果实现了Runable接口的话，则很容易的实现资源共享，即可以共享一个对象实例。
 * 出处：https://blog.csdn.net/Evankaka/article/details/44153709
 * */

//如果让一个线程实现Runnable接口，那么当调用这个线程的对象开辟多个线程时，可以让这些线程调用同一个变量；
class RunnableDemo1 implements Runnable{
	private volatile int i = 0; //同一个变量
	@Override
	public void run() {
		// TODO Auto-generated method stub
		for(; i<10000; i++){
			System.out.println(Thread.currentThread().getName()+"-->"+i+" Runnable");
		}
	}
}

//若这个线程是由继承Thread类而来，则要通过内部类来实现上述功能，利用的就是内部类可任意访问外部变量这一特性。
class ThreadDemo1{
	private int i = 0; //同一个变量
	class innerClass extends Thread{ //不能使用匿名内部类，若是匿名内部类，根据java语法要求上面的变量i必须为final类型，这样多线程也无法实现
		@Override
		public void run() {
			// TODO Auto-generated method stub
			for(; i<10; i++){
				System.out.println(Thread.currentThread().getName()+"-->"+i+" Thread");
			}
		}
	}
	Thread getThread(){ //真正产生线程
		return new innerClass();
	}
}


public class ThreadVsRunnableDemo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("implements Runnable的实现方式");
		Runnable r = new RunnableDemo1();
		(new Thread(r)).start();
		(new Thread(r)).start();
		(new Thread(r)).start();

		try {
			Thread.sleep(500); //让主线程阻塞一下，让上面的Runnable多线程任务完成
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("extends Thread的实现方式");
		ThreadDemo1 t = new ThreadDemo1();
		Thread t1 = t.getThread();
		Thread t2 = t.getThread();
		Thread t3 = t.getThread();
		t1.start();
		t2.start();
		t3.start();
	}

}
