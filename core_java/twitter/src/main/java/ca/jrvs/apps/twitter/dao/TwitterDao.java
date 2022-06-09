package ca.jrvs.apps.twitter.dao;

import ca.jrvs.apps.twitter.Model.Tweet;
import ca.jrvs.apps.twitter.Util.JsonUtil;
import ca.jrvs.apps.twitter.dao.helper.HttpHelper;
import com.google.gdata.util.common.base.PercentEscaper;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TwitterDao implements CrdDao<Tweet, String> {

  private static final Logger logger = LoggerFactory.getLogger(TwitterDao.class);

  //URI constants
  private static final String API_BASE_URI = "https://api.twitter.com";
  private static final String POST_PATH = "/1.1/statuses/update.json";
  private static final String SHOW_PATH = "/1.1/statuses/show.json";
  private static final String DELETE_PATH = "/1.1/statuses/destroy/";

  //URI symbols
  private static final String QUERY_SYM = "?";
  private static final String AMPERSAND = "&";
  private static final String EQUAL = "=";

  //Response code
  private static final int HTTP_OK = 200;

  private HttpHelper httpHelper;

  public TwitterDao(HttpHelper httpHelper) {
    this.httpHelper = httpHelper;
  }


  @Override
  public Tweet create(Tweet entity) {
    //Construct URI
    URI uri;
    try {
      uri = getPostURI(entity);

      //Execute HTTP Request
      HttpResponse response = httpHelper.httpPost(uri);

      //Validate Response and deserialize response to Tweet object
      return parseResponseBody(response, HTTP_OK);

    } catch (URISyntaxException e) {
      logger.error("URI Syntax error", e);
      throw new IllegalArgumentException();
    }
  }

  private URI getPostURI(Tweet entity) throws URISyntaxException {

    //replace space and special characters with URL escape characters
    PercentEscaper percentEscaper = new PercentEscaper("", false);
    String status = percentEscaper.escape(entity.getText());
    double longitude = entity.getCoordinates().getCoordinates()[0];
    double latitude = entity.getCoordinates().getCoordinates()[1];

    URI uri = new URI(API_BASE_URI + POST_PATH + QUERY_SYM + "status" + EQUAL + status + AMPERSAND
        + "long" + EQUAL + longitude + AMPERSAND + "lat" + EQUAL + latitude);
    logger.info("post uri:" + uri);
    return uri;
  }

  @Override
  public Tweet findById(String s) {
    //Construct URI
    URI uri;
    try {
      uri = getShowURI(s);

      //Execute HTTP Request
      HttpResponse response = httpHelper.httpGet(uri);

      //Validate Response and deserialize response to Tweet object
      return parseResponseBody(response, HTTP_OK);

    } catch (URISyntaxException e) {
      logger.error("URI Syntax error", e);
      throw new IllegalArgumentException();
    }
  }

  private URI getShowURI(String s) throws URISyntaxException {

    URI uri = new URI(API_BASE_URI + SHOW_PATH + QUERY_SYM + "id" + EQUAL + s);

    return uri;
  }

  @Override
  public Tweet deleteById(String s) {
    //Construct URI
    URI uri;
    try {
      uri = getDeleteURI(s);

      //Execute HTTP Request
      HttpResponse response = httpHelper.httpPost(uri);

      //Validate Response and deserialize response to Tweet object
      return parseResponseBody(response, HTTP_OK);

    } catch (URISyntaxException e) {
      logger.error("URI Syntax error", e);
      throw new IllegalArgumentException();
    }
  }

  private URI getDeleteURI(String s) throws URISyntaxException {
    URI uri = new URI(API_BASE_URI + DELETE_PATH + s + ".json");

    return uri;
  }

  private Tweet parseResponseBody(HttpResponse response, Integer expectedStatusCode) {
    //check response status
    int status = response.getStatusLine().getStatusCode();

    if (status != expectedStatusCode) {
      try {
        logger.error(EntityUtils.toString(response.getEntity()));
      } catch (IOException e) {
        logger.error("Response has no entity");
      }

      throw new RuntimeException("Unexpected HTTP status:" + status);
    }

    //if empty response body
    if (response.getEntity() == null) {
      logger.error("Empty response body");
      throw new RuntimeException("Empty response body");
    }

    //convert Response Entity to str
    String jsonStr;
    try {
      jsonStr = EntityUtils.toString(response.getEntity());
    } catch (IOException e) {
      logger.error("Failed to convert entity to str", e);
      throw new RuntimeException("Failed to convert entity to string", e);
    }

    try {
      Tweet tweet = JsonUtil.toObjectFromJson(jsonStr, Tweet.class);
      return tweet;
    } catch (IOException e) {
      logger.error("Failed to convert JSON String to object", e);
      throw new RuntimeException("Failed to convert JSON String to object", e);
    }

  }

}
