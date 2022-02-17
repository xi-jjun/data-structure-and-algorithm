package algorithm.kadane;

/**
 * Kadane's Algorithm : maximum subarray 구하는 효율적인 방법
 * reference : https://medium.com/@rsinghal757/kadanes-algorithm-dynamic-programming-how-and-why-does-it-work-3fd8849ed73d
 *
 * "각각의 최대 부분합은 이전 최대 부분합이 반영된 결과값이다"
 * time complexity : O(N)
 */
public class KadaneAlgorithm {
	public static void main(String[] args) {
		int[] testCase = {-2, 1, -3, 4, -1, 2, 1, -5, 4};

		/**
		 * dp[k] : k번째 index 를 마지막으로 하는 부분합의 최대값
		 */
		int[] dp = new int[testCase.length];
		dp[0] = testCase[0];

		for (int i = 1; i < testCase.length; i++) {
			dp[i] = Math.max(dp[i - 1] + testCase[i], testCase[i]);
		}

		System.out.println("=== dp array ===");
		for (int result : dp) {
			System.out.print(result+" ");
		}
	}
}
