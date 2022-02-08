package hash;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// baekJoon 2002 silver1 추월
public class S2002 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		Map<String, Integer> firstInCarList = new HashMap<>();
		List<String> outCarList = new ArrayList<>();

		for (int inPosition = 0; inPosition < N; inPosition++) {
			String name = br.readLine();
			firstInCarList.put(name, inPosition);
		}

		int result = 0;
		for (int outPosition = 0; outPosition < N; outPosition++) {
			String name = br.readLine();
			outCarList.add(name);
		}

		int min = 1001;
		for (int i = outCarList.size() - 1; i >= 0; i--) {
			String name = outCarList.get(i);
			Integer inCarPosition = firstInCarList.get(name);
			if (inCarPosition > min) {
				result++;
			} else {
				min = inCarPosition;
			}
		}

		bw.write(result + "\n");

		bw.flush();
		bw.close();
	}
}
