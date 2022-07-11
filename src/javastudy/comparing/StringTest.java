package javastudy.comparing;

public class StringTest {
    public static void main(String[] args) {
        String a = "aab";
        String b = "aab";

        System.out.println(a == b); // true

        String aa = new String("aa");
        String bb = new String("aa");
        System.out.println(aa == bb); // false
    }
}
