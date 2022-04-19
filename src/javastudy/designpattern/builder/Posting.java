package javastudy.designpattern.builder;

/**
 * builder pattern 에 대해 알아보기 위한 test 용 객체 입니다.
 */
public class Posting {
	private String title;
	private String subtitle;
	private String content;
	private String category;
	private String author;

	public Posting() {}

	public Posting(String title, String subtitle, String content, String category, String author) {
		this.title = title;
		this.subtitle = subtitle;
		this.content = content;
		this.category = category;
		this.author = author;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		this.subtitle = subtitle;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	@Override
	public String toString() {
		return "Posting{" +
				"title='" + title + '\'' +
				", subtitle='" + subtitle + '\'' +
				", content='" + content + '\'' +
				", category='" + category + '\'' +
				", author='" + author + '\'' +
				'}';
	}
}
