package algorithm.programmers.level1;

public class PasswordCaesar {
	public static void main(String[] args) {
		String s = "aa";
		int n = 4;
		String answer = solution(s, n);
		System.out.println("answer = " + answer);
	}

	public static String solution(String s, int n) {
		StringBuilder sb = new StringBuilder();
		for (String ch : s.split("")) {
			char c = (char) (Character.isUpperCase(ch.charAt(0)) ? ch.charAt(0) - 65 : ch.charAt(0) - 97);
			if (ch.equals(" ")) {
				sb.append(" ");
			} else if (Character.isUpperCase(ch.charAt(0))) {
				c = (char) ((c + n) % 26 + 65);
				sb.append(c);
			} else {
				c = (char) ((c + n) % 26 + 97);
				sb.append(c);
			}
		}
		return sb.toString();
	}
}
