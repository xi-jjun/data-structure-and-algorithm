package algorithm.programmers.devMatching1;

import java.util.ArrayList;
import java.util.List;

/**
 * test case 통과
 */
public class Solution2 {
	static final char TBD_CHAR = 'x';
	static final char[] alphabet = {'a', 'b', 'c'};
	static final int[] dx = {-1, 0, 1, 0};
	static final int[] dy = {0, 1, 0, -1};

	static int answer;
	static char[][] map;
	static boolean[][][] visited;
	static boolean[][] dfsVisited;
	static List<Character> visitedTarget = new ArrayList<>();
	static List<Loc> toBeAddedLocation = new ArrayList<>();

	public static void main(String[] args) {
		String[] grid = {"aa?"};
//		String[] grid = {"??b", "abc", "cc?"};
		int answer = solution(grid);
		System.out.println("answer = " + answer);
	}

	public static boolean dfs(int startX, int startY, char target) {
		if (startX < 0 || startX >= map.length || startY < 0 || startY >= map[0].length) {
			return false;
		}

		if (visitedTarget.contains(map[startX][startY])) {
			return false;
		}

		if (map[startX][startY] == target && !dfsVisited[startX][startY]) {
			dfsVisited[startX][startY] = true;

			dfs(startX + dx[0], startY + dy[0], target);
			dfs(startX + dx[1], startY + dy[1], target);
			dfs(startX + dx[2], startY + dy[2], target);
			dfs(startX + dx[3], startY + dy[3], target);

			return true;
		}

		return false;
	}

	public static void backTracking(int depth, int at) {
		if (depth == toBeAddedLocation.size()) {
			dfsVisited = new boolean[map.length][map[0].length];
			visitedTarget = new ArrayList<>();
			int count = 0;
			for (int i = 0; i < map.length; i++) {
				for (int j = 0; j < map[0].length; j++) {
					if (dfs(i, j, map[i][j])) {
						visitedTarget.add(map[i][j]); // 얘가 중요 포인트였음
						count++;
					}
				}
			}

			if (count <= 3 && isTrue()) answer++;
			return;
		}


		for (int next = at; next < toBeAddedLocation.size(); next++) {
			int x = toBeAddedLocation.get(next).x;
			int y = toBeAddedLocation.get(next).y;

			for (int i = 0; i < 3; i++) {
				int nextAlphabet = alphabet[i];

				if (!visited[x][y][nextAlphabet]) {
					visited[x][y][nextAlphabet] = true;
					map[x][y] = (char) nextAlphabet;

					backTracking(depth + 1, next + 1);

					visited[x][y][nextAlphabet] = false;
					map[x][y] = 'x';
				}
			}
		}
	}

	public static boolean isTrue() {
		for (boolean[] booleans : dfsVisited) {
			for (boolean aBoolean : booleans) {
				if (!aBoolean) return false;
			}
		}
		return true;
	}

	public static int solution(String[] grid) {
		answer = 0;
		map = new char[grid.length][grid[0].length()];
		visited = new boolean[grid.length][grid[0].length()][101];

		for (int i = 0; i < grid.length; i++) {
			char[] arr = grid[i].toCharArray();
			for (int j = 0; j < grid[0].length(); j++) {
				if (arr[j] == '?') {
					map[i][j] = TBD_CHAR;
					toBeAddedLocation.add(new Loc(i, j));
				} else {
					map[i][j] = arr[j];
				}
			}
		}

		backTracking(0, 0);

		return answer;
	}

	static class Loc {
		int x;
		int y;

		public Loc(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
