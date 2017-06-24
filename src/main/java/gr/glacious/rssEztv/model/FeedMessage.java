package gr.glacious.rssEztv.model;

public class FeedMessage {

	String title;
	String link;
	String guid;
	String pubDate;
	String magnetURI;
	String length;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public String getPubDate() {
		return pubDate;
	}

	public void setPubDate(String pubDate) {
		this.pubDate = pubDate;
	}

	public String getMagnetURI() {
		return magnetURI;
	}

	public void setMagnetURI(String magnetURI) {
		this.magnetURI = magnetURI;
	}

	public String getLength() {
		return length;
	}

	public void setLength(String length) {
		this.length = length;
	}

	@Override
	public String toString() {
		return "FeedMessage [title=" + title + ", link=" + link + ", guid=" + guid + ", pubDate=" + pubDate
				+ ", magnetURI=" + magnetURI + ", length=" + length + "]";
	}
	

}