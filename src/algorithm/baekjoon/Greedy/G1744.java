package Greedy;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.Stack;

// baekJoon 1744 Gold4 수 묶기
// 생각보다 고려해줘야할 경우의 수가 많아서 까다로웠다.
public class G1744 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine()); // <= 10000
        long result = 0L;

        int[] numbers = new int[N];

        if (N == 1) {
            System.out.println(Integer.parseInt(br.readLine()));
            return;
        }
        for (int i = 0; i < N; i++) numbers[i] = Integer.parseInt(br.readLine());

        Arrays.sort(numbers);
        Deque<Integer> deque = new ArrayDeque<>();
        Stack<Integer> stack = new Stack<>();

        for (int num : numbers) deque.add(num);

        while (deque.peekFirst() <= 0) {
            if (stack.size() < 2) {
                stack.push(deque.removeFirst());
            } else {
                int a = stack.pop();
                int b = stack.pop();
                result += (long) a * b;
            }// 5 5 4 1 1 1
            if (deque.isEmpty()) {
                int temp = 1;
                while (!stack.isEmpty()) temp *= stack.pop();
                result += temp;
                System.out.println(result);
                return;
            }
        }

        if (!stack.isEmpty()) {
            if (stack.size() == 1) result += stack.pop();
            else {
                int a = stack.pop();
                int b = stack.pop();
                if (a != 0 && b != 0) result += (long) a * b;
            }
        }

        if (deque.isEmpty()) return;

        while (deque.peekLast() > 0) {
            if (stack.size() < 2) stack.push(deque.removeLast());
            else {
                int a = stack.pop();
                int b = stack.pop();
                if (a * b < a + b) result += a + b;
                else result += (long) a * b;
            }
            if (deque.isEmpty()) {
                break;
            }
        }

        if (!stack.isEmpty()) {
            if (stack.size() == 1) result += stack.pop();
            else {
                int a = stack.pop();
                int b = stack.pop();
                if (a != 0 && b != 0) {
                    if (a * b < a + b) result += a + b;
                    else result += (long) a * b;
                } else result += a + b;
            }
        }

        System.out.println(result);
    }
}
