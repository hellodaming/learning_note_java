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
import java.util.Random;
import java.util.function.Function;
import java.util.function.Supplier;
import java.util.stream.IntStream;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Supplier<StreamDemo> demo = StreamDemo::new;
		demo.get().createStream();
	}

}
