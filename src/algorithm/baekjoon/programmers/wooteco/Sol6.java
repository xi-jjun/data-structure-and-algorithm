package programmers.wooteco;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Sol6 {
    static double[] FRI = {9.5, 18};
    static double[] MON = {13, 18};

    public static String solution(double time, String[][] plans) {
        String answer = "";
        List<Location> loc = new ArrayList<>();
        for (String[] plan : plans) {
            checkTime(loc, plan, time);
        }
        loc.sort((Comparator.comparingDouble(o -> o.lastArriveTime)));

        answer = loc.get(loc.size() - 1).name;
        return answer;
    }

    private static void checkTime(List<Location> location, String[] plan, double givenTime) {
        String city = plan[0];
        double dTime, aTime;
        dTime = (double) convertTime(plan[1]);
        aTime = (double) convertTime(plan[2]);

        int needTime = 0;
        if (FRI[1] - dTime > 0) needTime += FRI[1] - dTime;
        if (aTime - MON[0] > 0) needTime += aTime - MON[0];
        if (givenTime >= needTime) location.add(new Location(city, aTime));
    }

    private static int convertTime(String time) {
        if (time.contains("AM")) {
            time = time.replace("AM", "");
            return Integer.parseInt(time);
        } else {
            time = time.replace("PM", "");
            return Integer.parseInt(time) + 12;
        }
    }

    public static void main(String[] args) {
        double time = 3.5;
        String[][] p = {{"홍콩", "11PM", "9AM"}, {"엘에이", "3PM", "2PM"}, {"엘에이", "3PM", "1PM"}};
        System.out.println("solution(time ,p) = " + solution(time, p));
    }

    static class Location {

        String name;
        double lastArriveTime;

        public Location(String name, double lastArriveTime) {
            this.name = name;
            this.lastArriveTime = lastArriveTime;
        }

    }
}
