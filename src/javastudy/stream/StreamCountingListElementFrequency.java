package javastudy.stream;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamCountingListElementFrequency {
	public static void main(String[] args) {
		List<Integer> list = List.of(1, 1, 2, 3, 4, 5, 5, 5, 5);

		Map<Integer, Long> collect = list.stream()
				.collect(Collectors.groupingBy(Function.identity(), Collectors.counting()));
		for (Map.Entry<Integer, Long> entry : collect.entrySet()) {
			System.out.println("key, value = " + entry.getKey() + ", " + entry.getValue());
		}

		Stream<Integer> stream = Stream.of(1, 1, 2, 3, 4, 5, 5, 5, 5);
		printCollectorsCounting(stream);
	}

	private static void printCollectorsCounting(Stream<Integer> stream) {
		Long collect = stream.collect(Collectors.counting());
		System.out.println("collect = " + collect);
	}
}
