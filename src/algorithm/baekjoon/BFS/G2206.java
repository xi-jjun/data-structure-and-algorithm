package BFS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;

// baekJoon 2206 Gold4 벽 부수고 이동하기
public class G2206 {
	static final int WALL = 1;
	static int N, M;
	static int minDistance = Integer.MAX_VALUE;
	static int[][] map;
	static boolean[][][] isVisited;
	static int dx[] = {0, 1, 0, -1};
	static int dy[] = {1, 0, -1, 0};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		M = Integer.parseInt(line[1]);
		map = new int[N][M];
		isVisited = new boolean[2][N][M];

		for (int i = 0; i < N; i++) {
			int idy = 0;
			line = br.readLine().split("");
			for (String l : line) {
				map[i][idy++] = Integer.parseInt(l);
			}
		}

		bfs();

		if (N == 1 && M == 1) {
			bw.write("1");
		} else if (minDistance != Integer.MAX_VALUE) {
			bw.write(minDistance + "");
		} else {
			bw.write("-1");
		}

		bw.flush();
		bw.close();
	}

	private static void bfs() {
		Queue<Point> q = new ArrayDeque<>();
		Point startPoint = new Point(0, 0, 1, false);
		q.offer(startPoint);
		int floor;

		while (!q.isEmpty()) {
			Point currentPoint = q.poll();
			int currentX = currentPoint.x;
			int currentY = currentPoint.y;
			int currentDistance = currentPoint.distance;
			boolean isDestroyed = currentPoint.destroy;
			floor = isDestroyed ? 1 : 0;
			isVisited[floor][currentX][currentY] = true; // visit check

			for (int i = 0; i < 4; i++) {
				int nextX = currentX + dx[i];
				int nextY = currentY + dy[i];
				Point nextPoint = new Point(nextX, nextY, currentDistance + 1, isDestroyed);

				if (nextX == N - 1 && nextY == M - 1) {
					minDistance = Math.min(minDistance, currentDistance + 1);
					continue;
				}

				if (inRange(nextX, nextY) && !isVisited[floor][nextX][nextY]) {
					isVisited[floor][nextX][nextY] = true;

					if (isWall(nextX, nextY) && !isDestroyed) { // Wall, not destroy yet
						if (isMovable(nextX + dx[i], nextY + dy[i])) { // Wall 맞은편 확인 ( map 범위 안인지 + 맞은편이 0인ㄴ지)
							nextPoint.destroy = true;
							q.offer(nextPoint);
						}
					} else if (!isWall(nextX, nextY)) { // can go
						q.offer(nextPoint);
					}
				}
			}
		}
	}

	private static boolean isMovable(int x, int y) {
		return inRange(x, y) && !isWall(x, y);
	}

	private static boolean isWall(int x, int y) {
		return map[x][y] == WALL;
	}

	private static boolean inRange(int x, int y) {
		return !(x < 0 || x >= N || y < 0 || y >= M);
	}

	static class Point {
		int x, y;
		int distance;
		boolean destroy;

		public Point(int x, int y, int distance, boolean destroy) {
			this.x = x;
			this.y = y;
			this.destroy = destroy;
			this.distance = distance;
		}
	}
}
/**
 * 9 9
 * 000000000
 * 111111101
 * 101011000
 * 000010100
 * 011101011
 * 000001011
 * 101101000
 * 101100101
 * 101110000
 *
 * 10 10
 * 0111011011
 * 1010011011
 * 1000100011
 * 1000101100
 * 0011010000
 * 1101110101
 * 0110111101
 * 1010100100
 * 1111001011
 * 0001010100
 *
 * 6 4
 * 0000
 * 1110
 * 1000
 * 0000
 * 0111
 * 0100
 * ans : 9
 *
 * 5 10
 * 0000011000
 * 1101011010
 * 0000000010
 * 1111111110
 * 1111000000
 * ans : 14
 */
