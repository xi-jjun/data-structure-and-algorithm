package algorithm.programmers.level1;

public class StrangeChar {
	public static void main(String[] args) {
		String str = " aaaa ";
		String answer = solution(str);
		System.out.println("answer = " + answer);
	}

	public static String solution(String str) {
		int index = 0;
		StringBuilder sb = new StringBuilder();
		for (String s : str.split("")) {
			if (index++ % 2 == 0 && !s.equals(" ")) sb.append(s.toUpperCase());
			else if (s.equals(" ")) {
				index = 0;
				sb.append(s);
			} else sb.append(s.toLowerCase());
		}
		return sb.toString();
	}
}
