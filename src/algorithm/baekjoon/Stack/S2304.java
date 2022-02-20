package algorithm.baekjoon.Stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

// baekJoon 2304 silver2 창고 다각형
public class S2304 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

		int N = Integer.parseInt(br.readLine());
		List<Point> points = new ArrayList<>();
		for (int i = 0; i < N; i++) {
			String[] line = br.readLine().split(" ");
			points.add(new Point(Integer.parseInt(line[0]), Integer.parseInt(line[1])));
		}

		points.sort(Comparator.comparingInt(o -> o.L));
		int maxHeight = points.stream().max(Comparator.comparingInt(o -> o.H)).orElse(null).H;

		int size = 0;
		int backExLocation = points.get(points.size() - 1).L;
		int backCurrentMaxHeight = points.get(points.size() - 1).H;
		for (int i = points.size() - 2; i >= 0; i--) {
			int location = points.get(i).L;
			int height = points.get(i).H;

			if (height > backCurrentMaxHeight) {
				size += backCurrentMaxHeight * (backExLocation - location);
				backCurrentMaxHeight = height;
				backExLocation = location;
			}
		}

		int frontExLocation = points.get(0).L;
		int frontCurrentMaxHeight = points.get(0).H;
		for (int i = 1; i < points.size(); i++) {
			int location = points.get(i).L;
			int height = points.get(i).H;

			if (height > frontCurrentMaxHeight) {
				size += frontCurrentMaxHeight * (location - frontExLocation);
				frontCurrentMaxHeight = height;
				frontExLocation = location;
			}
		}

		int maxHeightMaxLocation = -1;
		int maxHeightMinLocation = 1001;
		for (Point point : points) {
			int location = point.L;
			int height = point.H;

			if (height == maxHeight) {
				maxHeightMaxLocation = Math.max(maxHeightMaxLocation, location);
				maxHeightMinLocation = Math.min(maxHeightMinLocation, location);
			}
		}

		if (maxHeightMaxLocation == maxHeightMinLocation) {
			size += maxHeight;
		} else {
			size += (maxHeightMaxLocation - maxHeightMinLocation + 1) * maxHeight;
		}

		System.out.println(size);
	}

	static class Point {
		int L;
		int H;

		public Point(int l, int h) {
			L = l;
			H = h;
		}
	}
}
