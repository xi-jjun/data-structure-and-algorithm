package algorithm.baekjoon.Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// baekJoon 2477 silver4 참외밭
public class S2477 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int maxCross = -1;
		int maxAcross = -1;
		int K = Integer.parseInt(br.readLine());
		int[] length = new int[6];
		for (int i = 0; i < 6; i++) {
			String[] coords = br.readLine().split(" ");
			int d = Integer.parseInt(coords[0]);
			length[i] = Integer.parseInt(coords[1]);

			if (d == 1 || d == 2) {
				maxCross = Math.max(maxCross, length[i]);
			} else {
				maxAcross = Math.max(maxAcross, length[i]);
			}
		}

		int minSize= 0;
		for (int i = 0; i < 6; i++) {
			if ((length[i] == maxAcross && length[(i + 1) % 6] == maxCross)
					|| (length[i] == maxCross && length[(i + 1) % 6] == maxAcross)) {
				minSize = length[(i + 3) % 6] * length[(i + 4) % 6];
			}
		}
		int answer = (maxAcross * maxCross - minSize) * K;
		System.out.println(answer);
	}
}
