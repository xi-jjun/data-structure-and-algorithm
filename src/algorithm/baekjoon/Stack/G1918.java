package Stack;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Stack;

// baekJoon 1918 Gold3 후위 표기식
// https://woongsios.tistory.com/288 살짝 참고
public class G1918 {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String line = br.readLine();
        StringBuilder sb = new StringBuilder();
        Stack<String> stack = new Stack<>();

        for (String l : line.split("")) {
            if (l.charAt(0) >= 65 && l.charAt(0) <= 90) sb.append(l); // 문자가 올 경우 그대로 출력 A=65...Z=90
            else {
                if (l.equals("(")) stack.push(l);
                else if (l.equals(")")) {
                    while (!stack.peek().equals("(")) {
                        sb.append(stack.pop());
                        if(stack.isEmpty()) break;
                    }
                    stack.pop();
                } else if (l.equals("*") || l.equals("/")) {
                    if (stack.isEmpty()) stack.push(l);
                    else if (stack.peek().equals("(") || stack.peek().equals("+") || stack.peek().equals("-"))
                        stack.push(l);
                    else {
                        while (!stack.peek().equals("+") && !stack.peek().equals("-") && !stack.peek().equals("(")) {
                            sb.append(stack.pop());
                            if (stack.isEmpty()) break;
                        }
                        stack.push(l);
                    }
                } else if (l.equals("+") || l.equals("-")) {
                    if (stack.isEmpty()) stack.push(l);
                    else if (stack.peek().equals("(") || stack.peek().equals("+") || stack.peek().equals("-")) {
                        while (!stack.peek().equals("*") && !stack.peek().equals("/") && !stack.peek().equals("(")) {
                            sb.append(stack.pop());
                            if (stack.isEmpty()) break;
                        }
                        stack.push(l);
                    } else {
                        while (!stack.peek().equals("(")) {
                            sb.append(stack.pop());
                            if(stack.isEmpty()) break;
                        }
                        stack.push(l);
                    }

                }
            }
        }

        while (!stack.isEmpty()) sb.append(stack.pop());

        System.out.println(sb);
    }
}