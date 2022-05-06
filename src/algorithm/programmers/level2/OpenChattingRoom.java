package algorithm.programmers.level2;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class OpenChattingRoom {
    static List<Message> messages = new ArrayList<>();
    static Map<String, User> users = new HashMap<>();

    public static void main(String[] args) {
        String[] record = {"Enter uid1234 Muzi", "Enter uid4567 Prodo", "Leave uid1234",
                "Enter uid1234 Prodo", "Change uid4567 Ryan"};
        String[] result = solution(record);
        for (String ans : result) {
            System.out.println("ans = " + ans);
        }
    }

    public static String[] solution(String[] records) {
        for (String record : records) {
            String[] info = record.split(" ");
            if (info[0].equals("Leave")) {
                String userId = info[1];
                User user = users.get(userId);
                messages.add(new Message(user, false));
            } else {
                String userId = info[1];
                String nickname = info[2];
                if (info[0].equals("Enter")) {
                    User user = users.containsKey(userId) ? users.get(userId) : new User(userId, nickname);
                    user.nickname = nickname;
                    if (!users.containsKey(userId)) users.put(userId, user);
                    Message message = new Message(user, true);
                    messages.add(message);
                } else {
                    User user = users.get(userId);
                    user.nickname = nickname;
                }
            }
        }

        String[] answer = new String[messages.size()];
        for (int i = 0; i < messages.size(); i++) {
            answer[i] = messages.get(i).toString();
        }

        return answer;
    }

    static class User {
        String id;
        String nickname;

        public User(String id, String nickname) {
            this.id = id;
            this.nickname = nickname;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            User user = (User) o;
            return this.id.equals(user.id);
        }

        @Override
        public int hashCode() {
            return Objects.hash(id);
        }
    }

    static class Message {
        User user;
        final boolean in;

        public Message(User user, boolean in) {
            this.user = user;
            this.in = in;
        }

        public String entrance(boolean in) {
            return in ? "들어왔습니다." : "나갔습니다.";
        }

        @Override
        public String toString() {
            return user.nickname + "님이 " + entrance(this.in);
        }
    }
}
