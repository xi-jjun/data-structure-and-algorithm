package BackTracking;

import java.io.*;

// baekJoon 9095 silver3 1,2,3더하기
public class S9095 {
    static int count = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int T = Integer.parseInt(br.readLine());

        while (T-->0){
            int n = Integer.parseInt(br.readLine());
            backTracking(n, 0);
            bw.write( count + "\n");
            count = 0;
        }

        bw.flush();
        bw.close();
    }

    private static void backTracking(int limit, int sum) {
        if(sum>=limit){
            if(sum == limit) count++;
            return;
        }

        for (int i = 1; i <= 3; i++) {
            sum += i;
            backTracking(limit, sum);
            sum -= i;
        }
    }
}
