package org.learning.java8.defaultableInterface;

import java.util.function.Supplier;

interface InterfaceDemo {
	//默认方法的实现是非常高效的，
	//但是：在声明一个默认方法前，请仔细思考是不是真的有必要使用默认方法，因为默认方法会带给程序歧义，并且在复杂的继承体系中容易产生编译错误。
	default void defaultMethod() {
		System.out.println("This is a defaultMethod");
	}
	
	//java8中接口可以声明（并且可以提供实现）静态方法
	static void defaultStaticMethod() {
		System.out.println("This is a defaultMethod");
	}
	
	void abstractMethod(); //abstract方法
}

interface DefaulableFactory {
    // java8中接口可以声明（并且可以提供实现）静态方法
    static InterfaceDemo create( Supplier< InterfaceDemo > supplier ) {
        return supplier.get();
    }
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
		
		// Java 8 允许s通过::关键字获取方法或者构造函数的的引用。 构造函数的的引用语法：ClassName::new
		InterfaceDemo defaulable = DefaulableFactory.create( DefaultableInterface::new );
	    defaulable.defaultMethod();

	}

}
