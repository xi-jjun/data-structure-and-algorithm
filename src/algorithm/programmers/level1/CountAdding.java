package algorithm.programmers.level1;

public class CountAdding {
	public int solution(int left, int right) {
		int answer = 0;
		for (int target = left; target <= right; target++) {
			if (find(target)) answer += target;
			else answer -= target;
		}
		return answer;
	}

	/**
	 * 그냥 간단하게
	 * 제곱수 => 약수 홀수개
	 * else => 약수 짝수개
	 * 참고 : https://programmers.co.kr/learn/courses/30/lessons/77884/solution_groups?language=java
	 * @param target
	 * @return
	 */
	public boolean find(int target) {
		int ret = 0;
		for (int i = 1; i * i <= target; i++) {
			if (target % i == 0) {
				if (target == i * i) ret++;
				else ret += 2;
			}
		}
		return ret % 2 == 0;
	}
}
