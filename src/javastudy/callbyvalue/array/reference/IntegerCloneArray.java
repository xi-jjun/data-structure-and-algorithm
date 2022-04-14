package javastudy.callbyvalue.array.reference;

public class IntegerCloneArray {
	public static void main(String[] args) {
		Integer[] original = new Integer[3];
		original[0] = 1;
		original[1] = 2;
		original[2] = 3;

		System.out.println("=== original Integer array ===");
		showArray(original);

		Integer[] copy = original.clone(); // int[] 는 안되지만, Integer array 는 clone() method 의 사용이 가능하다.
		System.out.println("=== copy Integer array ===");
		showArray(copy);

		System.out.println("===== after modifying arr[0] in copy =====");
		copy[0] = 11000;

		System.out.println("=== original Integer array ===");
		showArray(original);
		System.out.println("=== copy Integer array ===");
		showArray(copy);
/*
		=== original Integer array ===
		1 2 3
		=== copy Integer array ===
		1 2 3
		===== after modifying arr[0] in copy =====
		=== original Integer array ===
		1 2 3
		=== copy Integer array ===
		11000 2 3
 */
	}

	private static void showArray(Integer[] arr) {
		for (int data : arr) {
			System.out.print(data + " ");
		}
		System.out.println();
	}
}
