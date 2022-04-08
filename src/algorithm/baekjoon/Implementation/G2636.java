package algorithm.baekjoon.Implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class G2636 {
	static final int NONE = 0;
	static final int CHEESE = 1;
	static final int TO_BE_ERASED = 2;
	static final int[] dx = {-1, 0, 1, 0};
	static final int[] dy = {0, 1, 0, -1};
	static int N, M;
	static int[][] map;
	static boolean[][] visited;
	static int tempCount;
	static List<Integer> count = new ArrayList<>();

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		map = new int[N][M];

		makeMap(br);

		int time = 0;
		while (hasMoreCheese()) {
			++time;
			doProcess();
		}

		bw.write(time + "\n" + count.get(count.size() - 1) + "");

		bw.flush();
		bw.close();
	}

	private static void doProcess() {
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if ((i == 0 || j == 0 || i == N - 1 || j == M - 1) && !visited[i][j]) {
					dfs(i, j);
					eraseAiredCheese();
					count.add(tempCount);
				}
			}
		}
	}

	private static void eraseAiredCheese() {
		tempCount = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == TO_BE_ERASED) {
					++tempCount;
					map[i][j] = NONE;
				}
			}
		}
	}

	private static void dfs(int x, int y) {
		visited[x][y] = true;

		if (map[x][y] == CHEESE) {
			map[x][y] = TO_BE_ERASED;
			return;
		}

		for (int i = 0; i < 4; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];
			if (inRange(nextX, nextY) && !visited[nextX][nextY]) {
				dfs(nextX, nextY);
			}
		}
	}

	private static boolean inRange(int x, int y) {
		return !(x < 0 || x >= N || y < 0 || y >= M);
	}

	private static boolean hasMoreCheese() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == CHEESE) return true;
			}
		}
		return false;
	}

	private static void makeMap(BufferedReader br) throws IOException {
		for (int i = 0; i < N; i++) {
			int index = 0;
			StringTokenizer st = new StringTokenizer(br.readLine());
			while (st.hasMoreTokens()) {
				map[i][index++] = Integer.parseInt(st.nextToken());
			}
		}
	}
}
