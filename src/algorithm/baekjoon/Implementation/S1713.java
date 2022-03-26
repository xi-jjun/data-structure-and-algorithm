package algorithm.baekjoon.Implementation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

// baekJoon 1713 silver2 후보 추천하기

/**
 * 생각지도 못했던 PriorityQueue 에 관한 것을 알게된 문제.
 * pq 의 iter 도중에 객체의 값을 바꾼다고해서 내부적으로 재정렬을 하는 것이 아니다.
 * 따라서 모두 꺼낸 다음에 새로운 pq 에 삽입을 할 때 재정렬을 하게 되는 것이다.
 */
public class S1713 {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

		int N = Integer.parseInt(br.readLine());
		int K = Integer.parseInt(br.readLine());
		int[] recommends = new int[K];
		StringTokenizer st = new StringTokenizer(br.readLine());
		for (int i = 0; i < K; i++) {
			recommends[i] = Integer.parseInt(st.nextToken());
		}

		int time = 0;
		boolean[] isVisited = new boolean[101];  // 학생 번호는 1~100번까지
		PriorityQueue<Photo> pq = new PriorityQueue<>();
		for (int recommend : recommends) {
			if (!isVisited[recommend]) {
				if (N - pq.size() == 0) {
					Photo deletedPhoto = pq.poll();
					isVisited[deletedPhoto.studentNumber] = false;
				}
				isVisited[recommend] = true;
				pq.offer(new Photo(recommend, 0, time));
			} else { // 이미 게시된 학생이라면, like++
				pq = plusLike(recommend, pq);
			}

			time++;
		}

		List<Integer> uploadedStudentList = new ArrayList<>();
		for (Photo photo : pq) {
			uploadedStudentList.add(photo.studentNumber);
		}

		Collections.sort(uploadedStudentList);

		for (Integer student : uploadedStudentList) {
			bw.write(student + " ");
		}

		bw.flush();
		bw.close();
	}

	private static PriorityQueue<Photo> plusLike(int studentNumber, PriorityQueue<Photo> pq) {
		PriorityQueue<Photo> newPq = new PriorityQueue<>();
		for (Photo photo : pq) {
			if (photo.studentNumber == studentNumber) {
				photo.like++;
			}
			newPq.offer(photo);
		}

		return newPq;
	}

	static class Photo implements Comparable<Photo> {
		int studentNumber;
		int like;
		int created;

		public Photo(int studentNumber, int like, int created) {
			this.studentNumber = studentNumber;
			this.like = like;
			this.created = created;
		}

		@Override
		public int compareTo(Photo photo) {
			if (this.like == photo.like) return this.created - photo.created;
			return this.like - photo.like;
		}
	}
}
