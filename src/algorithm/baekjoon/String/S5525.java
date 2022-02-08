package String;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// baekJoon silver2 IOIOI
public class S5525 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(br.readLine());
		int M = Integer.parseInt(br.readLine());
		String[] S = br.readLine().split("");
		int answer = 0;

		for (int i = 0; i < M; i++) {
			if (S[i].equals("I")) {
				int count = 0;
				int next = 0;

				for (int j = i + 1; j < S.length; j += 2) {
					if (j + 1 == S.length) break;
					if (S[j].equals("O") && S[j + 1].equals("I")) {
						count++;
					} else {
						i = j - 1;
						break;
					}

					if (count == N) {
						answer++;
						next = j + 2;
						break;
					}
				}

				if (next != 0) {
					boolean ok = true;
					
					for (int j = next; j < S.length; j += 2) {
						if (j + 1 == S.length) break;
						if (S[j].equals("O") && S[j + 1].equals("I")) {
							answer++;
						} else {
							i = j - 1;
							ok = false;
							break;
						}
					}

					if (ok) {
						i = S.length - 1;
					}
				}
			}
		}

		System.out.println(answer);
	}
}
