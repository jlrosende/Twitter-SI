package twitter4j.json;

import twitter4j.*;

import java.io.*;
import java.util.List;

public final class GetRetweets {
    /**
     * @param args message
     */
    public static void main(String[] args) {
        if (args.length < 1) {
            System.out.println("Usage: java twitter4j.examples.tweets.GetRetweets [status id]");
            System.exit(-1);
        }
        System.out.println("Showing up to 100 of the first retweets of the status id - [" + args[0] + "].");
        try {
            Twitter twitter = new TwitterFactory().getInstance();
            List<Status> statuses = twitter.getRetweets(Long.parseLong(args[0]));
            for (Status status : statuses) {
                System.out.println("@" + status.getUser().getScreenName() + " - " + status.getText());
            }
            System.out.println("done.");
            System.exit(0);
        } catch (TwitterException te) {
            te.printStackTrace();
            System.out.println("Failed to get retweets: " + te.getMessage());
            System.exit(-1);
        }
    }
}