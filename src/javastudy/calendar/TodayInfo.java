package javastudy.calendar;

import java.util.Calendar;

public class TodayInfo {
    public static void main(String[] args) {
        final String[] week = {"토요일", "일요일", "월요일", "화요일", "수요일", "목요일", "금요일"};
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(Calendar.DAY_OF_WEEK);
        System.out.println("i = " + i);
        System.out.println("오늘은 " + week[i]);
    }
}
