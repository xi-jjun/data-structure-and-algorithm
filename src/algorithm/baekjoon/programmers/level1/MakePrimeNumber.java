package programmers.level1;

// programmers Summer/Winter Coding(~2018) > 소수 만들기
public class MakePrimeNumber {
    static int count = 0;
    static boolean[] visited;
    static int[] global = new int[3];

    static void backTracking(int depth, int at, int[] numbers) {
        if (depth == 3) {
            if (isPrime(global)) count++;
            return;
        }

        for (int i = at; i < numbers.length; i++) {
            if (!visited[i]) {
                visited[i] = true;
                global[depth] = numbers[i];
                backTracking(depth + 1, i + 1, numbers);
                visited[i] = false;
            }
        }
    }

    private static boolean isPrime(int[] numbers) {
        int sum = numbers[0] + numbers[1] + numbers[2];
        for (int i = 2; i * i <= sum; i++) {
            if (sum % i == 0) return false;
        }

        return true;
    }

    public static int solution(int[] nums) {
        visited = new boolean[nums.length];

        backTracking(0, 0, nums);

        return count;
    }

    public static void main(String[] args) {
        int[] n = {1,2,7,6,4};
//        int[] n = {1,2,3,4};
        System.out.println("solution(nm) = " + solution(n));
    }
}
