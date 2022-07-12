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

        Integer aa = 200; // -128~127 까지의 범위만 캐싱을 한다.
        Integer bb = 200;
        System.out.println("(aa==bb) = " + (aa == bb)); // false. 만약 aa 와 bb 가 100이었다면 true 가 나온다.

        System.out.println("aa.equals(bb) = " + aa.equals(bb)); // true
    }
}
