package algorithm.baekjoon.BruteForcing;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// baekJoon 14502 Gold5 - 연구소
public class G14502 {
	static final int BLANK = 0;
	static final int WALL = 1;
	static final int VIRUS = 2;
	static final int SPREAD_VIRUS = 3;
	static final int[] dx = {-1, 0, 1, 0};
	static final int[] dy = {0, 1, 0, -1};
	static int N, M, answer;
	static int[][] map;
	static boolean[][] visited;
	static boolean[][] virusVisited;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		M = Integer.parseInt(st.nextToken());
		map = new int[N][M];
		visited = new boolean[N][M];
		for (int i = 0; i < N; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; st.hasMoreTokens(); j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}

		backTracking(0);
		System.out.println(answer);
	}

	private static void dfs(int x, int y) {
		for (int i = 0; i < 4; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];

			if (inRange(nextX, nextY) && map[nextX][nextY] == BLANK && !virusVisited[nextX][nextY]) {
				virusVisited[x][y] = true;
				map[nextX][nextY] = SPREAD_VIRUS;
				dfs(nextX, nextY);
			}
		}
	}

	private static boolean inRange(int x, int y) {
		return !(x < 0 || x >= N || y < 0 || y >= M);
	}

	private static void backTracking(int depth) {
		if (depth == 3) {
			virusVisited = new boolean[N][M];
			spreadingVirus();
			// count and reset map
			int count = countSafeArea();
			answer = Math.max(answer, count);
			return;
		}

		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (!visited[i][j] && map[i][j] == BLANK) {
					visited[i][j] = true;
					map[i][j] = WALL;
					backTracking(depth + 1);
					map[i][j] = BLANK;
					visited[i][j] = false;
				}
			}
		}
	}

	private static int countSafeArea() {
		int ret = 0;
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == BLANK) ret++;
				if (map[i][j] == SPREAD_VIRUS) map[i][j] = BLANK; // reset
			}
		}
		return ret;
	}

	private static void spreadingVirus() {
		for (int i = 0; i < N; i++) {
			for (int j = 0; j < M; j++) {
				if (map[i][j] == VIRUS) {
					dfs(i, j);
				}
			}
		}
	}
}
