package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.Queue;

// baekJoon 12904 Gold5 A와 B
// https://settembre.tistory.com/253 참고
public class G12904 {
//    static String TARGET;
//    static boolean ok = false;
//    static Map<String, Boolean> visited = new HashMap<>();
//
//    static String flipPlusB(String str) {
//        String[] s = str.split("");
//        StringBuilder re = new StringBuilder();
//
//        for (int i = str.length() - 1; i >= 0; i--) re.append(s[i]);
//
//        return re.append("B").toString();
//    }
//
//    static boolean isValid(String str) {
//        return !visited.containsKey(str) && str.length() <= TARGET.length();
//    }
//
//    static void bfs(String input) {
//        Queue<String> q = new ArrayDeque<>();
//        q.offer(input);
//
//        while (!q.isEmpty()) {
//            String str = q.peek();
//            q.poll();
//
//            if(str.equals(TARGET)) {
//                ok = true;
//                return;
//            }
//
//            if(isValid(str)) {
//                visited.put(str, true);
//                String plusA = str + "A";
//                String fB = flipPlusB(str);
//                q.offer(plusA);
//                q.offer(fB);
//            }
//        }
//    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        String input = br.readLine();
//        TARGET = br.readLine();

        String input = br.readLine();
        StringBuffer TARTGET = new StringBuffer(br.readLine());
        int len = TARTGET.length();
        boolean ok = false;

        while (len-- > 0) {
            if(input.equals(TARTGET.toString())) {
                ok = true;
                break;
            }
            if (TARTGET.charAt(len) == 'A') TARTGET.deleteCharAt(len);
            else if (TARTGET.charAt(len) == 'B') {
                TARTGET.deleteCharAt(len);
                TARTGET.reverse();
            }
        }

//        bfs(input); // 시간초과

        if (ok) System.out.println("1");
        else System.out.println("0");
    }
}
