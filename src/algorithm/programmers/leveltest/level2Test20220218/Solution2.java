package algorithm.programmers.level2Test20220218;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 실패. 문제 풀이 실패.
 * 원인 : 좀 더 각 잡고 풀었어야 했다. 수업 중에 갑자기 할게 아니라
 */
public class Solution2 {
	public static void main(String[] args) {

	}

	public int[] solution(int[] fees, String[] records) {
		int[] answer = {};
		int basicTime = fees[0];
		int basicFee = fees[1];
		int unitTime = fees[2];
		int unitFee = fees[3];
		Map<String, Integer> inOutRecord = new HashMap<>(); // carNumber, inOrOutTime:누적된 시간
		Map<String, Integer> accTime = new HashMap<>();

		for (String record : records) {
			String[] recordInfo = record.split(" ");
			String[] time = recordInfo[0].split(":");
			int hour = Integer.parseInt(time[0]);
			int min = Integer.parseInt(time[1]);
			String carNumber = recordInfo[1];
			String inOut = recordInfo[2];

			if (inOutRecord.containsKey(carNumber)) {
				if (accTime.containsKey(carNumber)) {
					accTime.put(carNumber, accTime.get(carNumber) + 60 * hour + min - inOutRecord.get(carNumber));
				} else {
					accTime.put(carNumber, 60 * hour + min - inOutRecord.get(carNumber));
				}
				inOutRecord.remove(carNumber);
			} else {
				inOutRecord.put(carNumber, 60 * hour + min);
			}
		}
		Iterator<String> inOutRecordIter = inOutRecord.keySet().iterator();
		while (inOutRecordIter.hasNext()) {
			String remainCarNumber = inOutRecordIter.next();
			accTime.put(remainCarNumber, 23 * 60 + 59 - inOutRecord.get(remainCarNumber));
		}

		answer = new int[accTime.size()];
		int index = 0;
		for (String accCarNumber : accTime.keySet()) {
			Integer totalParkingTime = accTime.get(accCarNumber);
			if (totalParkingTime > basicTime) {
				int additionalFee = (double) (totalParkingTime - basicTime) / unitFee == 0 ? (totalParkingTime - basicTime) / unitTime : (totalParkingTime - basicTime) / unitTime + 1;
				answer[index++] = basicFee + additionalFee * unitFee;
			} else {
				answer[index++] = basicFee;
			}
		}

		return answer;
	}
}
