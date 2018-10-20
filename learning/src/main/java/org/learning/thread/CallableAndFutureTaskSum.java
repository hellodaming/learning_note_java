package org.learning.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

// 来源于http://josh-persistence.iteye.com/blog/2145120
class ConcurrentCalculator {

	private ExecutorService exec;
	private CompletionService completionService;  
	private int cpuCoreNumber;
	//private List<Future<Long>> tasks = new ArrayList<Future<Long>>();

	// 构造函数，获取cpu个数，并且根据cpu个数分配固定线程数的线程池
	public ConcurrentCalculator() {
		cpuCoreNumber = Runtime.getRuntime().availableProcessors();
		System.out.printf("本机CPU核数：  %d\n", cpuCoreNumber);
		exec = Executors.newFixedThreadPool(cpuCoreNumber);
		
		completionService = new ExecutorCompletionService<Long>(exec); 
	}
	
	public Long sum(final int[] numbers) {
		// 根据CPU核心个数拆分任务，创建FutureTask并提交到Executor
		for (int i = 0; i < cpuCoreNumber; i++) {
			int increment = numbers.length / cpuCoreNumber + 1;
			int start = increment * i;
			int end = increment * i + increment;
			if (end > numbers.length)
				end = numbers.length;
			SumCalculator subCalc = new SumCalculator(numbers, start, end);
			//FutureTask<Long> task = new FutureTask<Long>(subCalc);
			//tasks.add(task);
			if (!exec.isShutdown()) {
				//exec.submit(task);
				completionService.submit(subCalc);  
			}
		}
		return getResult();
	}
	
	// 内部类
	class SumCalculator implements Callable<Long> {
		private int[] numbers;
		private int start;
		private int end;

		public SumCalculator(final int[] numbers, int start, int end) { //使用构造方法传递参数
			this.numbers = numbers;
			this.start = start;
			this.end = end;
		}

		public Long call() throws Exception {
			Long sum = 0l;
			for (int i = start; i < end; i++) {
				sum += numbers[i];
			}
			return sum;
		}
	}

	/**
	 * 迭代每个只任务，获得部分和，相加返回
	 * @return
	 */
//	public Long getResult() {
//		Long result = 0l;
//		for (Future<Long> task : tasks) {
//			try {
//				// 如果计算未完成则阻塞 会阻塞！！！！
//				Long subSum = task.get();
//				result += subSum;
//			} catch (InterruptedException e) {
//				e.printStackTrace();
//			} catch (ExecutionException e) {
//				e.printStackTrace();
//			}
//		}
//		return result;
//	}
	
	
	public Long getResult() {  
        Long result = 0l;  
        for (int i = 0; i < cpuCoreNumber; i++) {              
            try {  
                Long subSum = (Long) completionService.take().get();  
                result += subSum;             
            } catch (InterruptedException e) {  
                e.printStackTrace();  
            } catch (ExecutionException e) {  
                e.printStackTrace();  
            }  
        }  
        return result;  
    }  

	public void close() {
		exec.shutdown();
	}
}


public class CallableAndFutureTaskSum {

	public static void main(String[] args){
		int[] numbers = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10};  
		ConcurrentCalculator calc = new ConcurrentCalculator();  
		Long sum = calc.sum(numbers);  
		System.out.println(sum);  
		calc.close();  
	}
	
}