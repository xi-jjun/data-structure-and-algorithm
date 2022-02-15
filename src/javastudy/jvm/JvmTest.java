// package javastudy.jvm;

/**
 * IntelliJ 에서 실행하는 파일이 아닙니다.
 * 작성 툴 : vi
 * 작성 목적 : JVM decompiler javap 명령어 테스트
 */

public class JvmTest {
	public static void main(String[] args) {
		int num1 = 100;
		int num2 = 60;

		int result = addMethod(num1, num2);

		System.out.println("result : " + result);
	}

	static int addMethod(int a, int b) {
		return a + b;
	}
}
