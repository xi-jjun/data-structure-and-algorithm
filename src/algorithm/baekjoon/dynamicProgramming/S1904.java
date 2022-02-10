package algorithm.baekjoon.dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

// baekJoon 1904 silver3 01타일
public class S1904 {
	static final int MOD = 15746;
	static Map<Long, Long> dp = new HashMap<>(); // index, value

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		Long N = Long.parseLong(br.readLine());
		dp.put(0L, 0L);
		dp.put(1L, 1L);
		dp.put(2L, 1L);
		dp.put(3L, 2L);
		dp.put(4L, 3L);
		dp.put(5L, 5L);
		dp.put(6L, 8L);

		Long result = func(N+1);
		System.out.println((result % MOD));
	}

	private static Long func(Long n) {
		if (n <= 6 || dp.containsKey(n)) {
			return dp.get(n);
		}

		Long dpn, t1, t2;

		if (n % 2 == 0L) {
			if (!dp.containsKey(n / 2)) t1 = dp.put(n / 2, func(n / 2));
			else t1 = dp.get(n / 2);
			if (!dp.containsKey(n / 2 - 1)) t2 = dp.put(n / 2 - 1, func(n / 2 - 1));
			else t2 = dp.get(n / 2 - 1);
			dpn = t1 * (t1 + 2 * t2) % MOD;
		} else {
			if (!dp.containsKey((n + 1) / 2)) t1 = dp.put((n + 1) / 2, func((n + 1) / 2));
			else t1 = dp.get((n + 1) / 2);
			if (!dp.containsKey((n - 1) / 2)) t2 = dp.put((n - 1) / 2, func((n - 1) / 2));
			else t2 = dp.get((n - 1) / 2);
			dpn = (t1 * t1 % MOD + t2 * t2 % MOD) % MOD;
		}

		dp.put(n, dpn);
		return dpn;
	}
}
