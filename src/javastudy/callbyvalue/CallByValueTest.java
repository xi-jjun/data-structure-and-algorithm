package javastudy.callbyvalue;

public class CallByValueTest {
	public static void main(String[] args) {
		/**
		 * #1 : foo 생성과 초기 상태
		 * foo 값 : foo 객체의 내용이 실제로 존재하는 "Heap space 의 주소"
		 * foo 주소 : 대충 여기서 구체적인 설명을 위해 0x1000 이라고 가정하자.
		 * foo 객체의 내용이 실제로 존재하는 Heap space 의 주소 : 0x3000 이라고 가정하자.
		 */
		Foo foo = new Foo(10, "Hello");

		System.out.println("=== Init Foo ===");
		System.out.println(foo);

		/**
		 * #2 : foo 객체 processing
		 * foo 라는 객체를 process 라는 함수에서 호출하여 접근하려고 한다.
		 */
		process(foo);
		System.out.println();

		System.out.println("=== After processing Foo ===");
		System.out.println(foo);
	}

	private static void process(Foo paramFoo) {
		/**
		 * #2-1 :
		 * main 에서 foo 라는 객체가 현재 이 process 라는 함수에 의해 호출되어
		 * paramFoo 라는 parameter 로 들어왔다.
		 * paramFoo 값 : foo 의 값(0x3000)
		 * paramFoo 주소 : 구체적으로 설명하기 위해 대충 0x1200 이라고 하자.
		 */

		/**
		 * 아래는 paramFoo 가 가리키는 객체의 'value', 'name' 이라는 variable 에 접근하는 것이다.
		 * 말했다시피 객체의 변수에 직접 접근했기에 원본을 변경한 것과 동일하다.
		 */
		paramFoo.value = 4321;
		paramFoo.name = "Bye";

		/**
		 * 새롭게 생성된 객체를 new Foo 라고 하자. 해당 객체를 paramFoo 에 넣어주겠다.
		 * new Foo 값 : 새롭게 생성된 객체의 주소
		 * new Foo 주소 : == paramFoo 의 주소 (0x1200)
		 */
		paramFoo = new Foo(99999, "Changed!");
	}

	static class Foo {
		int value;
		String name;

		public Foo(int value, String name) {
			this.value = value;
			this.name = name;
		}

		@Override
		public String toString() {
			return "Foo{" +
					"value=" + value +
					", name='" + name + '\'' +
					'}';
		}
	}
}
