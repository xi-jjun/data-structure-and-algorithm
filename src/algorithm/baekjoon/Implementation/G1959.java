package algorithm.baekjoon.Implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// baekJoon 1959 Gold3 달팽이3
public class G1959 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] number = br.readLine().split(" ");
		int M = Integer.parseInt(number[0]);
		int N = Integer.parseInt(number[1]);

		long diagonal = 0;
		int x = 0;
		int y = 0;
		if (M == N) {
			diagonal = 2L * (N - 1);
			if (N % 2 == 1) {
				x = (N - 1) / 2;
				y = (N - 1) / 2;
			} else {
				x = N / 2;
				y = (N / 2) - 1;
			}
		} else if (M > N) {
			diagonal = 2L * (N - 1) + 1;
			if (N % 2 == 1) {
				x = (N - 1) / 2 + (M - N);
				y = (N - 1) / 2;
			} else {
				x = N / 2;
				y = (N / 2) - 1;
			}
		} else {
			diagonal = 2L * (M - 1);
			if (M % 2 == 1) {
				x = (M - 1) / 2;
				y = (M - 1) / 2 + (N - M);
			} else {
				x = M / 2;
				y = (M / 2) - 1;
			}
		}
		x++; y++;
		bw.write(diagonal + "\n" + x + " " + y);
		bw.flush();
		bw.close();
	}
}
