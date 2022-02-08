package Implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

// baekJoon 17144 Gold4 미세먼지 안녕!
public class G17144 {
	static final int UPSIDE_CLEANER = -1;
	static final int DOWNSIDE_CLEANER = -2;

	static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int R,C,T;
	static int[][] dustMap;

	public static void main(String[] args) throws IOException {
		init();



		bw.flush();
		bw.close();
	}

	private static void init() throws IOException {
		String[] line = br.readLine().split(" ");
		R = Integer.getInteger(line[0]);
		C = Integer.getInteger(line[1]);
		T = Integer.getInteger(line[2]);
		dustMap = new int[R][C];
		int cnt = 0;

		for (int i = 0; i < R; i++) {
			String[] info = br.readLine().split(" ");
			for (int j = 0; j < C; j++) {
				dustMap[i][j] = Integer.parseInt(info[i]);
				if (dustMap[i][j] == -1) {
					dustMap[i][j] = -1 + (cnt--);
				}
			}
		}
	}
}
