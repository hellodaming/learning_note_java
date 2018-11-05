package org.learning.java8.lambda;

import java.util.function.Function; // 功能型函数式接口Function<T, R>  接受一个输入参数T，返回一个结果R
import java.util.function.Consumer; // Consumer<T>
import java.util.function.Predicate; // Predicate<T>
import java.util.function.Supplier;; // Supplier<T>

public class SomeInterface {
	
	
	// 功能型函数式接口Function<T, R>  接受一个输入参数T，返回一个结果R
	public void FunctionDemo(){
		//apply用法
		Function<Integer, Integer> fun1 = x -> x + 1;
		Integer i = fun1.apply(1);
		System.out.printf("输出加1的结果： %d \n", i);
		
		//compose用法
		Function<Integer, String> fun2 = x -> {
			return (new StringBuffer(x.toString())).reverse().toString();
		};
		String str = fun2.compose(fun1).apply(122); //先执行fun1，然后把fun1执行后的结果传递给fun2，然后执行fun2
		System.out.printf("输出compose的结果： %s \n", str);
		
		Function<String, Integer> fun3 = (String x) -> Integer.parseInt(x);
		Integer i2 = fun3.andThen(fun1).apply("999");
		System.out.printf("输出andThen的结果： %d \n", i2);
		
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SomeInterface demo = new SomeInterface();
		demo.FunctionDemo();
	}

}
