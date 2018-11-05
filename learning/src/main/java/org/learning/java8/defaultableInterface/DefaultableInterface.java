package org.learning.java8.defaultableInterface;

interface InterfaceDemo {
	default void defaultMethod() {
		System.out.println("This is a defaultMethod");
	}
	void abstractMethod(); //abstract方法
}

public class DefaultableInterface implements InterfaceDemo {

//	 @Override
//	 public void defaultMethod(){ // 接口的默认方法可以不实现
//	 System.out.println("This is a overrideDefaultMethod");
//	 }
	
	@Override
	public void abstractMethod() { // 接口的抽象方法必须实现
		// TODO Auto-generated method stub
		System.out.println("This is a abstractMethod");
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DefaultableInterface di = new DefaultableInterface();
		di.defaultMethod(); //注释后，输出的是默认方法的输出；若去掉注释，则输出Override的方法的输出，覆盖了默认方法
		di.abstractMethod();

	}

}
