package javastudy.collectionframework.queueInterface;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class QueueTest {
	public static void main(String[] args) {
		Queue<Integer> queue = new ArrayDeque<>();
		queue.offer(1);
		queue.add(2);
		System.out.println("queue = " + queue);

		Queue<Integer> unboundedQueue = new ConcurrentLinkedQueue<>();


		/**
		 * capacity 를 정해줘야 함. 기본값 : Integer.MAX_VALUE
		 */
		Queue<Integer> boundedQueue = new LinkedBlockingQueue<>();


		System.out.println("=== Queue offer(null) Test ===");
		Queue<Object> q = new ArrayDeque<>(new ArrayList<>(Arrays.asList(1, 4, 2, 6, -1, 0, 3)));
		System.out.println("q = " + q);
//		q.offer(null); // 오류 발생 원인
		System.out.println("q = " + q);
		/**
		 * Exception in thread "main" java.lang.NullPointerException
		 * 	at java.base/java.util.ArrayDeque.addLast(ArrayDeque.java:304)
		 * 	at java.base/java.util.ArrayDeque.offerLast(ArrayDeque.java:353)
		 * 	at java.base/java.util.ArrayDeque.offer(ArrayDeque.java:509)
		 * 	at javastudy.collectionframework.queueInterface.QueueTest.main(QueueTest.java:29)
		 */


		System.out.println("=== LinkedQueue offer(null) Test ===");
		Queue<Object> linkedListQueue = new LinkedList<>(new ArrayList<>(Arrays.asList(1, 4, 2, 6, -1, 0, 3)));
		System.out.println("linkedListQueue = " + linkedListQueue);
		linkedListQueue.offer(null); // 추가된다!
		System.out.println("linkedListQueue = " + linkedListQueue);
		/**
		 * Queue implementations generally do not allow insertion of null elements.
		 * The LinkedList implementation, which was retrofitted to implement Queue, is an exception.
		 * For historical reasons, it permits null elements, but you should refrain from taking advantage of this,
		 * because null is used as a special return value by the poll and peek methods.
		 *
		 * === LinkedQueue offer(null) Test ===
		 * linkedListQueue = [1, 4, 2, 6, -1, 0, 3]
		 * linkedListQueue = [1, 4, 2, 6, -1, 0, 3, null]
		 */
	}
}
