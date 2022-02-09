package algorithm.baekjoon.divide_and_Conquer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

// baekJoon 11444 Gold2 피보나치 수 6
public class G11444 {
	static Map<Long, Long> fib = new HashMap<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Long n = Long.parseLong(br.readLine());
		fib.put(0L, 0L);
		fib.put(1L, 1L);
		fib.put(2L, 1L);
		fib.put(3L, 2L);
		fib.put(4L, 3L);
		fib.put(5L, 5L);
		fib.put(6L, 8L);

		Long result = func(n);
		System.out.println(result % 1_000_000_007);
	}

	private static Long func(Long n) {
		if (n == 1L || n == 2L || n == 0L || fib.containsKey(n)) {
			return fib.get(n);
		}

		if (n % 2 == 0L) {
			Long temp1, temp2; // k, k-1
			if (!fib.containsKey(n / 2)) temp1 = fib.put(n / 2, func(n / 2));
			else temp1 = fib.get(n / 2);
			if (!fib.containsKey(n / 2 - 1)) temp2 = fib.put(n / 2 - 1, func(n / 2 - 1));
			else temp2 = fib.get(n / 2 - 1);

			Long temp = temp1 * (2 * temp2 + temp1) % 1_000_000_007;
			fib.put(n, temp);

			return temp;
		} else {
			Long temp1, temp2;
			if (!fib.containsKey((n + 1) / 2)) temp1 = fib.put((n + 1) / 2, func((n + 1) / 2));
			else temp1 = fib.get((n + 1) / 2);
			if (!fib.containsKey((n - 1) / 2)) temp2 = fib.put((n - 1) / 2, func((n - 1) / 2));
			else temp2 = fib.get((n - 1) / 2);

			Long temp = (temp1 * temp1 % 1_000_000_007 + temp2 * temp2 % 1_000_000_007) % 1_000_000_007;
			fib.put(n, temp);

			return temp;
		}
	}
}
