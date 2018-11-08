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
import java.util.Spliterator;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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

	public void useStream() {
		Stream<String> s = Arrays.asList("0", "1", "2", "3", "4", "0").stream();
		
		//distinct filter reduce
		Optional<String> result = s.distinct().filter(x -> x.compareTo("3") < 0).reduce((x, y) -> x + " " + y);
		System.out.println(result.get());
		
		String str = "Java 8 is a revolutionary release of the world’s #1 development platform. It includes a huge upgrade to the Java programming model and a coordinated evolution of the JVM, Java language, and libraries. Java 8 includes features for productivity, ease of use, improved polyglot programming, security and improved performance. Welcome to the latest iteration of the largest, open, standards-based, community-driven platform.";
		List<String> l= Arrays.stream(str.split("[.]")).flatMap(line -> Arrays.stream(line.split(" "))).map(x->{
			if(x.endsWith(".")||x.endsWith(",")){
				return x.substring(0, x.length()-1).trim();
			}
			else{
				return x.trim();
			}}).distinct().sorted().collect(Collectors.toList());
		l.forEach(x -> System.out.print(x+" "));
	}

	public static void main(String[] args) {
		Supplier<StreamDemo> demo = StreamDemo::new;
		demo.get().createStream();
		demo.get().useStream();
	}

}
