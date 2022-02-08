package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

// baekJoon 2437 Gold3 저울
// https://m.blog.naver.com/PostView.naver?isHttpsRedirect=true&blogId=hongjg3229&logNo=221627349685 참고
public class G2437 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String line = br.readLine();

        int index = 0;
        int[] choo = new int[N];
        for (String l : line.split(" ")) choo[index++] = Integer.parseInt(l);
        Arrays.sort(choo);

        int sum = 0;
        for (int i = 0; i < N; i++) {
            if (sum + 2 <= choo[i]) break;
            sum += choo[i];
        }
        System.out.println(sum + 1);
    }
}
