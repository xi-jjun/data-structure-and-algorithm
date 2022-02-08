package Queue;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.StringTokenizer;

// baekJoon 5464 siler2 주차장-스터디(Queue)
// 구현 순한맛
public class S5464 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken()); // space
        int M = Integer.parseInt(st.nextToken()); // cars
        int carCount = 0;
        int totalFee = 0;
        int[] Rs = new int[N + 1]; // fee
        int[] Wk = new int[M + 1]; // weight
        boolean[] used = new boolean[N + 1]; // false:empty, true:now used
        Car[] isUsedCars = new Car[M + 1];
        Queue<Car> waitQueue = new ArrayDeque<>();

        for (int i = 1; i < N + 1; i++) Rs[i] = Integer.parseInt(br.readLine());
        for (int i = 1; i < M + 1; i++) Wk[i] = Integer.parseInt(br.readLine());

        for (int i = 0; i < 2 * M; i++) {
            int carNum = Integer.parseInt(br.readLine());

            if (carNum < 0) { // 주차장에서 나갈 때
                int carN = Math.abs(carNum);
                Car out = isUsedCars[carN];
                isUsedCars[out.carNum] = null;
                used[out.spaceNum] = false;
                carCount--;

                if(!waitQueue.isEmpty()) {
                    Car in = waitQueue.poll();

                    for (int k = 1; k < N + 1; k++) {
                        if (!used[k]) {
                            used[k] = true;
                            isUsedCars[in.carNum] = in;
                            in.spaceNum = k;
                            totalFee += Wk[in.carNum] * Rs[k];
                            break;
                        }
                    }
                    carCount++;
                }
            } else { // 주차장에 들어오려고 할 때
                Car in = new Car(Wk[carNum], carNum);

                if (carCount < N) {
                    for (int k = 1; k < N + 1; k++) {
                        if (!used[k]) {
                            used[k] = true;
                            isUsedCars[carNum] = in;
                            in.spaceNum = k;
                            totalFee += Wk[in.carNum] * Rs[k];
                            break;
                        }
                    }
                    carCount++;
                } else waitQueue.offer(new Car(Wk[carNum], carNum));
            }
        }
        System.out.println(totalFee);
    }

    static class Car {
        private int weight;
        private int carNum;
        public int spaceNum;

        public Car(int weight, int carNum) {
            this.weight = weight;
            this.carNum = carNum;
        }
    }
}
