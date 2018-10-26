package org.learning.lambda;

//functional interface（函数式接口）: 仅制定了一个抽象方法的借口
@FunctionalInterface  //指明该接口类型声明是根据 Java 语言规范定义的函数式接口
interface FuncInterface {
	int method(); // 隐式的抽象方法，可以用abstract修饰但也可以不用
}

interface FuncInterfaceParam {
	int method(int v1, int v2, int v3); // 带多个参数的方法
}

// 带泛型的接口
interface FuncInterfaceTemp<T> {
	T method(T v1, T v2);
}

// 方法需要抛出异常的接口
interface FuncInterfaceException {
	int method(String v) throws InterruptedException; // 抛出InterruptedException异常
}

public class LambdaBasis {

	public static void main(String[] args) {

		FuncInterface fi; // 声明对函数式接口funcInterface的一个引用
		// lambda表达式不是独立执行的，而是构成一个函数式接口定义的抽象方法的实现，函数式接口定义了它的目标类型
		// 当目标类型类型上下文出现lambda表达式时，会自动创建实现了函数式接口的一个类的实例，函数式接口的声明的抽象方法的行为由lambda表达式定义。
		// 当通过目标调用该方法时，就会执行lambda表达式。因此，lambda表达式提供了一种将代码片段转换为对象的方法。
		fi = () -> 5; // 将一个lambda表达式赋值给该接口引用，注意：函数式接口定义的抽象方法的类型为int，这和lambda表达式的类型兼容
		System.out.println(fi.method()); // 输出5
		
		// 等价于上面的两行代码
		FuncInterface fi2 = new FuncInterface(){
			@Override
			public int method() {
				return 5;
			}
			
		};
		System.out.println(fi2.method()); // 输出5

		
		// funcInterfaceParam fip = (int value1, int value2, int value3) ->
		// (value1 + value2 + value3);
		// 跟上面注释的语句作用一样。lambda可以从上下文推断类型，所以可以不写类型
		FuncInterfaceParam fip = (value1, value2, value3) -> (value1 + value2 + value3);
		System.out.println(fip.method(1, 3, 5)); // 输出累加的结果9

		// 同一个接口，但是使用不同的lambda表达式
		fip = (value1, value2, value3) -> (value1 - value2 - value3);
		System.out.println(fip.method(1, 3, 5)); // 输出相减的结果-7
		fip = (value1, value2, value3) -> (value1 * value2 * value3);
		System.out.println(fip.method(1, 3, 5)); // 输出想乘的结果15

		// 块表达式
		fip = (value1, value2, value3) -> {
			if (value1 > value2) {
				if (value1 > value3) {
					return value1;
				} else {
					return value3;
				}
			} else {
				if (value2 > value3) {
					return value2;
				} else {
					return value3;
				}
			}
		};
		System.out.println(fip.method(1, 3, 5)); // 输出最大值的结果5

		// 泛型函数式接口
		FuncInterfaceTemp<Integer> fit = (Integer a, Integer b) -> (a > b) ? a : b;
		System.out.println(fit.method(1, 3)); // 输出最大值的结果3
		FuncInterfaceTemp<String> fit2 = (String a, String b) -> {
			if (a.length() > b.length())
				return a;
			else
				return b;
		};
		System.out.println(fit2.method("1", "12345")); // 输出长度最大的“12345”

		int outerVariable = 4;
		fip = (value1, value2, value3) -> {
			int sum = value1 + value2 + value3;
			sum += outerVariable; // 使用外层作用域的变量

			// outerVariable++; //会报错！不能修改外部变量。这里的outerVariable相当于final修饰
			return sum;
		};
		System.out.println(fip.method(1, 2, 3)); // 输出1+2+3+4的累加结果10

		FuncInterfaceException fie = (str) -> {
			Thread.sleep(10);
			return Integer.parseInt(str);
		};
		
		try {
			fie.method("1");
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			System.out.println("捕获lambda表达式产生的异常");
		}
		
		

	}

}
