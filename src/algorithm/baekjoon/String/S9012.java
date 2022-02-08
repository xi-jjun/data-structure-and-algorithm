package String;

import java.io.*;

// baekJoon 9012 silver4 괄호
public class S9012 {
    static boolean solution(String s) {
        boolean answer = true;
        int count = 0;

        if (s.length() % 2 != 0) return false;

        for (String ss : s.split("")) {
            if (ss.equals("(")) count++;
            else count--;

            if (count < 0) {
                answer = false;
                return answer;
            }
        }
        if (count != 0) answer = false;

        return answer;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int N = Integer.parseInt(br.readLine());
        for(int i=0;i<N;i++) {
            if(solution(br.readLine())) bw.write("YES\n");
            else bw.write("NO\n");
        }
        bw.flush();
        bw.close();

    }
}
