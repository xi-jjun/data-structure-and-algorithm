package test.passby;

public class Test2 {
	static class Foo{
		private int value;

		public Foo(int value) {
			this.value = value;
		}

		public int getValue() {
			return value;
		}

		public void setValue(int value) {
			this.value = value;
		}
	}

	public static void main(String[] args) {
		Foo foo = new Foo(10);
		System.out.println("First foo value = " + foo.getValue());
		process(foo);
		System.out.println("Last foo value = " + foo.getValue());

	}

	private static void process(Foo paramFoo) {
		paramFoo.setValue(10000);

		/**
		 * Java 는 call by value 방식이기 때문에 Parameter 로 들어오는 객체는 모두
		 * 원본 객체의 복사된 주소값을 가리키고 있다.
		 * 따라서!! 아래의 상황을 가정해보자.
		 * 원본 foo 객체 주소 : 0x1111
		 * 원본 foo 객체 값 : foo object
		 * foo 변수의 값 : 0x1111
		 * foo 변수의 주소 : 0x1234
		 *
		 * process 함수의 Stack...
		 * 현재 process 라는 함수로 전달된 paramFoo 는
		 * paramFoo 주소 : 0x5555
		 * paramFoo 값 : 0x1111(원본 객체 주소)
		 * ... Stack!!
		 *
		 * 따라서 paramFoo 를 아래와 같이
		 * paramFoo = new Foo(9999);
		 * 로 바꿔도, 새롭게 생긴 new Foo(9999) 에 대한 주소를 자기자신의 값으로 바꿀 뿐이다.
		 * 구체적으로 말하자면, new Foo(9999) 주소가 0x9999라고 한다면,
		 * paramFoo 의 값 : 0x9999 로 변경!!
		 * → 결국 원래 foo 객체는 여전히 0x1111에 잘 있고, 영향을 받지 않았다.
		 * !!!! 결론 !!!!
		 * 따라서 Java 는 Pass by value 라는 것!!!
		 */
		paramFoo = new Foo(9999);
	}
}
