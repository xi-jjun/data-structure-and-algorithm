package hash;

import java.io.*;
import java.util.HashMap;

// baekJoon 10826 silver4 숫자 카드2 - class2+
public class S10816 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        HashMap<Integer, Integer> card = new HashMap<>();

        int __ = Integer.parseInt(br.readLine());

        String[] numbers = br.readLine().split(" ");
        for (String number : numbers) {
            int num = Integer.parseInt(number);
            card.put(num, card.getOrDefault(num, 0) + 1);
        }
        int _ = Integer.parseInt(br.readLine());

        String[] numbers2 = br.readLine().split(" ");
        for (String number2 : numbers2) {
            int num2 = Integer.parseInt(number2);
            bw.write((card.get(num2) != null ? card.get(num2) : 0) + " ");
        }

        bw.flush();
        bw.close();
    }
}
