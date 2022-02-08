package BFS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

// baekJoon 13549 Gold5 숨박꼭질 3
public class G13549 {
	static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
	static int N, K;
	static int result = Integer.MAX_VALUE;
	static boolean[] isVisited;

	public static void main(String[] args) throws IOException {
		String[] line = br.readLine().split(" ");
		N = Integer.parseInt(line[0]);
		K = Integer.parseInt(line[1]);
		isVisited = new boolean[100001];

		if (N == K) {
			bw.write("0");
		} else if (N > K) {
			bw.write((N - K) + "");
		} else {
			bfs(N);
			bw.write(result + "");
		}

		bw.flush();
		bw.close();
	}
	// 123 9325

	private static void bfs(int start) {
		PriorityQueue<Subin> pq = new PriorityQueue<>(Comparator.comparing(o -> o.accTime));
		pq.offer(new Subin(start, 0));

		while (!pq.isEmpty()) {
			int location = pq.peek().location;
			int time = pq.peek().accTime;
			pq.poll();

			if (location == K) {
				result = Math.min(result, time);
				continue;
			}

			if (isValidLocation(location)) {
				isVisited[location] = true;

				pq.offer(new Subin(location - 1, time + 1));
				pq.offer(new Subin(location + 1, time + 1));
				pq.offer(new Subin(location * 2, time));
			}
		}
	}

	private static boolean isValidLocation(int location) {
		return location >= 0 && location <= 100000 && !isVisited[location];
	}

	static class Subin {
		int location;
		int accTime;

		public Subin(int location, int accTime) {
			this.location = location;
			this.accTime = accTime;
		}
	}
}
