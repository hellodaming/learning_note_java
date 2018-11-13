package org.learning.java8.newLibrary;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Random;
import java.util.Set;
import java.util.Spliterator;
import java.util.Stack;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

//Java 8 中的 Stream 是对集合（Collection）对象功能的增强，
//它专注于对集合对象进行各种非常便利、高效的聚合操作（aggregate operation），或者大批量数据操作 (bulk data operation)。
//Stream API 借助于同样新出现的 Lambda 表达式，极大的提高编程效率和程序可读性。
//同时它提供串行和并行两种模式进行汇聚操作，并发模式能够充分利用多核处理器的优势，使用 fork/join 并行方式来拆分任务和加速处理过程。


//流的操作类型: 1) Intermediate 惰性化的（lazy） 2) Terminal  3) short-circuiting
//short-circuiting: 对于一个 intermediate 操作，如果它接受的是一个无限大（infinite/unbounded）的 Stream，但返回一个有限的新 Stream。
//或者对于一个 terminal 操作，如果它接受的是一个无限大的 Stream，但能在有限的时间计算出结果。

//Intermediate操作: map (mapToInt, flatMap 等)、 filter、 distinct、 sorted、 peek、 limit、 skip、 parallel、 sequential、 unordered
//Terminal操作： forEach、 forEachOrdered、 toArray、 reduce、 collect、 min、 max、 count、 anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 iterator
//Short-circuiting操作: anyMatch、 allMatch、 noneMatch、 findFirst、 findAny、 limit

//参考https://www.ibm.com/developerworks/cn/java/j-lo-java8streamapi/index.html
//参考https://colobu.com/2016/03/02/Java-Stream/#%E4%BB%8B%E7%BB%8D
public class StreamDemo {

	public void createStream() {

		// 通过列表集合创建
		List<String> l = Arrays.asList("0", "1", "2", "3", "4", "0");
		Stream<String> s = l.stream(); // 利用列表集合的stream()创建
		Stream<String> s2 = l.parallelStream(); // 利用列表集合的parallelStream()创建

		// 通过列表静态方法创建
		Stream<String> s3 = Arrays.stream(new String[] { "0", "1", "2", "3", "4", "0" }); // 利用Arrays.stream(T[]
																							// array)方法创建

		// 利用Stream的静态方法创建
		Stream<String> s4 = Stream.of("0", "1", "2", "3", "4", "0");
		Stream<Integer> s5 = Stream.iterate(0, n -> (n + 1));
		Stream<Integer> s6 = Stream.generate(() -> 5);
		IntStream s7 = IntStream.range(1, 100);

		// BufferedReader.lines()从文件中获得行的流。
		Function<Reader, BufferedReader> fun = BufferedReader::new;
		try {
			Stream<String> s8 = fun.apply(new FileReader("/home/will/nohup.out")).lines();
			// s8.forEach(x->System.out.println(x));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 随机数流Random.ints()
		Supplier<Random> random = Random::new;
		IntStream s9 = random.get().ints();

		// Files类的操作路径的方法，如list、find、walk等。
		try (Stream<Path> entries = Files.walk(Paths.get("/home/will/Git"), 9)) {
			entries.onClose(() -> System.out.println("Done!")).forEach(p -> {
				System.out.println(p.toFile().getAbsolutePath());
			});
		} catch (IOException e) {
			e.printStackTrace();
		}

		// 使用StreamSupport，它提供了将Spliterator转换成流的方法
		Spliterator<String> myParallelSpliterator = l.spliterator();
		Stream<String> s10 = StreamSupport.stream(myParallelSpliterator.trySplit(), true);

		// 还有其他很多方法……
	}

	public void streamTransform(){
		Stream<String> s =  Arrays.asList("0", "1", "2", "3", "4", "5").stream();
		
		// 一个流只能用一次
		//String[] a =  s.toArray(String[]::new);
		//List<String> l =  s.collect(Collectors.toList());
		//Set set = s.collect(Collectors.toSet()); 
		//Stack<String> stack = s.collect(Collectors.toCollection(Stack::new));
		
		String string = s.collect(Collectors.joining());
		System.out.println(string);
		

	}
	
	public void useStream() {

		Stream<String> s = Arrays.asList("0", "1", "2", "3", "4", "0").stream();

		// distinct filter reduce
		Optional<String> result = s.distinct().filter(x -> x.compareTo("3") < 0).reduce((x, y) -> x + " " + y);
		System.out.println(result.get());

		String str = "Java 8 is a revolutionary release of the world’s #1 development platform. It includes a huge upgrade to the Java programming model and a coordinated evolution of the JVM, Java language, and libraries. Java 8 includes features for productivity, ease of use, improved polyglot programming, security and improved performance. Welcome to the latest iteration of the largest, open, standards-based, community-driven platform.";
		List<String> l = Arrays.stream(str.split("[.]")).flatMap(line -> Arrays.stream(line.split(" ")))
				.map(x -> {
					if (x.endsWith(".") || x.endsWith(",")) {
						return x.substring(0, x.length() - 1).trim();
					} else {
						return x.trim();
					}
				})
				.distinct()  // 去重
				.sorted() // 排序
				.peek(System.out::print) // peek方法方法会使用一个Consumer消费流中的元素，但是返回的流还是包含原来的流中的元素
				.limit(15) // 支取前面n个
				.collect(Collectors.toList()); // 
		System.out.println("");
		l.forEach(x -> System.out.print(x + " "));

	}

	public static void main(String[] args) {
		Supplier<StreamDemo> demo = StreamDemo::new;
		demo.get().createStream();
		demo.get().streamTransform();
		demo.get().useStream();
	}

}
