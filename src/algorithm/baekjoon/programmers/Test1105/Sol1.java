package programmers.Test1105;

// 정답
public class Sol1 {
    public static int[][] solution(int[][] arr1, int[][] arr2) {
        int[][] answer = new int[arr1.length][arr2[0].length];
        for (int i = 0; i < answer.length; i++) {
            for (int j = 0; j < answer[0].length; j++) {
                for (int k = 0; k < arr2.length; k++) {
                    answer[i][j] += arr1[i][k]*arr2[k][j];
                }
            }
        }
        return answer;
    }

    public static void main(String[] args) {
        int[][] a ={{2, 3, 2}, {4, 2, 4}, {3, 1, 4}};
        int[][] b = {{5, 4, 3}, {2, 4, 1}, {3, 1, 1}};
        for (int[] ints : solution(a, b)) {
            for (int anInt : ints) {
                System.out.print(anInt+" ");
            }
            System.out.println("");
        }
    }
}
