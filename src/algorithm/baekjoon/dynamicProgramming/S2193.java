package algorithm.baekjoon.dynamicProgramming;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// baekJoon 2193 silver3 - 이친수
public class S2193 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		long[] f = new long[91];
		f[1] = f[2] = 1;
		f[3] = 2;
		for (int i = 4; i < 91; i++) {
			f[i] = f[i - 1] + f[i - 2];
		}
		System.out.println(f[Integer.parseInt(br.readLine())]);
	}
}
