package org.learning.java8.lambda;

import java.util.function.Function; // 功能型函数式接口Function<T, R>  接受一个输入参数T，返回一个结果R
import java.util.function.Consumer; // 消费型函数式接口Consumer<T>  接受一个输入参数T并且无返回的操作
import java.util.function.Predicate; // 断言型函数式接口Predicate<T>  接受一个输入参数，返回一个布尔值结果
import java.util.function.Supplier;; // 供给型函数式接口Supplier<T> 无参数，返回一个结果。

public class SomeInterface {

	// 功能型函数式接口Function<T, R> 接受一个输入参数T，返回一个结果R
	public void FunctionDemo() {
		// apply用法
		Function<Integer, Integer> fun1 = x -> x + 1;
		Integer i = fun1.apply(1);
		System.out.printf("输出FunctionDemo加1的结果： %d \n", i);

		// compose用法
		Function<Integer, String> fun2 = x -> {
			return (new StringBuffer(x.toString())).reverse().toString();
		};
		String str = fun2.compose(fun1).apply(122); // 先执行fun1，然后把fun1执行后的结果传递给fun2，然后执行fun2
		System.out.printf("输出FunctionDemo compose的结果： %s \n", str);

		// andThen用法
		Function<String, Integer> fun3 = (String x) -> Integer.parseInt(x);
		Integer i2 = fun3.andThen(fun1).apply("999");
		System.out.printf("输出FunctionDemo andThen的结果： %d \n", i2);

		// identity用法
		Function<String, String> fun4 = Function.identity();// 对于函数式接口，形如这样的表达式（x->x）能够实例化一个对象。并且该表达式正是那个唯一的抽象方法的实现。使用identity方法来产生一个Function对象
		String str2 = fun4.apply("猜到输出了吗？");
		System.out.printf("输出FunctionDemo identity的结果： %s \n", str2);
	}
	
	// 消费型函数式接口Consumer<T>  接受一个输入参数T并且无返回的操作
	public void ConsumerDemo() {
		Consumer<String> con1 = x -> System.out.println(x);
		con1.accept("输出Consumer1");
		Consumer<String> con2 = x -> System.out.println(x+" 第二个输出");
		
		con1.andThen(con2).accept("输出Consumer1");
	}

	// 断言型函数式接口Predicate<T>  接受一个输入参数，返回一个布尔值结果
	public void PredicateDemo() {
		Predicate<Integer> pre = x -> x>3;
		boolean b = pre.test(4);
		System.out.printf("输出PredicateDemo的结果1：%b \n", b);
		
		boolean b2 = pre.test(1);
		System.out.printf("输出PredicateDemo的结果2：%b \n", b2);
		
		Predicate<Integer> pre2 = x -> x<5;
		System.out.printf("输出PredicateDemo and的结果：%b \n", pre.and(pre2).test(6));
		System.out.printf("输出PredicateDemo or的结果：%b \n", pre.or(pre2).test(6));
		System.out.printf("输出PredicateDemo negate的结果：%b \n", pre.negate().test(4));
		System.out.printf("输出PredicateDemo isEqual的结果1：%b \n", Predicate.isEqual("4").test(4));
		System.out.printf("输出PredicateDemo isEqual的结果2：%b \n", Predicate.isEqual("4").test("4"));
		
	}
	
	// 供给型函数式接口Supplier<T> 无参数，返回一个结果。
	private void SupplierDemo() {
		String str = "This is a SupplierDemo"; 
		Supplier<Integer> x = ()-> str.length();
		Integer i = x.get();
		System.out.printf("输出SupplierDemo的结果%d\n", i);
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SomeInterface demo = new SomeInterface();
		demo.FunctionDemo();
		demo.ConsumerDemo();
		demo.PredicateDemo();
		demo.SupplierDemo();
	}
}
