package algorithm.baekjoon.BruteForcing;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

public class G1759 {
    static int L, C;
    static char[] givens;
    static char[] code;
    static boolean[] visited;
    static Set<String> codes = new HashSet<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        L = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());
        givens = new char[C];
        code = new char[L];
        visited = new boolean[C];

        int idx = 0;
        String line = br.readLine();
        for (String str : line.split(" ")) {
            givens[idx++] = str.charAt(0);
        }

        Arrays.sort(givens);

        backTracking(0, 0);

        List<String> answerCodes = new ArrayList<>(codes);
        Collections.sort(answerCodes);

        for (String answerCode : answerCodes) {
            bw.write(answerCode + "\n");
        }

        bw.flush();
        bw.close();
    }

    private static void backTracking(int depth, int at) {
        if (depth == L) {
            if (!conditionCheck(code)) return;

            StringBuilder answerCode = new StringBuilder();
            for (char c : code) {
                answerCode.append(c);
            }
            codes.add(answerCode.toString());
            return;
        }

        for (int i = at; i < givens.length; i++) {
            code[depth] = givens[i];
            backTracking(depth + 1, i + 1);
        }
    }

    private static boolean conditionCheck(char[] code) {
        if (code.length < 3) return false;

        int consonant = 0;
        int vowel = 0;
        for (char c : code) {
            if (c == 'a' || c == 'e' || c == 'i' || c == 'o' || c == 'u') consonant++;
            else vowel++;
        }
        return consonant >= 1 && vowel >= 2;
    }
}
