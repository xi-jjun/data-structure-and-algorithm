package BackTracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// baekJoon 15657 silver3 N과 M(8)
public class S15657 {
	static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	static List<Integer> a;
	static boolean[] visited;
	static int[] output;
	static int N, M;

	public static void main(String[] args) throws IOException {
		init();

		backTracking(0, 0);

		bw.flush();
		bw.close();
	}

	private static void backTracking(int depth, int at) throws IOException {
		if (depth == M) {
			printResults();
			return;
		}

		for (int i = at; i < N; i++) {
			output[depth] = a.get(i);
			backTracking(depth + 1, i);
		}
	}

	private static void printResults() throws IOException {
		for (int o : output) {
			bw.write(o + " ");
		}
		bw.write("\n");
	}

	private static void init() throws IOException {
		getNM();
		getElements();

		visited = new boolean[N];
		output = new int[M];
	}

	private static void getElements() throws IOException {
		String[] elements = br.readLine().split(" ");
		a = Arrays.stream(elements)
				.map(Integer::parseInt)
				.sorted()
				.collect(Collectors.toList());
	}

	private static void getNM() throws IOException {
		String[] nm = br.readLine().split(" ");
		N = Integer.parseInt(nm[0]);
		M = Integer.parseInt(nm[1]);
	}
}
