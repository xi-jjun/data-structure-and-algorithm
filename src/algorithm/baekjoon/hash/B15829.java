package hash;

import java.math.BigInteger;
import java.util.Scanner;

// baekJoon 15829 Bronze2 Hashing - class2++
public class B15829 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        sc.nextLine();
        String word = sc.nextLine();
        sc.close();
        BigInteger hashcode = new BigInteger("0");
        BigInteger NUM_31 = new BigInteger("31");
        BigInteger M = new BigInteger("1234567891");
        int bias = 0;

        for (String w : word.split("")) {
            BigInteger n = new BigInteger((w.charAt(0) - 96) + "");
            BigInteger k = new BigInteger("1");
            for (int i = 0; i < bias; i++) {
                k = k.multiply(NUM_31);
            }
            hashcode = hashcode.add(n.multiply(k));
            bias++;
        }
        System.out.println(hashcode.mod(M));
    }
}
