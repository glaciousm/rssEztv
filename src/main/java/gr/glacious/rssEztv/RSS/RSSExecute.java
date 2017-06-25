package gr.glacious.rssEztv.RSS;

import java.util.ArrayList;
import java.util.List;

import gr.glacious.rssEztv.model.Feed;
import gr.glacious.rssEztv.model.FeedMessage;
import gr.glacious.rssEztv.read.RSSFeedParser;

public class RSSExecute {
	
	public List<FeedMessage> getRSS(){
		List<FeedMessage> rss = new ArrayList<>();
		
		RSSFeedParser parser = new RSSFeedParser(
                "https://eztv.ag/ezrss.xml");
        Feed feed = parser.readFeed();
        for (FeedMessage message : feed.getMessages()) {
            rss.add(message);

        }
        
        return rss;
	}

}
