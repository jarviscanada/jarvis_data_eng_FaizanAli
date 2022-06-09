package ca.jrvs.apps.twitter.controller;

import static ca.jrvs.apps.twitter.Util.TweetUtil.buildTweet;

import ca.jrvs.apps.twitter.Model.Tweet;
import ca.jrvs.apps.twitter.dao.CrdDao;
import ca.jrvs.apps.twitter.dao.TwitterDao;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import ca.jrvs.apps.twitter.dao.helper.TwitterHttpHelper;
import ca.jrvs.apps.twitter.service.Service;
import ca.jrvs.apps.twitter.service.TwitterService;
import java.util.List;
import org.springframework.util.StringUtils;

public class TwitterController implements Controller{
  private static final String COORD_SEP = ":";
  private static final String COMMA = ",";

  private Service service;

  public TwitterController(Service service){
    this.service = service;
  }

  @Override
  public Tweet postTweet(String[] args) {
    if(args.length !=3){
      throw new IllegalArgumentException("USAGE: TwitterCLIApp post \"tweet_test\" \"latitude:logitude\" ");
    }

    String tweetTxt = args[1];
    String coord = args[2];
    String[] coordArray = coord.split(COORD_SEP);

    if(coordArray.length !=2 || StringUtils.isEmpty(tweetTxt)){
      throw new IllegalArgumentException("Invalid location format");
    }

    Double lat = null;
    Double lon = null;

    try{
      lat = Double.parseDouble(coordArray[0]);
      lon = Double.parseDouble(coordArray[1]);
    }catch (Exception e){
      throw new IllegalArgumentException("Invalid location format",e);
    }

    Tweet postTweet = buildTweet(tweetTxt,lon,lat);


    return service.postTweet(postTweet);
  }

  @Override
  public Tweet showTweet(String[] args) {
    return null;
  }

  @Override
  public List<Tweet> deleteTweet(String[] args) {
    return null;
  }
}
