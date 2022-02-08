package tree;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// baekJoon 11725 silver2 트리의 부모 찾기
// https://jaimemin.tistory.com/585 참고...
public class S11725 {
	static int[] parentKeys;
	static boolean[] isShowedUp;
	static List<List<Integer>> pairs = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		isShowedUp = new boolean[N + 1];
		parentKeys = new int[N + 1];

		for (int i = 0; i <= N; i++) {
			pairs.add(new ArrayList<>());
		}

		for (int i = 1; i < N; i++) {
			StringTokenizer st = new StringTokenizer(br.readLine());
			int key1 = Integer.parseInt(st.nextToken());
			int key2 = Integer.parseInt(st.nextToken());
			pairs.get(key1).add(key2);
			pairs.get(key2).add(key1);
		}

		findParents(1);

		for (int i = 2; i <= N; i++) {
			bw.write(parentKeys[i] + "\n");
		}

		bw.flush();
		bw.close();
	}

	private static void findParents(int parentKey) {
		isShowedUp[parentKey] = true; // check visiting

		for (int i = 0; i < pairs.get(parentKey).size(); i++) {
			int childKey = pairs.get(parentKey).get(i);
			if (!isShowedUp[childKey]) {
				parentKeys[childKey] = parentKey;
				findParents(childKey);
			}
		}
	}
}
