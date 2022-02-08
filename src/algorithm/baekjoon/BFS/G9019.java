package BFS;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// baekJoon 9019 Gold5 DSLR
// 문제 유형만 확인.
public class G9019 {
    static String answer;
    static boolean[] visited;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int T = Integer.parseInt(br.readLine());

        while (T-- > 0) {
            visited = new boolean[10001];
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            bfs(A, B);
            bw.write(answer + "\n");
        }

        bw.flush();
        bw.close();
    }

    private static void bfs(int A, int ans) {
        Queue<OpInfo> info = new ArrayDeque<>();
        info.offer(new OpInfo(A, ""));
        visited[A] = true;

        while (!info.isEmpty()) {
            boolean[] end = new boolean[4];
            int value = info.peek().value;
            String opCode = info.peek().opCode;
            info.poll();

            end[0] = opD(value, ans, opCode, info);
            end[1] = opS(value, ans, opCode, info);
            end[2] = opL(value, ans, opCode, info);
            end[3] = opR(value, ans, opCode, info);
            for (boolean e : end) if (e) return;
        }
    }

    private static boolean opR(int value, int ans, String exOpCode, Queue<OpInfo> info) {
        StringBuilder number = new StringBuilder(value + "");
        while (number.length() != 4) {
            number.insert(0, "0");
        }
        String temp = number.toString();
        temp = temp.charAt(3) + temp.substring(0, temp.length() - 1);

        value = Integer.parseInt(temp);
        if (value == ans) {
            answer = exOpCode + "R";
            return true;
        }

        if (!visited[value]) {
            info.offer(new OpInfo(value, exOpCode + "R"));
            visited[value] = true;
        }
        return false;
    }

    private static boolean opL(int value, int ans, String exOpCode, Queue<OpInfo> info) {
        StringBuilder number = new StringBuilder(value + "");
        while (number.length() != 4) {
            number.insert(0, "0");
        }
        String temp = number.toString();
        temp = temp.substring(1) + temp.charAt(0);

        value = Integer.parseInt(temp);
        if (value == ans) {
            answer = exOpCode + "L";
            return true;
        }

        if (!visited[value]) {
            info.offer(new OpInfo(value, exOpCode + "L"));
            visited[value] = true;
        }
        return false;
    }

    private static boolean opS(int value, int ans, String exOpCode, Queue<OpInfo> info) {
        value = value - 1 < 0 ? 9999 : value - 1;
        if (value == ans) {
            answer = exOpCode + "S";
            return true;
        }
        if (!visited[value]) {
            info.offer(new OpInfo(value, exOpCode + "S"));
            visited[value] = true;
        }
        return false;
    }

    private static boolean opD(int value, int ans, String exOpCode, Queue<OpInfo> info) {
        value = (value * 2) % 10000;
        if (value == ans) {
            answer = exOpCode + "D";
            return true;
        }
        if (!visited[value]) {
            info.offer(new OpInfo(value, exOpCode + "D"));
            visited[value] = true;
        }
        return false;
    }

    static class OpInfo {
        int value;
        String opCode;

        public OpInfo(int value, String opCode) {
            this.value = value;
            this.opCode = opCode;
        }
    }
}
