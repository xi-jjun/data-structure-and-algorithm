package javastudy.callbyvalue.array.primitive;

public class PrimitiveArray {
	public static void main(String[] args) {
		int seq = 0;
		int[][] array = new int[3][4];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				array[i][j] = ++seq;
			}
		}

		printArray(array);

		changeArray(array);

		printArray(array);
	}

	private static void changeArray(int[][] array) {
		int seq = 0;
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 4; j++) {
				array[i][j] = --seq;
			}
		}
	}

	private static void printArray(int[][] array) {
		for (int[] ints : array) {
			for (int anInt : ints) {
				System.out.print(anInt+" ");
			}
			System.out.println();
		}
	}
}
