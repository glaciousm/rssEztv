package gr.glacious.rssEztv.model;

import java.util.ArrayList;
import java.util.List;

public class Feed {

	final String title;
	final String link;
	final String description;
	final String lastBuildDate;

	final List<FeedMessage> entries = new ArrayList<FeedMessage>();

	public Feed(String title, String link, String description, String pubDate) {
		this.title = title;
		this.link = link;
		this.description = description;
		this.lastBuildDate = pubDate;
	}

	public List<FeedMessage> getMessages() {
		return entries;
	}

	public String getTitle() {
		return title;
	}

	public String getLink() {
		return link;
	}

	public String getDescription() {
		return description;
	}

	public String getPubDate() {
		return lastBuildDate;
	}

	@Override
	public String toString() {
		return "Feed [description=" + description + ", link=" + link + ", lastBuildDate=" + lastBuildDate + ", title=" + title
				+ "]";
	}

}