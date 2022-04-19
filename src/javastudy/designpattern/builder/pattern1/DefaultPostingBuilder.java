package javastudy.designpattern.builder.pattern1;

import javastudy.designpattern.builder.Posting;

public class DefaultPostingBuilder implements PostingBuilder {
	private String title;
	private String subtitle;
	private String content;
	private String category;
	private String author;

	@Override
	public PostingBuilder title(String title) {
		this.title = title;
		return this;
	}

	@Override
	public PostingBuilder subtitle(String subtitle) {
		this.subtitle = subtitle;
		return this;
	}

	@Override
	public PostingBuilder content(String content) {
		this.content = content;
		return this;
	}

	@Override
	public PostingBuilder category(String category) {
		this.category = category;
		return this;
	}

	@Override
	public PostingBuilder author(String author) {
		this.author = author;
		return this;
	}

	@Override
	public Posting getPosting() {
		return new Posting(title, subtitle, content, category, author);
	}
}
