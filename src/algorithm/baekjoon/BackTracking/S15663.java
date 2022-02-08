package BackTracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

// baekJoon 15663 silver2 N과 M (9)
public class S15663 {
	static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	static List<Integer> numbers = new ArrayList<>();
	static boolean[] visited;
	static List<Integer> output = new ArrayList<>();
	static int N, M;

	public static void main(String[] args) throws IOException {
		getNM();
		getNumbers();

		backTracking(0);

		bw.flush();
		bw.close();
	}

	private static void backTracking(int depth) throws IOException {
		if (depth == M) {
			printResult();
			return;
		}

		int dup = -1;
		for (int i = 0; i < N; i++) {
			if (!visited[i] && dup != numbers.get(i)) {
				output.set(depth, numbers.get(i));
				dup = output.get(depth);
				visited[i] = true;
				backTracking(depth + 1);
				visited[i] = false;
			}
		}
	}

	private static void printResult() throws IOException {
		for (Integer out : output) {
			bw.write(out + " ");
		}
		bw.write("\n");
	}

	private static void getNumbers() throws IOException {
		String[] line = br.readLine().split(" ");
		numbers = Arrays.stream(line)
				.map(Integer::parseInt)
				.sorted()
				.collect(Collectors.toList());
	}

	private static void getNM() throws IOException {
		String[] line = br.readLine().split(" ");
		N = line[0].charAt(0) - '0';
		M = line[1].charAt(0) - '0';

		for (int i = 0; i < M; i++) {
			output.add(i);
		}
		visited = new boolean[N];
	}
}
