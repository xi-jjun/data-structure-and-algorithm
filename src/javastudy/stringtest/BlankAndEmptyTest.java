package javastudy.stringtest;

public class BlankAndEmptyTest {
	public static void main(String[] args) {
		String str = "";
		System.out.println("'' is empty? => " + str.isEmpty()); // true
		System.out.println("'' is blank? => " + str.isBlank()); // true

		String str1 = "  ";
		System.out.println("' ' is empty? => " + str1.isEmpty()); // true
		System.out.println("' ' is blank? => " + str1.isBlank()); // true
	}
}
