package javastudy.collectionframework.listInterface;

public class ArrayRunTimeCheckTest {
	/**
	 * Array 는 Run time 에 실제 객체의 type 을 확인 후 사용해야 하는 번거로움이 있다고 배웠다.
	 * 근데 왜...?
	 *
	 * reference : https://docs.oracle.com/javase/specs/jls/se8/html/jls-10.html
	 * example reference : https://courses.cs.washington.edu/courses/cse401/16wi/sections/section8/javatypes.html
	 */
	public static void main(String[] args) {
		String[] a = new String[1];
		Object[] b = a;

		/**
		 * It is rarely appropriate to use this constructor.
		 * The static factory valueOf(int) is generally a better choice,
		 * as it is likely to yield significantly better space and time performance.
		 * 위 선언을 적어서 IntelliJ 가 보여주는 문구를 그대로 복사했다. 차라리 저렇게 할거면 int 로 선언하라고 한다.
		 * better space : Stack 에 int type 선언하게 되면 Heap 영역 안먹으니깐
		 * better time performance : 따라서 GC 를 안해도 되니깐
		 * + 애초에 Integer 라는 생성자를 쓰는게 흔하지도 않고 적절하지도 않았다고 한다.
		 */
		b[0] = new Integer(123);


		/**
		 * 현재 코드는 JDK 에서 제공하는 javac 로 '컴파일은 오류없이 가능' 하다. 그 증거가 현재 directory 에 존재하는 .class file 이다.
		 * 그러나 실행을 시키게 되면 아래와 같은 에러를 던지는 것을 볼 수 있다.
		 */

		/**
		 * run 결과.
		 * Exception in thread "main" java.lang.ArrayStoreException: java.lang.Integer
		 * 	at javastudy.collectionframework.listInterface.ArrayRunTimeCheckTest.main(ArrayRunTimeCheckTest.java:14)
		 */

		/**
		 * 위와 같은 오류가 발생한 이유 - Java SE8 docs
		 * For an array whose type is A[], where A is a reference type,
		 * an assignment to a component of the array is checked at run time
		 * to ensure that the value being assigned is assignable to the component.
		 */
	}
}
