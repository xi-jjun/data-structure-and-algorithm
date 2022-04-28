package algorithm.baekjoon.BFS;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.StringTokenizer;

public class S9205 {
    static int n;
    static int endX, endY;
    static Set<Location> visited = new HashSet<>();
    static List<Location> convenientStores;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int t = Integer.parseInt(br.readLine());

        for (int tc = 0; tc < t; tc++) {
            n = Integer.parseInt(br.readLine());
            convenientStores = new ArrayList<>();

            StringTokenizer st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                convenientStores.add(
                        new Location(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()))
                );
            }

            st = new StringTokenizer(br.readLine());
            endX = Integer.parseInt(st.nextToken());
            endY = Integer.parseInt(st.nextToken());

            visited = new HashSet<>();
            Location end = new Location(endX, endY);
            Location now = new Location(x, y);

            if (bfs(now, end)) {
                bw.write("happy\n");
            } else {
                bw.write("sad\n");
            }
        }

        bw.flush();
        bw.close();
    }

    private static boolean bfs(Location now, Location end) {
        Queue<Location> q = new ArrayDeque<>();
        q.offer(new Location(now));
        visited.add(new Location(now));

        while (!q.isEmpty()) {
            Location curr = q.poll();
            if (isEnoughDistance(curr, end, 20)) {
                return true;
            }

            for (Location convenientStore : convenientStores) {
                if (isNotVisited(convenientStore)) {
                    if (isEnoughDistance(curr, convenientStore, 20)) {
                        visited.add(new Location(convenientStore));
                        q.offer(new Location(convenientStore));
                    }
                }
            }
        }

        return false;
    }

    private static boolean isNotVisited(Location convenientStore) {
        return !visited.contains(convenientStore);
    }

    private static boolean isEnoughDistance(Location now, Location conv, int beer) {
        return getDistance(now, conv) <= beer * 50;
    }

    public static int getDistance(Location loc1, Location loc2) {
        return Math.abs(loc1.x - loc2.x) + Math.abs(loc1.y - loc2.y);
    }

    static class Location implements Comparable<Location> {
        int x, y;

        public Location(Location location) {
            this.x = location.x;
            this.y = location.y;
        }

        public Location(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public int compareTo(Location o) {
            return getDistance(o, new Location(endX, endY));
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Location location = (Location) o;
            return x == location.x && y == location.y;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y);
        }
    }
}
