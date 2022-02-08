package divide_and_Conquer;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// baekJoon 1629번 silver1 곱셈
// https://st-lab.tistory.com/237 참고.
public class S1629 {
	private static long A, B, C;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] inputs = br.readLine().split(" ");
		A = Long.parseLong(inputs[0]);
		B = Long.parseLong(inputs[1]);
		C = Long.parseLong(inputs[2]);

		Long answer = divideConquer(A, B);

		bw.write(answer + "");

		bw.flush();
		bw.close();
	}

	private static Long divideConquer(long base, long exp) {
		if (exp == 1) {
			return base % C;
		}

		long temp = divideConquer(base, exp / 2);

		if (exp % 2 == 1) {
			return ((temp * temp % C) * base) % C;
		}

		return temp * temp % C;
	}
}
