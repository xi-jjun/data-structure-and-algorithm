package set;

import java.io.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

// baekJoon 11723 silver5 집합

/**
 * add x: S에 x를 추가한다. (1 ≤ x ≤ 20) S에 x가 이미 있는 경우에는 연산을 무시한다.
 * remove x: S에서 x를 제거한다. (1 ≤ x ≤ 20) S에 x가 없는 경우에는 연산을 무시한다.
 * check x: S에 x가 있으면 1을, 없으면 0을 출력한다. (1 ≤ x ≤ 20)
 * toggle x: S에 x가 있으면 x를 제거하고, 없으면 x를 추가한다. (1 ≤ x ≤ 20)
 * all: S를 {1, 2, ..., 20} 으로 바꾼다.
 * empty: S를 공집합으로 바꾼다.
 */
public class S11723 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int M = Integer.parseInt(br.readLine());
        List<Integer> ALL_CONST = new ArrayList<>();
        for (int i = 1; i < 21; i++) ALL_CONST.add(i);
        Set<Integer> answer = new HashSet<>();

        while (M-- > 0) {
            String[] line = br.readLine().split(" ");
            String cmd = line[0];
            if (cmd.charAt(0) == 'a' && line.length == 2) answer.add(Integer.parseInt(line[1]));
            else if (cmd.charAt(0) == 'a' && line.length == 1) answer = new HashSet<>(ALL_CONST);
            else if (cmd.charAt(0) == 'r') answer.remove(Integer.parseInt(line[1]));
            else if (cmd.charAt(0) == 'c') bw.write((answer.contains(Integer.parseInt(line[1])) ? 1 : 0) + "\n");
            else if (cmd.charAt(0) == 't') {
                int value = Integer.parseInt(line[1]);
                if (answer.contains(value)) answer.remove(value);
                else answer.add(value);
            } else answer.removeAll(answer);
        }

        bw.flush();
        bw.close();
    }
}
