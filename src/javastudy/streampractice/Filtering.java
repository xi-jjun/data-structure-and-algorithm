package javastudy.streampractice;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Filtering {
    public static void main(String[] args) {
        List<Problem> problemList = new ArrayList<>();
        problemList.add(new Problem(1000L, "A+B", 1, "Math,Implementation"));
        problemList.add(new Problem(1206L, "사람의 수", 8, "Math,Bruteforce"));
        problemList.add(new Problem(1002L, "종이 자르기", 14, "Backtracking,Bruteforce,Implementation"));
        problemList.add(new Problem(1003L, "A/B", 1, "Math,Implementation"));
        problemList.add(new Problem(1004L, "A+B", 1, "Math,Implementation"));

        final int FILTER_LEVEL = 1;

        List<Problem> levelFilteredProblemList = problemList.stream()
                .filter(p -> p.level == FILTER_LEVEL)
                .collect(Collectors.toList());

        for (Problem problem : levelFilteredProblemList) {
            System.out.println("problem.name = " + problem.name);
        }
    }

    static class Problem {
        Long id;
        String name;
        int level;
        String tags;

        public Problem(Long id, String name, int level, String tags) {
            this.id = id;
            this.name = name;
            this.level = level;
            this.tags = tags;
        }
    }
}
