package ca.jrvs.apps.twitter.dao.helper;

import static org.junit.Assert.*;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TwitterHttpHelperTest {

  private static final Logger logger = LoggerFactory.getLogger(TwitterHttpHelperTest.class);

  private static String CONSUMER_KEY;
  private static String CONSUMER_SECRET;
  private static String ACCESS_TOKEN;
  private static String TOKEN_SECRET;

  @Before
  public void setUp() throws Exception {
    logger.info("Twitter Http Helper Unit Test");
    CONSUMER_KEY = System.getenv("consumerKey");
    CONSUMER_SECRET = System.getenv("consumerSecret");
    ACCESS_TOKEN = System.getenv("accessToken");
    TOKEN_SECRET = System.getenv("tokenSecret");
  }

  @Test
  public void httpPost() throws IOException, URISyntaxException {
    HttpHelper httpHelper = new TwitterHttpHelper(CONSUMER_KEY,CONSUMER_SECRET,ACCESS_TOKEN,TOKEN_SECRET);

    HttpResponse response = httpHelper.httpPost(new URI("https://api.twitter.com/1.1/statuses/update.json?status=HttpHelperTest"));

    logger.info("httpPost: " + EntityUtils.toString(response.getEntity()));

    assertEquals("200 Code for successful post should be returned",200,response.getStatusLine().getStatusCode());
  }

  @Test
  public void httpGet() throws IOException, URISyntaxException {
    HttpHelper httpHelper = new TwitterHttpHelper(CONSUMER_KEY,CONSUMER_SECRET,ACCESS_TOKEN,TOKEN_SECRET);

    HttpResponse response = httpHelper.httpGet(new URI("https://api.twitter.com/1.1/statuses/user_timeline.json?screen_name=FaizanAli9413"));

    logger.info("httpGet: " + EntityUtils.toString(response.getEntity()));

    assertEquals("200 Code for successfully getting user timeline should be returned",200,response.getStatusLine().getStatusCode());
  }
}