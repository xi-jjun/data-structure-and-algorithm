package test.passby;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class PassByValueTest {
	static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static final BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	static void swap(int a, int b) throws IOException {
		bw.write("\nBefore executing in swap Function a = "+a);
		bw.write("\nBefore executing in swap Function b = "+b);

		a = a + b;
		b = a - b;
		a = a - b;

		bw.write("\n\nAfter executing in swap Function a = "+a);
		bw.write("\nAfter executing in swap Function b = "+b);
	}

	public static void main(String[] args) throws IOException {
		// pass by value test
		int a = 10;
		int b = 20;
		bw.write("first a = " + a);
		bw.write("\nfirst b = " + b);

		swap(a, b);

		/**
		 * a, b 가 swap 이후에도 원래의 값을 유지한다 → pass by value
		 *
		 * 실제로 Java Language Specification의 (Section 4.3) 에서는 원시 값이든 객체든 상관없이 모든 데이터를 Pass By Value로 전달한다고 나와 있다.
		 * 출처: https://mangkyu.tistory.com/107 [MangKyu's Diary]
		 * Java에서는 객체에 한해 확장된 규칙이 적용된다. 그것은 개체와 관련되어 복사 후 전달되는 값은 실제 메모리를 가리키는 Reference(참조값)인 포인터라는 것
		 * 출처: https://mangkyu.tistory.com/107 [MangKyu's Diary]
		 */
		bw.write("\n\nAfter came out from swap function a = " + a);
		bw.write("\nAfter came out from swap function b = " + b);

		bw.flush();
		bw.close();
	}
}
