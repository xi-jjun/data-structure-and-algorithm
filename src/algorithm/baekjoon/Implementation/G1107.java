package Implementation;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

// baekJoon 1107 Gold5 리모컨
// https://mygumi.tistory.com/157 아이디어 참고
// 생각 좀 하고 풀자. bruteforce 인지 아닌지 정도는 알 수 있지 않았냐
public class G1107 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String N = br.readLine();
        int NN = Integer.parseInt(N);
        int M = Integer.parseInt(br.readLine());
        boolean[] cantUse = new boolean[10]; // false:can use , true:can't use
        List<String> notUse = new ArrayList<>();
        StringTokenizer st = null;

        if (M != 0)
            st = new StringTokenizer(br.readLine());
        while (M-- > 0) {
            int num = Integer.parseInt(st.nextToken());
            cantUse[num] = true;
            notUse.add(num + "");
        }

        List<Integer> answers = new ArrayList<>();
        boolean ok1 = true;

        // 1. 숫자 버튼만을 이용해 목표 채널 도달. - 답 : 목표 채널의 자릿수
        for (String n : N.split("")) {
            if (cantUse[n.charAt(0) - 48]) {
                ok1 = false;
                break;
            }
        }

        if (ok1) answers.add(N.length());

        // 2. 숫자버튼 + (+,-)로 도달
        int min = 1000001;
        for (int i = 0; i <= 1000000; i++) {
            boolean go = true;
            String check = i + "";

            for (String no : notUse) {
                if (check.contains(no)) {
                    go = false;
                    break;
                }
            }

            if (go) min = Math.abs(i-NN) >= Math.abs(NN-min) ? min : i;//Math.min(Math.abs(i - NN), min);
        }

        String len = min + "";
        answers.add(Math.abs(NN-min) + len.length());

        // 3. +,-로만 도달
        answers.add(Math.abs(NN - 100));

        answers.sort(Comparator.comparingInt(o -> o));

        System.out.println(answers.get(0));
    }
}
