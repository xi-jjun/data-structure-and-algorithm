package algorithm.baekjoon.dynamicProgramming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// baekJoon 9251 Gold5 LCS
// https://www.programiz.com/dsa/longest-common-subsequence LCS 참고
public class G9251 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String str1 = br.readLine();
		String str2 = br.readLine();
		int[][] lcs = new int[str1.length() + 1][str2.length() + 1];
		for (int i = 1; i < str1.length() + 1; i++) {
			for (int j = 1; j < str2.length() + 1; j++) {
				if (str1.charAt(i - 1) == str2.charAt(j - 1)) lcs[i][j] = lcs[i - 1][j - 1] + 1;
				else lcs[i][j] = Math.max(lcs[i - 1][j], lcs[i][j - 1]);
			}
		}

		bw.write(lcs[str1.length()][str2.length()] + "");
		bw.flush();
		bw.close();
	}
}
