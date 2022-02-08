package Sort;

import java.io.*;
import java.util.*;

// baekJoon 10814 silver5 나이순 정렬 - class 2+
public class S10814 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        List<Member> members = new ArrayList<>();

        int N = Integer.parseInt(br.readLine());
        while (N-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            members.add(new Member(Integer.parseInt(st.nextToken()), st.nextToken()));
        }

        Collections.sort(members, new Comparator<Member>() {
            @Override
            public int compare(Member o1, Member o2) {
                if(o1.age == o2.age) return 0;
                else return o1.age - o2.age;
            }
        });

        for (Member member : members) {
            bw.write(member.toString());
        }

        bw.flush();
        bw.close();
    }

    private static class Member {
        private final Integer age;
        private final String name;

        public Member(Integer age, String name) {
            this.age = age;
            this.name = name;
        }

        public String toString() {
            return age + " " + name + "\n";
        }
    }
}
