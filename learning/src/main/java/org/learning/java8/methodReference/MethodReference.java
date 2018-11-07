package org.learning.java8.methodReference;

import java.util.Arrays;
import java.util.List;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;

public class MethodReference {

	public static void main(String[] args) {
		Consumer<String> c = (str) -> System.out.println(str);
		c.accept("一般lambda输出的结果");

		// 对象引用::实例方法名
		Consumer<String> c2 = System.out::println; // 对象引用::实例方法名（System.out就是一个PrintStream类型的对象引用，而println则是一个实例方法名）
		c2.accept("实例方法引用输出的结果"); // 实现抽象方法的参数列表，必须与方法引用方法的参数列表保持一致！至于返回值就不作要求

		// 类名::静态方法名
		// Function<Integer, Integer> f = (x) -> Math.abs(x);
		Function<Integer, Integer> f = Math::abs;
		Integer i = f.apply(-1);
		System.out.printf("静态方法引用 结果 %d \n", i);

		// 类名::实例方法名
		BiPredicate<String, String> bp = String::equals;
		boolean b = bp.test("123", "123");
		System.out.printf("实例方法引用 结果 %b \n", b);

		// 引用构造器
		Function<String, StringBuffer> fun = StringBuffer::new; // 对应StringBuffer的输入参数为String的构造函数
		StringBuffer buffer = fun.apply("1234567890");
		System.out.printf("引用构造器 结果 %s \n", buffer);

		Function<Integer, String[]> t = String[]::new;
		String[] arr = t.apply(3); //new一个容量为3的数组
		arr[0] = "0";
		arr[1] = "1";
		arr[2] = "2";
		//arr[3] = "3";// Exception in thread "main" java.lang.ArrayIndexOutOfBoundsException: 3
		Arrays.asList(arr).forEach(e -> System.out.printf("%s ", e));
		
		System.out.println();
		Function<Integer, Integer[]> t2 = Integer[]::new;
		Integer[] a2 = t2.apply(3); //new一个容量为3的数组
		a2[0] = 0;
		a2[1] = 10;
		a2[2] = 100;
		Arrays.asList(a2).forEach(e -> System.out.printf("%d ", e));
	}

}
