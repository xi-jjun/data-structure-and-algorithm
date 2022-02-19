package algorithm.baekjoon.BruteForcing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 시간 복잡도 관해서 질문드리고 싶다.
 */
// baekJoon 2635 silver5 수 이어가기
public class S2635 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());

		int maxLen = -1;
		List<Integer> answer = new ArrayList<>();
		for (int k = N; k > 0; k--) {
			int thisMaxLen = 0;
			List<Integer> ai = new ArrayList<>();
			ai.add(N);
			ai.add(k);
			int a = N - k;
			int index = 0;
			while (a >= 0) {
				a = ai.get(index) - ai.get(index + 1);
				if (a < 0) {
					thisMaxLen = ai.size();
					break;
				}
				ai.add(a);
				index++;
			}

			if (thisMaxLen > maxLen) {
				maxLen = thisMaxLen;
				for (int i = 0; i < maxLen; i++) {
					answer.add(0);
				}
				Collections.copy(answer, ai);
			} else if (thisMaxLen < maxLen) {
				break;
			}
		}

		bw.write(maxLen + "\n");
		for (int i = 0; i < maxLen; i++) {
			bw.write(answer.get(i) + " ");
		}

		bw.flush();
		bw.close();
	}
}
