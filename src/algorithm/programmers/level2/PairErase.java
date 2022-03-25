package algorithm.programmers.level2;

import java.util.Stack;

public class PairErase {
	public static void main(String[] args) {
		String s = "baabaa";
		int answer = solution(s);
		System.out.println("answer = " + answer);
	}

	public static int solution(String s) {
		Stack<Character> stack = new Stack<>();
		for (Character c : s.toCharArray()) {
			if (!stack.isEmpty() && stack.peek() == c) stack.pop();
			else stack.push(c);
		}
		return stack.isEmpty() ? 1 : 0;
	}
}
