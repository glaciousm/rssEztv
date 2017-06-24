package gr.glacious.test;

import gr.glacious.rssEztv.model.Feed;
import gr.glacious.rssEztv.model.FeedMessage;
import gr.glacious.rssEztv.read.RSSFeedParser;

public class ReadTest {
    public static void main(String[] args) {
        RSSFeedParser parser = new RSSFeedParser(
                "https://eztv.ag/ezrss.xml");
        Feed feed = parser.readFeed();
        System.out.println(feed);
        for (FeedMessage message : feed.getMessages()) {
            System.out.println(message);

        }

    }
}