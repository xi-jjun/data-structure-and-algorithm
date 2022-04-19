package javastudy.designpattern.builder.pattern1;

import javastudy.designpattern.builder.Posting;

public class FirstDirector {
	private final PostingBuilder postingBuilder;

	public FirstDirector(PostingBuilder postingBuilder) {
		this.postingBuilder = postingBuilder;
	}

	public Posting blankPosting() {
		return postingBuilder.getPosting();
	}

	/**
	 * 이렇게 많이 쓰이는 형식이 있다면, 매번 Builder Pattern 에서 일일이 값을 넣어주는 것보다
	 * 같은 set 을 Director 에 정의하여 쓰는게 훨씬 더 좋을 수 있다.
	 *
	 * *여기서 쓴 예시 객체인 Posting 에서는 매번 달라지는 데이터이기 때문에 조금 적절하지 않을 수 있다*
	 * @return
	 */
	public Posting exampleSetPosting() {
		return postingBuilder.title("title")
				.subtitle("subtitle")
				.content("content")
				.category("c a t e g o r y")
				.author("author is you")
				.getPosting();
	}
}
