package ca.jrvs.apps.twitter.Util;

import ca.jrvs.apps.twitter.Model.Tweet;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.io.IOException;

public class JsonUtil {

  /**
   * Parse JSON string to a object
   *
   * @param json JSON str
   * @param c    object class
   * @param <T>  Type
   * @return Object
   * @throws IOException
   */
  public static <T> T toObjectFromJson(String json, Class c) throws IOException {
    ObjectMapper m = new ObjectMapper();
    return (T) m.readValue(json, c);
  }

  public static String toPrettyJson(Tweet postTweet) throws JsonProcessingException {
    ObjectMapper m = new ObjectMapper();

    //dont include none value
    m.setSerializationInclusion(Include.NON_NULL);

    //pretty print
    m.enable(SerializationFeature.INDENT_OUTPUT);

    return m.writeValueAsString(postTweet);
  }
}
