package algorithm.baekjoon.BFS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.Queue;

// baekJoon 7562 silver2 나이트의 이동
public class S7562 {
	static final int[] dx = {-2, -2, -1, 1, 2, 2, 1, -1};
	static final int[] dy = {-1, 1, 2, 2, 1, -1, -2, -2};

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int T = Integer.parseInt(br.readLine());
		for (int i = 0; i < T; i++) {
			int l = Integer.parseInt(br.readLine());

			String[] coords = br.readLine().split(" ");
			int currX = Integer.parseInt(coords[0]);
			int currY = Integer.parseInt(coords[1]);

			coords = br.readLine().split(" ");
			int answerX = Integer.parseInt(coords[0]);
			int answerY = Integer.parseInt(coords[1]);

			if (isAnswer(currX, currY, answerX, answerY)) {
				bw.write("0\n");
				continue;
			}

			int answer = bfs(currX, currY, answerX, answerY, l);
			bw.write(answer + "\n");

		}

		bw.flush();
		bw.close();
	}

	private static int bfs(int startX, int startY, int destX, int destY, int size) {
		boolean[][] isVisited = new boolean[size][size];
		Queue<Point> q = new ArrayDeque<>();
		q.offer(new Point(startX, startY, 0));
		isVisited[startX][startY] = true;

		while (!q.isEmpty()) {
			Point currPoint = q.poll();
			int currX = currPoint.x;
			int currY = currPoint.y;
			int currTimes = currPoint.times;

			for (int i = 0; i < 8; i++) {
				int nextX = currX + dx[i];
				int nextY = currY + dy[i];

				if (inRange(nextX, nextY, size) && !isVisited[nextX][nextY]) {
					if (isAnswer(nextX, nextY, destX, destY)) {
						return currTimes + 1;
					}

					isVisited[nextX][nextY] = true;
					q.offer(new Point(nextX, nextY, currTimes + 1));
				}
			}
		}

		return -1;
	}

	private static boolean isAnswer(int x, int y, int answerX, int answerY) {
		return x == answerX && y == answerY;
	}

	static boolean inRange(int x, int y, int size) {
		return !(x < 0 || x >= size || y < 0 || y >= size);
	}

	static class Point {
		int x, y;
		int times;

		public Point(int x, int y, int times) {
			this.x = x;
			this.y = y;
			this.times = times;
		}
	}
}
