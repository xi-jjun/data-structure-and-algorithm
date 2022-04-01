package algorithm.baekjoon.BackTracking;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.List;

public class G3967 {
	static final int EMPTY = 0;
	static boolean flag = false;
	static int[][] star;
	static char[][] answer;
	static boolean[] visited = new boolean[13];
	static List<Node> toBeAddedLocation = new ArrayList<>();

	public static void backTracking(int depth) {
		if (flag) return;
		if (depth == toBeAddedLocation.size()) {
			if (checkAll()) {
				answer = new char[5][9];
				for (int i = 0; i < 5; i++) {
					for (int j = 0; j < 9; j++) {
						answer[i][j] = star[i][j] == -1 ? '.' : (char) (star[i][j] + 'A' - 1);
					}
				}
				flag = true;
			}
			return;
		}

		for (int i = 1; i < 13; i++) {
			if (!visited[i]) {
				visited[i] = true;
				star[toBeAddedLocation.get(depth).x][toBeAddedLocation.get(depth).y] = i;
				backTracking(depth + 1);
				star[toBeAddedLocation.get(depth).x][toBeAddedLocation.get(depth).y] = EMPTY;
				visited[i] = false;
			}
		}
	}

	/**
	 * (0,4) : 노랑, 진파랑
	 * (1,1) : 연두, 파랑
	 * (1,3) : 노랑, 파랑
	 * (1,5) : 진파랑, 파랑
	 * (1,7) : 파랑, 주황
	 * (2,2) : 연두, 노랑
	 * (2,6) : 주황, 진파랑
	 * (3,1) : 분홍, 노랑
	 * (3,3) : 연두, 분홍
	 * (3,5) : 주황, 분홍
	 * (3,7) : 진파랑, 분홍
	 * (4,4) : 연두, 주황
	 */
	public static boolean isYellowSum26() {
		int sum = 0;
		for (int i = 0; i < 4; i++) {
			sum += star[i][4 - i];
		}
		return sum == 26;
	}

	public static boolean isJinBlueSum26() {
		int sum = 0;
		for (int i = 0; i < 4; i++) {
			sum += star[i][4 + i];
		}
		return sum == 26;
	}

	public static boolean isBlueSum26() {
		int sum = 0;
		for (int i = 0; i < 4; i++) {
			sum += star[1][2 * i + 1];
		}
		return sum == 26;
	}

	public static boolean isGreenSum26() {
		int sum = 0;
		for (int i = 0; i < 4; i++) {
			sum += star[i + 1][i + 1];
		}
		return sum == 26;
	}

	public static boolean isPinkSum26() {
		int sum = 0;
		for (int i = 0; i < 4; i++) {
			sum += star[3][2 * i + 1];
		}
		return sum == 26;
	}

	public static boolean isOrangeSum26() {
		int sum = 0;
		for (int i = 0; i < 4; i++) {
			sum += star[i + 1][7 - i];
		}
		return sum == 26;
	}

	public static boolean checkAll() {
		return isOrangeSum26() && isYellowSum26() && isPinkSum26() &&
				isGreenSum26() && isBlueSum26() && isJinBlueSum26();
	}

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		star = new int[5][9];
		for (int i = 0; i < 5; i++) {
			char[] line = br.readLine().toCharArray();
			for (int j = 0; j < 9; j++) {
				star[i][j] = line[j] == '.' ? -1 : line[j] == 'x' ? 0 : (int) line[j] - 'A' + 1;
				if (star[i][j] > 0) visited[star[i][j]] = true;
				if (star[i][j] == 0) {
					toBeAddedLocation.add(new Node(i, j)); // 채워져야할 곳의 좌표를 저장하는 리스트
				}
			}
		}

		backTracking(0);

		printAnswer(bw);

		bw.flush();
		bw.close();
	}

	private static void printAnswer(BufferedWriter bw) throws IOException {
		for (char[] row : answer) {
			for (char col : row) bw.write(col + "");
			bw.write("\n");
		}
	}

	static class Node {
		int x;
		int y;

		public Node(int x, int y) {
			this.x = x;
			this.y = y;
		}
	}
}
