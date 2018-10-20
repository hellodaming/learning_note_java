package thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.Random;

class CallableMethod implements Callable<Object>{

	@Override
	public Object call() throws Exception {
		// TODO Auto-generated method stub
		Random r = new Random();
		int randomNum = java.util.concurrent.ThreadLocalRandom.current().nextInt(10, 100 + 1);
		System.out.println(Thread.currentThread().getName()+" " +randomNum);
		Thread.sleep(randomNum);
		return randomNum;
	}
	
}

public class CallableAndFutureTask {

	public static void main(String[] args){
		Callable<Object> callableMethod = new CallableMethod(); //创建Callable方法， 这样就可以有返回值
		FutureTask<Object> futureTask1 =  new FutureTask<Object>(callableMethod);
		FutureTask<Object> futureTask2 =  new FutureTask<Object>(callableMethod);
		Thread t1 = new Thread(futureTask1, "Thread 1");
		Thread t2 = new Thread(futureTask2, "Thread 2");
		t1.start();
		t2.start();
		try {
			//int i1 = (int) futureTask1.get();  //如果异步操作还没有完成，则，get()会使当前线程阻塞
			int i1 = (int) futureTask1.get(5, TimeUnit.SECONDS);  //会阻塞线程，设置了超时时间为5秒，超过5秒就抛出TimeoutException
			System.out.printf("输出i1=%d\n", i1);
			int i2 = (int) futureTask2.get();
			System.out.printf("输出i2=%d\n", i2);
		} catch (InterruptedException | ExecutionException | TimeoutException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
}
