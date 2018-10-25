package org.learning.lambda;

//functional interface（函数式接口）: 仅制定了一个抽象方法的借口
interface funcInterface{
	int method();
}

public class LambdaBasis {

	public static void main(String[] args) {
		
		funcInterface fi; //声明对函数式接口funcInterface的一个引用
		fi = ()->5; //将一个lambda表达式赋值给该借口引用，注意：函数式接口定义的抽象方法的类型为int，这和lambda表达式的类型兼容
		System.out.println(fi.method()); //输出5
	}

}
