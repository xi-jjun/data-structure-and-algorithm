package javastudy.designpattern.builder;

public class NormalApp {
	public static void main(String[] args) {
		/**
		 * 나는 이 때까지 이렇게 했었고, 이렇게 하는것만 있는 줄 알았다...
		 * 이렇게 하면 안 좋은 점
		 * 1. 장황해진다. 딱 봐도 코드가 field 의 개수에 따라서 더 늘어나게 된다.
		 * 2. 추가하고 싶은 부분만 추가하고 싶어도 method 가 많이 만들어져야 하거나,
		 * 		매번 set method 를 필요한 것만 쓸려면 실수를 하게 될 수 도 있다. → 불완전하게 만들어질 수 있다.
		 * 3. 그렇다고 생성자를 사용한다면...?
		 * 		1) 2번과 같은 문제 발생. 생성자가 많아져야하는 경우가 생길 수 있다.
		 * 		2) 1번과 같은 문제 발생. 생성자에 어떤 파라미터가 어디에 들어가야 하는지 알기 힘들다. 장황해진. 따라서 가독성이 떨어진다.
		 */
		Posting posting = new Posting();
		posting.setTitle("this is title");
		posting.setSubtitle("this is subtitle");
		posting.setContent("안녕하세요. 이것은 본문에 관한 field 라고 생각하시면 됩니다.");
		posting.setCategory("자유게시판");
		posting.setAuthor("this is me!!");

		System.out.println("posting : " + posting);
	}
}
