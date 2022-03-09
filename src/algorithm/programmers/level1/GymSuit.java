package algorithm.programmers.level1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// programmers 코딩테스트 연습 => 탐욕법(Greedy) => 체육복
public class GymSuit {
	public static void main(String[] args) {
		int n = 3;
		int[] lost = {1, 2};
		int[] reserve = {2, 3};
//		int[] lost = new int[]{3, 5, 6, 7, 9};
//		int[] reserve = new int[]{2, 5, 8};

		int answer = solution(n, lost, reserve);
		System.out.println("answer = " + answer);
	}

	public static int solution(int n, int[] lost, int[] reserve) {
		boolean[] isBorrowed = new boolean[n + 1];

		List<Integer> sameList = getAsList(lost);
		List<Integer> reserves = getAsList(reserve);
		sameList.retainAll(reserves);

		List<Integer> lostNumbers = removeDup(lost, sameList);
		List<Integer> reserveNumbers = removeDup(reserve, sameList);

		int answer = n - lostNumbers.size();
		for (int lostNumber : lostNumbers) {
			if (find(lostNumber, reserveNumbers, isBorrowed, n)) answer++;
			else if (find(lostNumber - 1, reserveNumbers, isBorrowed, n)) answer++;
			else if (find(lostNumber + 1, reserveNumbers, isBorrowed, n)) answer++;
		}

		return answer;
	}

	public static boolean find(int target, List<Integer> arr, boolean[] isBorrowed, int n) {
		if (target == 0) return false;
		else if (target > n) return false;
		for (int i = 0; i < arr.size(); i++) {
			if (target == arr.get(i) && !isBorrowed[i]) {
				isBorrowed[i] = true;
				return true;
			}
		}
		return false;
	}

	public static List<Integer> removeDup(int[] arr, List<Integer> dup) {
		List<Integer> newList = new ArrayList<>();
		for (int data : arr) {
			if (!dup.contains(data)) newList.add(data);
		}
		Collections.sort(newList);
		return newList;
	}


	private static List<Integer> getAsList(int[] arr) {
		List<Integer> list = new ArrayList<>();
		for (int l : arr) {
			list.add(l);
		}
		Collections.sort(list);
		return list;
	}
}
