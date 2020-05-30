package si.data;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;
import twitter4j.Query;
import twitter4j.QueryResult;
import twitter4j.Status;

import java.util.List;
import java.util.ArrayList;

public class TwitterClient {

	//Consumer Key
	static String consumerKeyStr = "xxxxx";
	static String consumerSecretStr = "xxxxx";

	//Access Token
	static String accessTokenStr = "xxxxx";
	static String accessTokenSecretStr = "xxxxx";


	//Metodo de busqueda que regresara todos los tweets relacionados a la busqueda de keywords
	public List searchForWord(String word, int limit){

		//List que contendra los tweets que arroje la busqueda
		List tweets = new ArrayList();

		try {

			//Inicializamos el objeto Twitter desde la clase Factory
			Twitter twitter = new TwitterFactory().getInstance();

			//Pasamos ConsumerKey and ConsumerSecret
			twitter.setOAuthConsumer(consumerKeyStr, consumerSecretStr);

			//Creamos el objeto AccessToken con los valores previamente inicializados
			AccessToken accessToken = new AccessToken(accessTokenStr,
					accessTokenSecretStr);

			//Establecer el objeto AccessToken al objeto Twitter 
			twitter.setOAuthAccessToken(accessToken);

			//Definir el objeto query. El parametro del contructor de QUery es la palabra a buscar dentro de Twitter
			Query query = new Query(word);

			//Definir el tamano del resultset. En este ejemplo, el tamano se ha establecido en 10,000 entradas
			query.setCount(10001);

			//Ejecutar el metodo search en el objeto twitter. Los resultados se pasan a un objeto QueryResult que contiene un objeto Status por cada Tweet.
			QueryResult result = twitter.search(query);

			//Agregamos los resultados al objeto List que regresaremos
			for(Status status: result.getTweets()){
				tweets.add(status.getText());
			}


		} catch (TwitterException te) {
			//Imprime cualquier error que pueda ser asociado con este codigo
			te.printStackTrace();
		}

		//Regresa una List con todos los tweets parte de la busqueda.
		return tweets;
	}


	public static void main(String[] args) {
		//Instanciando la clase actual a un objeto
		TwitterSearch search = new TwitterSearch();

		//Vista previa de los twwts encontrados hacia la consola principal
		System.out.println(search.searchForWord(args[0],Integer.parseInt(args[1])));

	}

}