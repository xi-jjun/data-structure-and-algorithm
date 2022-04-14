package javastudy.callbyvalue.array.primitive;

public class IntArray {
	public static void main(String[] args) {
		int data = 1;
		int[] original = new int[3];
		for (int i = 0; i < 3; i++) {
			original[i] = data++;
		}
		System.out.println("=== original array ===");
		showArray(original);

		int[] copy = original;
		System.out.println("=== copy array ===");
		showArray(copy);

		copy[0] = -10000;
		System.out.println("===== after modifying copy array =====");
		System.out.println("=== original array ===");
		showArray(original);
		System.out.println("=== copy array ===");
		showArray(copy);
/*
		=== original array ===
		1 2 3
		=== copy array ===
		1 2 3
		===== after modifying copy array =====
		=== original array ===
		-10000 2 3
		=== copy array ===
		-10000 2 3
 */
	}

	private static void showArray(int[] arr) {
		for (int data : arr) {
			System.out.print(data + " ");
		}
		System.out.println();
	}
}
