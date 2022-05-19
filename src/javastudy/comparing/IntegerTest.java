package javastudy.comparing;

/**
 * 얼마 전 면접에서 해당 내용에 대한 질문이 나와서 테스트 했다.
 */
public class IntegerTest {
    public static void main(String[] args) {
        Integer a = 1;
        Integer b = 1;
        Integer c = new Integer(1);
        System.out.println(a); // 1
        System.out.println(a == b); // true
        System.out.println(a == c); // false
    }
}
