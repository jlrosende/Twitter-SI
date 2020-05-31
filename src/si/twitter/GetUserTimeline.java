package si.twitter;

import twitter4j.*;
import java.util.List;

public class GetUserTimeline {

    public static List<Status> getTimeLine(String username) throws TwitterException {
        // gets Twitter instance with default credentials
        Paging pg = new Paging(1, 2);
        Twitter twitter = new TwitterFactory().getInstance();
        return twitter.getUserTimeline(username, pg);
        //user = twitter.verifyCredentials().getScreenName();
        //statuses = twitter.getUserTimeline();
    }
}