package javastudy.stream;

import java.util.stream.IntStream;

public class StreamVsForLoop {
	private static final int MOD = 1000000;

	private static void streamFeature(int start, int end) {
		int ret = IntStream.range(start, end)
				.reduce(0, (acc, num) -> num + 1);
		System.out.println("stream ret = " + ret);
	}

	private static void forLoopFeature(int start, int end) {
		long ret = 0;
		for (int i = start; i < end; i++) {
			ret++;
		}
		System.out.println("for loop ret = " + ret);
	}

	private static String showTime(long start, long end) {
		return ((end - start) / MOD) + " ms";
	}

	public static void main(String[] args) {
		System.out.println("Stream api vs For loop");

		final int start = 0;
		final int end = 1000000000;

		long streamStartTime = System.nanoTime();
		streamFeature(start, end);
		long streamEndTime = System.nanoTime();

		long forLoopStartTime = System.nanoTime();
		forLoopFeature(start, end);
		long forLoopEndTime = System.nanoTime();

		System.out.println("stream api iterate time : " + showTime(streamStartTime, streamEndTime));
		System.out.println("for loop iterate time : " + showTime(forLoopStartTime, forLoopEndTime));
		/**
		 * Stream api vs For loop
		 * stream ret = 1000000000
		 * for loop ret = 1000000000
		 * stream api iterate time : 353 ms
		 * for loop iterate time : 27 ms
		 */
	}
}
