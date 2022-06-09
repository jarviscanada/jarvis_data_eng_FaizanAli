package ca.jrvs.apps.twitter.Util;

import ca.jrvs.apps.twitter.Model.Coordinates;
import ca.jrvs.apps.twitter.Model.Tweet;

public class TweetUtil {

  public static Tweet buildTweet(String text, Double lon, Double lat){

    Coordinates coord = new Coordinates();

    coord.setCoordinates(new double[]{lon,lat});
    coord.setType("point");

    Tweet tweet = new Tweet();

    tweet.setText(text);
    tweet.setCoordinates(coord);

    return tweet;
  }
}
