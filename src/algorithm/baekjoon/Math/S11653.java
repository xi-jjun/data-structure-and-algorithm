package Math;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// baekJoon 11653 silver5 소인수분해
public class S11653 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		if (N == 1) {
			return;
		}

		int divider = 2;

		while (N != 1) {
			while (N % divider == 0) {
				N /= divider;
				bw.write(divider + "\n");
			}
			divider++;
		}

		bw.flush();
		bw.close();
	}
}
