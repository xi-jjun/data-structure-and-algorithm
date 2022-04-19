package javastudy.designpattern.builder.pattern1;

import javastudy.designpattern.builder.Posting;

public class FirstBuilderApp {
	public static void main(String[] args) {
		PostingBuilder postingBuilder = new DefaultPostingBuilder();

		/**
		 * NormalApp 에서 테스트 했을 때 보다 코드가 더 보기 편해졌다.
		 * 만약에 이러한 형식으로 입력이 많이 들어온다면, 이 형식 set 을 Director 에 넣어놓고
		 * 사용하면 된다.
		 */
		Posting posting = postingBuilder.title("이것은 제목")
				.subtitle("이것은 부제목")
				.content("이것은 내용")
				.category("카테고리 종류")
				.author("저자").getPosting();

		System.out.println("posting = " + posting);


		/**
		 * Director 를 사용하여 정의하여 사용해보자
		 */
		FirstDirector director = new FirstDirector(new DefaultPostingBuilder());
		Posting blankPosting = director.blankPosting();
		Posting example = director.exampleSetPosting();
		System.out.println("blankPosting = " + blankPosting);
		System.out.println("example = " + example);
	}
}
