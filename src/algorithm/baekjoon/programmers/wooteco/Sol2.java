package programmers.wooteco;

public class Sol2 {
    public static String solution(String[] log) {
        String answer = "";
        int totalTime = 0;
        for (int i = 0; i < log.length; i += 2) {
            String start = log[i];
            String stop = log[i + 1];
            int time = calTime(start, stop);
            totalTime += time;
        }
        answer = formatTime(totalTime);
        return answer;
    }

    private static String formatTime(int time) {
        String hour = time / 60 + "";
        String min = time % 60 + "";
        while (hour.length() != 2) hour = "0" + hour;
        while (min.length() != 2) min = "0" + min;
        return hour + ":" + min;
    }

    private static int calTime(String start, String stop) {
        String[] startTime = start.split(":");
        String[] stopTime = stop.split(":");
        int startHH = Integer.parseInt(startTime[0]);
        int startMM = Integer.parseInt(startTime[1]);
        int stopHH = Integer.parseInt(stopTime[0]);
        int stopMM = Integer.parseInt(stopTime[1]);

        int hour = stopHH - startHH;
        int min = stopMM - startMM;

        int result = 60 * (hour) + min;

        if (result < 5) return 0;
        else return Math.min(result, 105);
    }

    public static void main(String[] args) {
        String[] log = {"08:30", "09:00", "14:00", "16:00", "16:01", "16:06", "16:07", "16:11"};
        String[] log2 = {"08:30", "09:30", "9:31", "10:30", "10:30", "11:30", "11:31", "12:30", "12:31", "13:30", "14:00", "16:00", "16:01", "16:06",
                "16:07", "16:11", "16:12", "16:17", "16:20", "17:11", "18:07", "18:51", "18:52", "19:52", "19:52", "20:52", "20:52", "21:52"};
        System.out.println(solution(log2));
    }
}
