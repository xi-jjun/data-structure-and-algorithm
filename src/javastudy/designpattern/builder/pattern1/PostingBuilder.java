package javastudy.designpattern.builder.pattern1;

import javastudy.designpattern.builder.Posting;

public interface PostingBuilder {
	PostingBuilder title(String title);

	PostingBuilder subtitle(String subtitle);

	PostingBuilder content(String content);

	PostingBuilder category(String category);

	PostingBuilder author(String author);

	Posting getPosting();
}
