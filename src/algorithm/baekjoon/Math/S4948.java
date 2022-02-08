package Math;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// baekJoon 4948 silver2 베르트랑 공준
public class S4948 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		int N = -1;

		while (true) {
			N = Integer.parseInt(br.readLine());
			if (N == 0) {
				break;
			}
			int count = 0;

			for (int i = N + 1; i <= 2 * N; i++) {
				if (isPrime(i)) {
					count++;
				}
			}
			bw.write(count + "\n");
		}

		bw.flush();
		bw.close();
	}

	private static boolean isPrime(int number) {
		for (int i = 2; i * i <= number; i++) {
			if (number % i == 0) {
				return false;
			}
		}
		return true;
	}
}
