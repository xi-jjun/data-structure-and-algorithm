package dynamicProgramming;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// baekJoon 11660 silver1 구간 합 구하기 5
public class S11660 {
	static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int[][] map;
	static int N, M, x1, x2, y1, y2;

	public static void main(String[] args) throws IOException {
		getNM();
		setMap();

		for (int i = 0; i < M; i++) {
			getCoords();
			int answer = map[x2][y2] - (map[x1 - 1][y2] + map[x2][y1 - 1] - map[x1 - 1][y1 - 1]);
			bw.write(answer + "\n");
		}

		bw.flush();
		bw.close();
	}

	private static void getCoords() throws IOException {
		String[] coords = br.readLine().split(" ");
		x1 = Integer.parseInt(coords[0]);
		y1 = Integer.parseInt(coords[1]);
		x2 = Integer.parseInt(coords[2]);
		y2 = Integer.parseInt(coords[3]);
	}

	private static void setMap() throws IOException {
		map = new int[N + 1][N + 1];
		for (int i = 1; i <= N; i++) {
			String[] numbers = br.readLine().split(" ");
			for (int j = 1; j <= N; j++) {
				map[i][j] = map[i - 1][j] + map[i][j - 1] - map[i - 1][j - 1] + Integer.parseInt(numbers[j - 1]);
			}
		}
	}

	private static void getNM() throws IOException {
		String[] nm = br.readLine().split(" ");
		N = Integer.parseInt(nm[0]);
		M = Integer.parseInt(nm[1]);
	}
}
