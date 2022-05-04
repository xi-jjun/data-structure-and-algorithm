package algorithm.programmers.level1;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

// Level1 신고 결과 받기 - 2022 KAKAO BLIND RECRUITMENT
public class GetReport {
    static Map<String, Integer> usersGetMailCount = new LinkedHashMap<>();
    static Map<String, Set<String>> canBeStoppedList = new HashMap<>();

    public static void main(String[] args) {
        String[] idList = {"muzi", "frodo", "apeach", "neo"};
        String[] report = {"muzi frodo", "apeach frodo", "frodo neo", "muzi neo", "apeach muzi"};
        int k = 2;
        int[] answer = solution(idList, report, k);
        for (int ans : answer) {
            System.out.println("ans = " + ans);
        }
    }

    public static int[] solution(String[] id_list, String[] report, int k) {
        for (String id : id_list) {
            usersGetMailCount.put(id, 0);
        }

        for (String detailReport : report) {
            String[] details = detailReport.split(" ");
            String reporter = details[0];
            String reportedPerson = details[1];
            canBeStoppedList.put(reportedPerson, canBeStoppedList.getOrDefault(reportedPerson, new HashSet<>()));
            Set<String> reporters = canBeStoppedList.get(reportedPerson);
            reporters.add(reporter);
        }

        for (Set<String> reporters : canBeStoppedList.values()) {
            if (reporters.size() >= k) {
                for (String reporter : reporters) {
                    usersGetMailCount.put(reporter, usersGetMailCount.get(reporter) + 1);
                }
            }
        }

        int[] answer = new int[id_list.length];
        int index = 0;
        for (int userGetMailCount : usersGetMailCount.values()) {
            answer[index++] = userGetMailCount;
        }

        return answer;
    }
}
