package BackTracking;

import java.util.*;

public class Temp {
    static final int[] dx = {0, 1};
    static final int[] dy = {1, 0};
    static final int[][] expense = {
            {1, 2, 1, 3},
            {2, 1, 4, 2},
            {3, 1, 2, 3},
            {2, 3, 2, 1},
    };
    static int N, M; // NxM 크기 배열
    static boolean[][] visited = new boolean[4][4]; // 방문처리를 위한 배열
    static int[] cost = new int[6]; // 각 노드의 숙박비용의 정보를 담기위한 배열
    static List<Integer> COSTS = new ArrayList<>(); // 해당 경로에 대한 최소 숙박비용을 저장하는 리스트

    static void backTracking(int startX, int startY, int depth) {
        if (depth == 6) { // 도착지점(3,3) 이전 노드까지 왔을 때
            int sum = 0; // 합을 구하기 위한 변수
            for (int i = 0; i < 6; i++) { // 시작 노드부터 도착 노드 전까지의 숙박비용 합을 구한다.
                System.out.print(cost[i] + " ");
                sum += cost[i];
            }
            COSTS.add(sum); // 구한 합을 COSTS 라는 리스트에 저장.
            System.out.println("");
            return;
        }

        cost[depth] = expense[startX][startY]; // 현재 노드의 비용을 cost 배열에 저장.

        List<Point> fee = new ArrayList<>(); // 현재 노드로 부터 오른쪽, 아래 2군데 탐색 가능. 2군데에 대한 숙박비용 비교위한 리스트.
        int nextX, nextY; // 다음 노드의 좌표

        for (int i = 0; i < 2; i++) {
            nextX = startX + dx[i]; // 다음 x 좌표
            nextY = startY + dy[i]; // 다음 y 좌표

            if (isValid(nextX, nextY)) { // 다음 좌표가 유효하다면,
                fee.add(new Point(nextX, nextY, expense[nextX][nextY])); // 요금 리스트에 추가
            }
        }

        if (fee.size() == 1) { // 요금 리스트에 1개의 요소밖에 없을 때-> 해당 요소로 가면 된다.
            Point next = fee.get(0);
            visited[next.x][next.y] = true; // 다음으로 갈 노드 방문처리
            backTracking(next.x, next.y, depth + 1); // 다음 노드부터 재귀적 탐색. depth++
            visited[next.x][next.y] = false; // 재귀적 탐색 끝났으니 방문처리 해제
        } else if (fee.size() == 2) { // 요금 리스트에 2개의 요소가 있을 때-> 요금의 비교 필요.
            Point p1 = fee.get(0);
            Point p2 = fee.get(1);

            if (p1.expense > p2.expense) { // p2 노드의 숙박비용이 더 적을 때, p2로 탐색하러 간다.
                visited[p2.x][p2.y] = true;
                backTracking(p2.x, p2.y, depth + 1);
                visited[p2.x][p2.y] = false;
            } else if (p1.expense < p2.expense) { // p1 노드의 숙박비용이 더 적을 때, p1로 탐색하러 간다.
                visited[p1.x][p1.y] = true;
                backTracking(p1.x, p1.y, depth + 1);
                visited[p1.x][p1.y] = false;
            } else { // p1, p2 노드의 숙박비용이 같을 때, 둘 다 각각 탐색을 해준다.
                visited[p2.x][p2.y] = true;
                backTracking(p2.x, p2.y, depth + 1);
                visited[p2.x][p2.y] = false;

                visited[p1.x][p1.y] = true;
                backTracking(p1.x, p1.y, depth + 1);
                visited[p1.x][p1.y] = false;
            }
        }
    }

    private static boolean isValid(int nextX, int nextY) { // 유효한지 확인. OutOfArrayBounded, Visited Check
        return !(nextX < 0 || nextX >= 4 || nextY < 0 || nextY >= 4) && !visited[nextX][nextY];
    }

    public static void main(String[] args) {
        N = 4;
        M = 4;

        backTracking(0, 0, 0);
        COSTS.sort((Comparator.comparingInt(o -> o))); // 구한 비용합의 최솟값 구하기 위한 정렬.
        System.out.println("ANSWER = " + COSTS.get(0)); // 최소숙박비용 합 출력
    }

    static class Point {
        private final int x, y, expense;

        public Point(int x, int y, int expense) {
            this.x = x;
            this.y = y;
            this.expense = expense;
        }
    }
}
