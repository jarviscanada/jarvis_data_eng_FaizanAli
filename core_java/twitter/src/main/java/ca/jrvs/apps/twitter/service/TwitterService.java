package ca.jrvs.apps.twitter.service;

import ca.jrvs.apps.twitter.Model.Tweet;
import ca.jrvs.apps.twitter.dao.CrdDao;
import java.util.ArrayList;
import java.util.List;

public class TwitterService implements Service{

  private CrdDao dao;
  private static final int TWEET_MAX_SIZE = 140;

  public TwitterService(CrdDao dao){
    this.dao = dao;
  }

  @Override
  public Tweet postTweet(Tweet tweet) {
    validatePostTweet(tweet);
    return (Tweet) dao.create(tweet);
  }

  private void validatePostTweet(Tweet tweet){
    if(tweet.getText().length() > TWEET_MAX_SIZE  || tweet.getText().length() <= 0){
      throw new IllegalArgumentException("Tweet length greater than " + TWEET_MAX_SIZE + " or less than 0");
    }
  }

  @Override
  public Tweet showTweet(String id, String[] fields) {
    validateShowTweet(id,fields);
    return null;
  }

  private void validateShowTweet(String id, String[] fields) {
    if(!id.matches("[0-9]+")){
      throw new IllegalArgumentException("ID must consist of only numbers");
    }
  }

  @Override
  public List<Tweet> deleteTweets(String[] ids) {
    List<Tweet> deletedTweets = new ArrayList<>();
    for(String id : ids){
      Tweet temp = (Tweet) dao.deleteById(id);
      deletedTweets.add(temp);
    }
    return deletedTweets;
  }
}
