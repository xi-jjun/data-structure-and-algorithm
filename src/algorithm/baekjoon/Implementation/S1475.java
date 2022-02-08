package Implementation;

import java.util.Arrays;
import java.util.Scanner;

// baekJoon 1475 silver5 방 번호
public class S1475 {
    public static void main(String[] args) {
        int[] count = new int[10];
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt();

        while (true) {
            count[N % 10]++;
            N /= 10;
            if (N == 0) break;
        }
        int temp = (count[9] + count[6]) % 2 == 0 ? (count[9] + count[6]) / 2 : (count[9] + count[6]) / 2 + 1;
        count[6] = temp;
        count[9] = temp;
        Arrays.sort(count);

        System.out.println(count[9]);
    }
}
