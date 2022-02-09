package javastudy.callbyvalue;

public class CallByValueReferenceTypeTest {
	public static void main(String[] args) {
		int[][] arr = new int[3][4];
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				arr[i][j] = 100;
			}
		}

		System.out.println("=== Before Processing ===");
		showArray(arr);

		System.out.println();

		process(arr);
		System.out.println("=== After Processing ===");
		showArray(arr);
	}

	private static void process(int[][] paramArr) {
		for (int i = 0; i < paramArr.length; i++) {
			for (int j = 0; j < paramArr[i].length; j++) {
				paramArr[i][j] = 41;
			}
		}

		/**
		 * call by reference 라면 아래 코드로 인해 process
		 * 라는 method 가 끝나게된 후 arr 배열의 상태는 모든
		 * element 가 0이어야 한다.
		 * → 하지만 그렇지 않다.
		 */
		paramArr = new int[6][7];
	}

	private static void showArray(int[][] arr) {
		for (int i = 0; i < arr.length; i++) {
			for (int j = 0; j < arr[i].length; j++) {
				System.out.print(arr[i][j]+" ");
			}
			System.out.println();
		}
	}
}
