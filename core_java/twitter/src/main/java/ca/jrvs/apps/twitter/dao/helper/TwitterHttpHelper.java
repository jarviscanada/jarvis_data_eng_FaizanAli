package ca.jrvs.apps.twitter.dao.helper;

import java.io.IOException;
import java.net.URI;
import oauth.signpost.OAuthConsumer;
import oauth.signpost.commonshttp.CommonsHttpOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TwitterHttpHelper implements HttpHelper {

  private static final Logger logger = LoggerFactory.getLogger(TwitterHttpHelper.class);
  //dependencies
  private OAuthConsumer consumer;
  private HttpClient httpClient;

  /**
   * Constructor Setup dependencies using secrets
   *
   * @param consumerKey    (API Key) Client credential key
   * @param consumerSecret (API Key secret) Client credential secret
   * @param accessToken    User access token
   * @param tokenSecret    User access token secret
   */
  public TwitterHttpHelper(String consumerKey, String consumerSecret, String accessToken,
      String tokenSecret) {

    consumer = new CommonsHttpOAuthConsumer(consumerKey, consumerSecret);
    consumer.setTokenWithSecret(accessToken, tokenSecret);

    httpClient = HttpClientBuilder.create().build();
  }

  @Override
  public HttpResponse httpPost(URI uri) {

    try {
      HttpPost request = new HttpPost(uri);
      consumer.sign(request);

      return httpClient.execute(request);

    } catch (OAuthMessageSignerException e) {
      logger.error("Failed to Post", e);
      throw new RuntimeException();
    } catch (OAuthExpectationFailedException e) {
      logger.error("Failed to Post", e);
      throw new RuntimeException();
    } catch (IOException e) {
      logger.error("Failed to Post", e);
      throw new RuntimeException();
    } catch (OAuthCommunicationException e) {
      logger.error("Failed to Post", e);
      throw new RuntimeException();
    }
  }

  @Override
  public HttpResponse httpGet(URI uri) {


    try {
      HttpGet request = new HttpGet(uri);
      consumer.sign(request);

      return httpClient.execute(request);

    } catch (OAuthMessageSignerException e) {
      logger.error("Failed to Post", e);
      throw new RuntimeException();
    } catch (OAuthExpectationFailedException e) {
      logger.error("Failed to Post", e);
      throw new RuntimeException();
    } catch (OAuthCommunicationException e) {
      logger.error("Failed to Post", e);
      throw new RuntimeException();
    } catch (ClientProtocolException e) {
      logger.error("Failed to Post", e);
      throw new RuntimeException();
    } catch (IOException e) {
      logger.error("Failed to Post", e);
      throw new RuntimeException();
    }
  }
}
