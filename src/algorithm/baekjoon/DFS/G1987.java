package algorithm.baekjoon.DFS;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class G1987 {
	static int R, C, answer;
	static char[][] map;
	static boolean[][] visited;
	static boolean[] alphabet;
	static final int[] dx = {-1, 0, 1, 0};
	static final int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		R = Integer.parseInt(st.nextToken());
		C = Integer.parseInt(st.nextToken());
		map = new char[R][C];
		visited = new boolean[R][C];
		alphabet = new boolean[26];
		for (int i = 0; i < R; i++) {
			map[i] = br.readLine().toCharArray();
		}

		answer = 0;
		alphabet[map[0][0] - 'A'] = true;
		dfs(0, 0, 1);

		System.out.println(answer);
	}

	private static void dfs(int x, int y, int depth) {
		answer = Math.max(answer, depth);

		for (int i = 0; i < 4; i++) {
			int nextX = x + dx[i];
			int nextY = y + dy[i];

			if (valid(nextX, nextY)) {
				visited[nextX][nextY] = true;
				alphabet[map[nextX][nextY] - 'A'] = true;
				dfs(nextX, nextY, depth + 1);
				alphabet[map[nextX][nextY] - 'A'] = false;
				visited[nextX][nextY] = false;
			}
		}
	}

	private static boolean valid(int x, int y) {
		return inRange(x, y) && !visited[x][y] && !alphabet[map[x][y] - 'A'];
	}

	private static boolean inRange(int x, int y) {
		return !(x < 0 || x >= R || y < 0 || y >= C);
	}
}
