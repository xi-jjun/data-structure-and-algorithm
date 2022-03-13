package algorithm.programmers.sktIct;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class Solution1 {
	public static void main(String[] args) {
		int money = 1999;
		int[] costs = new int[]{2, 11, 20, 100, 200, 600};
		int answer = solution(money, costs);
		System.out.println("answer = " + answer);
	}

	public static int solution(int money, int[] costs) {
		int answer = 0;
		final int[] KEYS = new int[]{1, 5, 10, 50, 100, 500};
		Map<Integer, Integer> costMapper = new LinkedHashMap<>();
		for (int i = costs.length - 1; i >= 0; i--) {
			costMapper.put(KEYS[i], costs[i]);
		}

		while (money != 0) {
			int minCost = Integer.MAX_VALUE;
			int minCoin = Integer.MAX_VALUE;

			for (Map.Entry<Integer, Integer> costEntry : costMapper.entrySet()) {
				int count = money / costEntry.getKey(); // 최소비용일 때 개수
				int nowCost = count * costEntry.getValue();

				if (minCost > nowCost && nowCost != 0) {
					if (nowCost + (money - nowCost) * costMapper.get(1) <= money * costMapper.get(1)) {
						continue;
					} else {
						minCost = nowCost; // 최소 비용
						minCoin = costEntry.getKey(); // 그 때 동전 단위
					}
				}
			}
			money = money % minCoin;
			answer += minCost;
		}
		return answer;
	}
}
