package javastudy.callbyvalue.array.primitive;

import java.util.Arrays;

public class ArrayCopyOfTest {
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int[] newArr = Arrays.copyOfRange(arr, 1, 3);

        showArrays(arr, newArr);

        System.out.println("=== modify copy array ===");
        newArr[1] = 100;

        System.out.println("\n\n== result ==");
        showArrays(arr, newArr);
        /**
         * == original array ==
         * 1 2 3 4 5
         * == copy array ==
         * 2 3
         * === modify copy array ===
         *
         *
         * == result ==
         * == original array ==
         * 1 2 3 4 5
         * == copy array ==
         * 2 100
         */
    }

    private static void showArrays(int[] arr, int[] newArr) {
        System.out.println("== original array ==");
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();

        System.out.println("== copy array ==");
        for (int i : newArr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}
