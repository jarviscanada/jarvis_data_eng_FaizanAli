package ca.jrvs.apps.twitter;

import ca.jrvs.apps.twitter.Model.Tweet;
import ca.jrvs.apps.twitter.Util.JsonUtil;
import ca.jrvs.apps.twitter.controller.Controller;
import ca.jrvs.apps.twitter.controller.TwitterController;
import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import com.fasterxml.jackson.core.JsonProcessingException;

public class TwitterCLIApp {

  private Controller controller;

  public TwitterCLIApp(Controller controller) {
    this.controller = controller;
  }

  public static void main(String[] args) {
    String CONSUMER_KEY = System.getenv("consumerKey");
    String CONSUMER_SECRET = System.getenv("consumerSecret");
    String ACCESS_TOKEN = System.getenv("accessToken");
    String TOKEN_SECRET = System.getenv("tokenSecret");

    HttpHelper httpHelper = new TwitterHttpHelper(CONSUMER_KEY,CONSUMER_SECRET,ACCESS_TOKEN,TOKEN_SECRET);

    CrdDao dao = new TwitterDao(httpHelper);
    Service service = new TwitterService(dao);

    Controller controller = new TwitterController(service);
    TwitterCLIApp app = new TwitterCLIApp(controller);

    app.run(args);

  }

  private void run(String[] args) {
    printTweet(controller.postTweet(args));
  }

  private void printTweet(Tweet postTweet) {
    try {
      System.out.println(JsonUtil.toPrettyJson(postTweet));
    } catch (JsonProcessingException e) {
      e.printStackTrace();
    }
  }

}
