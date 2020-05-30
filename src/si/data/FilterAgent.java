package si.data;

import jade.core.AID;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.introspection.ACLMessage;
import twitter4j.*;
import twitter4j.conf.ConfigurationBuilder;

import java.util.Scanner;


public class FilterAgent extends AgentBase{
	private static final long serialVersionUID = 1L;

	public static final String NICKNAME = "Filter";

	protected void setup(){
		super.setup();
		this.type = AgentModel.FILTER;
		addBehaviour(new Filter());
		registerAgentDF();
	}
	private class Filter extends CyclicBehaviour {
		Scanner myObj = new Scanner(System.in);

		@Override
		public void action() {

			String [] arguments = new String[2]; 
			System.out.println("Enter word, username or hashtag: "); 
			arguments[0] = myObj.nextLine(); 
			System.out.println("Enter number of tweets (< 15): "); 
			arguments[1] = myObj.nextLine(); 
			while(Integer.parseInt(arguments[1]) > 15) {
				System.out.println("Enter number of tweets (< 15): "); arguments[1] = myObj.nextLine();
			}
			ACLMessage finish = new ACLMessage(ACLMessage.REQUEST);
			finish.setSender(getAID());
			AID id = new AID("Search@192.168.56.1:1200/JADE", AID.ISGUID);
			finish.addReceiver(id);
			String toSend = arguments[0].toString() + "_" + arguments[1].toString(); finish.setContent(toSend);
			send(finish);
			block();
		}
	}
}
public class SearchAgent extends AgentBase{
	private static final long serialVersionUID = 1L;
	public static final String NICKNAME = "Search"; static String hashTag;
	static long sinceId = 0;
	static int numberOfTweets = 0;
	protected void setup(){
		super.setup();
		this.type = AgentModel.SEARCH;
		addBehaviour(new Search());
		registerAgentDF();
	}

	private class Search extends CyclicBehaviour{ 
		@Override
		public void action(){
			ACLMessage input = receive();
			if(input!=null) {
				String [] arguments = input.getContent().split("_");
				hashTag = arguments[0];
				numberOfTweets = Integer.parseInt(arguments[1]);
				String send = TweetRecopiler(hashTag, numberOfTweets).toString(); 
				ACLMessage message = new ACLMessage(ACLMessage.REQUEST); 
				message.setSender(getAID());
				AID id = new AID("Analyzer@192.168.56.1:1200/JADE", AID.ISGUID); 
				message.addReceiver(id);
				message.setContent(send);
				send(message);
			}
			// block().
		}
	}

	public JsonObject TweetRecopiler(String hashtag, int numOfTweets){
		ConfigurationBuilder cb = new ConfigurationBuilder();

		cb.setDebugEnabled(true)
				.setOAuthConsumerKey("hfj2TCftZYWf3i2HIqboUHTEc")
				.setOAuthConsumerSecret("JNYmd7ZqYqmMgfbOSF12Z3L6zbvDNhFWtQbu6v6STMLU5n58hy")
				.setOAuthAccessToken("1142116313885753346-hl7jINmn8eTQKqP8wcot2EwdBVEx4F")
				.setOAuthAccessTokenSecret("0e2EStjQfXOKUJyBAFqVbcLP30dKmRxXzF2KQoUlrmBel");

		TwitterFactory tf = new TwitterFactory(cb.build());
		Twitter twitter = tf.getInstance();

		Query queryMax = new Query(hashTag);
		JsonObject output = getTweets(queryMax, twitter, numOfTweets);
		return output;
	}

	private JsonObject getTweets(Query query, Twitter twitter, int numberOfTweets) {
		int forCount = 0;TwitterSearch

		JsonObject output = new JsonObject();
		JsonArray arrayOfTweets = new JsonArray();
		try {
			QueryResult result = twitter.search(query);
			System.out.println("***********************************************");
			for (forCount=0; forCount < numberOfTweets || result.getTweets().get(forCount) == null; forCount++) {
				JsonObject tweets = new JsonObject();
				Status status = result.getTweets().get(forCount);
				tweets.addProperty("Id", status.getId());
				tweets.addProperty("Nickname", status.getUser().getScreenName());
				tweets.addProperty("Name", status.getUser().getName());
				tweets.addProperty("Text", status.getText());
				arrayOfTweets.add(tweets);
			}

		}catch (TwitterException te) {
			System.out.println("Couldn't connect: " + te);
			System.exit(-1);

		}catch (Exception e) {
			System.out.println("Something went wrong: " + e);
			System.exit(-1);
		}
		output.add("Tweets", arrayOfTweets);
		return output;
	}
}
i