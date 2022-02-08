package programmers.level1;

import java.util.Locale;

// programmers 신규 아이디 추천 - 2021 KAKAO BLIND RECRUITMENT
//        2단계 new_id에서 알파벳 소문자, 숫자, 빼기(-), 밑줄(_), 마침표(.)를 제외한 모든 문자를 제거합니다.
//        3단계 new_id에서 마침표(.)가 2번 이상 연속된 부분을 하나의 마침표(.)로 치환합니다.
//        4단계 new_id에서 마침표(.)가 처음이나 끝에 위치한다면 제거합니다.
//        5단계 new_id가 빈 문자열이라면, new_id에 "a"를 대입합니다.
//        6단계 new_id의 길이가 16자 이상이면, new_id의 첫 15개의 문자를 제외한 나머지 문자들을 모두 제거합니다.
//        만약 제거 후 마침표(.)가 new_id의 끝에 위치한다면 끝에 위치한 마침표(.) 문자를 제거합니다.
//        7단계 new_id의 길이가 2자 이하라면, new_id의 마지막 문자를 new_id의 길이가 3이 될 때까지 반복해서 끝에 붙입니다.
//        -_.~!@#$%^&*()=+[{]}:?,<>/
public class NewIdRecommand {
    private final char OK1 = '-'; // 45 95_ 46.

    public static String solution(String new_id) {
        String answer = "";
        String firstResult = new_id.toLowerCase(); // 1단계

        // step 2
        StringBuilder second = new StringBuilder();
        for (String f : firstResult.split("~|!|@|#|\\$|%|\\^|&|\\*|\\(|\\)|=|\\+|\\[|\\{|]|}|:|\\?|,|<|>|/")) { // "[~!@#$%^&*()=+\\[{\\]}:?,<>/]"
            second.append(f);
        }

        // step 3
        String third = second.toString().replaceAll("\\.{2,}", ".");

        // step 4
        if (third.charAt(0) == '.') third = third.substring(1);
        if (third.length() != 0) {
            if (third.charAt(third.length() - 1) == '.') third = third.substring(0, third.length() - 1);
        }

        // step 5
        if (third.length() == 0) third = "a";

        // step 6
        if (third.length() >= 16) {
            third = third.substring(0, 15);
            if (third.charAt(third.length() - 1) == '.') third = third.substring(0, third.length() - 1);
        }

        // step 7
        StringBuilder seven = new StringBuilder(third);
        if (seven.length() <= 2) {
            while (seven.length() != 3) {
                seven.append(seven.charAt(seven.length() - 1));
            }
        }

        return seven.toString();
    }

    public static void main(String[] args) {
        String id = "...........([{}])?^..-_.~!@#$%^&*()=+[{]}:?,<>/..$$$.......";
        System.out.println("solution(id) = " + solution(id));
        StringBuilder sb = new StringBuilder(id);
        System.out.println("sb.toString().charAt(sb.length()-1) = " + sb.toString().charAt(sb.length() - 1));
    }
}
