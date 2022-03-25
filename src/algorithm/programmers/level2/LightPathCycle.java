package algorithm.programmers.level2;

import java.util.HashSet;
import java.util.Set;

public class LightPathCycle {
	static int[] dx = {-1, 0, 1, 0}; // U R D L
	static int[] dy = {0, 1, 0, -1};

	public static void main(String[] args) {
		String[] grid = {"SL", "LR"};
		int[] answer = solution(grid);
		for (int ans : answer) {
			System.out.println("ans = " + ans);
		}
	}

	public static int[] solution(String[] grid) {
		int[] answer = {};
		int size = Math.max(grid.length, grid[0].length());
		boolean[][][][] isVisited = new boolean[size][size][size][size];
		char[][] map = createGridMap(grid);

		for (int i = 0; i < 4; i++) { // Up, Right, Down, Left 4개의 방향에 대해서 각각 실행시켜주면 됨
			int count = 0;
			isVisited = new boolean[size][size][size][size];

			int startX = 0;
			int startY = 0;
			int nextX = dx[i];
			int nextY = dy[i];
			int exDirection = i;
			isVisited[startX][startY][nextX][nextY] = true;

			while (true) {
				char nowNode = map[nextX][nextY];
				int nextDirection = direction(exDirection, nowNode);

				startX = nextX;
				startY = nextY;
				nextX += dx[nextDirection];
				nextY += dy[nextDirection];

				if (!isVisited[startX][startY][nextX][nextY]) {
					isVisited[startX][startY][nextX][nextY] = true;
					count++;
				} else {

				}
			}
		}


		return answer;
	}

	private static int direction(int exDirection, char node) {
		if (node == 'L') return (exDirection + 3) % 4;
		if (node == 'R') return (exDirection + 1) % 4;
		else return exDirection;
	}


	private static char[][] createGridMap(String[] grid) {
		char[][] map = new char[grid.length][grid[0].length()];
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[0].length(); j++) {
				map[i][j] = grid[i].charAt(j);
			}
		}
		return map;
	}

	static class Path {
		int startX;
		int startY;
		int endX;
		int endY;

		public Path(int startX, int startY, int endX, int endY) {
			this.startX = startX;
			this.startY = startY;
			this.endX = endX;
			this.endY = endY;
		}

		@Override
		public boolean equals(Object obj) {
			return (obj instanceof Path) &&
					((Path) obj).startX == this.startX &&
					((Path) obj).startY == this.startY &&
					((Path) obj).endX == this.endX &&
					((Path) obj).endY == this.endY;
		}
	}
}
