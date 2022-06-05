package algorithm.programmers.leveltest.test220605;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Solution1 {
    public static void main(String[] args) {
        String s = " 3people unFollowed me   ";
////        String s = "the jaejun is   boy";
//        String s = " a 1aA AaA G  G a    a   a  ";
        String answer = solution(s);
        System.out.println("answer = " + answer +"|END");
    }

    public static String solution(String s) {
        StringBuilder answer = new StringBuilder();
        String[] alphabets = s.split("");
        String ex = "";
        for (String alphabet : alphabets) {
            answer.append(ex.equals(" ") ? alphabet.toUpperCase() : alphabet.toLowerCase());
            ex = alphabet;
        }

        return answer.toString();
    }
}
