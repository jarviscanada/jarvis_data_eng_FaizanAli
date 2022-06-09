package ca.jrvs.apps.twitter.dao;

import static org.junit.Assert.*;

import ca.jrvs.apps.twitter.Model.Tweet;
import ca.jrvs.apps.twitter.Util.JsonUtil;
import ca.jrvs.apps.twitter.Util.TweetUtil;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TwitterDaoIntTest {

  private static final Logger logger = LoggerFactory.getLogger(TwitterDaoIntTest.class);
  private TwitterDao dao;

  @Before
  public void setUp(){
    logger.info("Twitter DAO Integration Test");

    String CONSUMER_KEY = System.getenv("consumerKey");
    String CONSUMER_SECRET = System.getenv("consumerSecret");
    String ACCESS_TOKEN = System.getenv("accessToken");
    String TOKEN_SECRET = System.getenv("tokenSecret");

    //setup dependency
    HttpHelper httpHelper = new TwitterHttpHelper(CONSUMER_KEY,CONSUMER_SECRET,ACCESS_TOKEN,TOKEN_SECRET);

    //pass dependency
    this.dao = new TwitterDao(httpHelper);
  }

  @Test
  public void create() throws JsonProcessingException {
    String hashTag = "#abc";
    String text = "@Test this is a test for twitter api " + hashTag + " " + System.currentTimeMillis();

    logger.info("Text to add" + text);
    double lat = 1d;
    double lon = -1d;

    Tweet postTweet = TweetUtil.buildTweet(text,lon,lat);
    logger.info("Tweet model: \n" + JsonUtil.toPrettyJson(postTweet));

    //create tweet
    //save response in tweet object
    Tweet tweet = dao.create(postTweet);

    assertEquals(text,tweet.getText());
    assertNotNull(tweet.getCoordinates());
    assertEquals(2,tweet.getCoordinates().getCoordinates().length);
    assertEquals(lon,tweet.getCoordinates().getCoordinates()[0],0.0);
    assertEquals(lat,tweet.getCoordinates().getCoordinates()[1],0.0);
    assertTrue(hashTag.contains(tweet.getEntities().getHashtags().get(0).getText()));
  }

  @Test
  public void findById() throws JsonProcessingException {
    String id = "1531262063095554048";

    //find tweet by id
    //save response in tweet object
    Tweet findTweet = dao.findById(id);
    logger.info("Found tweet: \n" +JsonUtil.toPrettyJson(findTweet));

    assertEquals(id,findTweet.getIdStr());
    assertNotNull(findTweet.getCoordinates());
    assertEquals(2,findTweet.getCoordinates().getCoordinates().length);
  }

  @Test
  public void deleteById() throws JsonProcessingException {
    String id = "1531262291383025668";

    //delete tweet by id
    //save response in tweet object
    Tweet deleteTweet = dao.deleteById(id);
    logger.info("Deleted tweet: \n" +JsonUtil.toPrettyJson(deleteTweet));

    assertEquals(id,deleteTweet.getIdStr());
    assertNotNull(deleteTweet.getCoordinates());
    assertEquals(2,deleteTweet.getCoordinates().getCoordinates().length);

  }
}