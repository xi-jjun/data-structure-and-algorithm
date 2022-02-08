package String;

import java.io.*;
import java.util.ArrayDeque;
import java.util.Deque;

// baekJoon 5430 Gold5 AC
public class G5430 {
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

    public static boolean isInteger(String s) {
//        System.out.println(String.format("Parsing string value: \"%s\"", s));

        if (s == null || s.equals("")) {
//            System.out.println("Cannot parse the string since it either null or empty");
            return false;
        }

        try {
            int iVal = Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
//            System.out.println("Cannot parse the string to integer");
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
        int T = Integer.parseInt(br.readLine());

        for (int i = 0; i < T; i++) {
            String p = br.readLine(); // functions RDD..
            int n = Integer.parseInt(br.readLine());
            Deque<Integer> deque = new ArrayDeque<>();
            String elements = br.readLine(); // == [1,2,3,4]

            for (String e : elements.split(",|\\[|\\]")) {
                if (isInteger(e)) deque.add(Integer.parseInt(e)); // deque : 1 2 3 4
            }

            boolean mode = false; // fasle: first mode , true: last mode
            boolean ERROR = false;

            for (String func : p.split("")) {
                if (func.equals("R")) mode = !mode;
                else if (func.equals("D")) {
                    if (deque.isEmpty()) {
                        ERROR = true;
                        break;
                    }
                    if (mode) deque.removeLast();
                    else deque.removeFirst();
                }
            }

            if(ERROR) bw.write("error\n");
            else if(mode) {
                bw.write("[");
                while (!deque.isEmpty()) {
                    bw.write(deque.removeLast() + "");
                    if(!deque.isEmpty()) bw.write(",");
                }
                bw.write("]\n");
            } else {
                bw.write("[");
                while (!deque.isEmpty()) {
                    bw.write(deque.removeFirst() + "");
                    if(!deque.isEmpty()) bw.write(",");
                }
                bw.write("]\n");
            }
        }

        bw.flush();
        bw.close();
    }
}
