package algorithm.programmers.level1;

import java.util.Arrays;
import java.util.Comparator;

public class MinRectang {
	public static void main(String[] args) {
//		int[][] sizes = {
//				{60, 50},
//				{30, 70},
//				{60, 30},
//				{80, 40}
//		};
		int[][] sizes = {
				{10, 7},
				{12, 3},
				{8, 15},
				{14, 7},
				{5, 15}
		};
		int answer = solution(sizes);
		System.out.println("answer = " + answer);
	}

	public static int solution(int[][] sizes) {
		for (int i = 0; i < sizes.length; i++) {
			if (sizes[i][0] > sizes[i][1]) {
				int temp = sizes[i][0];
				sizes[i][0] = sizes[i][1];
				sizes[i][1] = temp;
			}
		}

		int rowMax = -1;
		int colMax = -1;
		for (int[] size : sizes) {
			rowMax = Math.max(size[0], rowMax);
			colMax = Math.max(size[1], colMax);
		}
		return rowMax * colMax;
	}
}
