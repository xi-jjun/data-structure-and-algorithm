package algorithm.programmers.level1;

public class BlindPhoneNumber {
	public static void main(String[] args) {
		String number = "01033334444";
		String answer = solution(number);
		System.out.println("answer = " + answer);
	}

	public static String solution(String phone_number) {
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < phone_number.length() - 4; i++) {
			sb.append("*");
		}
		sb.append(phone_number.substring(phone_number.length() - 4));
		return sb.toString();
	}
}
